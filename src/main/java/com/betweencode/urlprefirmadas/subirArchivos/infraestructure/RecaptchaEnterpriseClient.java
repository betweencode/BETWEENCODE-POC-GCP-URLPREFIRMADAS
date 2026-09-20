package com.betweencode.urlprefirmadas.subirArchivos.infraestructure;

import com.betweencode.urlprefirmadas.subirArchivos.infraestructure.modelos.RecaptchaAssessmentRequest;
import com.betweencode.urlprefirmadas.subirArchivos.infraestructure.modelos.RecaptchaEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RecaptchaEnterpriseClient {


    @Value("${recaptcha.enterprise.base-url}")
    private String baseUrl;

    @Value("${gcp.storage.project-id}")
    private String projectId;

    @Value("${recaptcha.enterprise.api-key}")
    private String apiKey;
    @Value("${recaptcha.enterprise.site-key}")
    private String siteKey;
    @Value("${recaptcha.enterprise.expected-action}")
    private String expectedAction;
    @Value("${recaptcha.enterprise.minimum-score}")
    private Double minimumScore;



    public boolean autenticar(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }


        RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl + "/v1/projects/" + projectId)
                .build();

        AssessmentResponse response = restClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/assessments")
                        .queryParam("key", apiKey)
                        .build())
                .body(RecaptchaAssessmentRequest.builder()
                        .event(RecaptchaEvent.builder()
                                .token(token)
                                .siteKey(siteKey)
                                .expectedAction(expectedAction)
                                .build())
                        .build())
                .retrieve()
                .body(AssessmentResponse.class);

        return response != null
                && response.tokenProperties() != null
                && response.tokenProperties().valid()
                && expectedAction.equals(response.tokenProperties().action())
                && response.riskAnalysis() != null
                && response.riskAnalysis().score() >= minimumScore;
    }

    private record AssessmentResponse(TokenProperties tokenProperties, RiskAnalysis riskAnalysis) {
    }

    private record TokenProperties(boolean valid, String action) {
    }

    private record RiskAnalysis(double score) {
    }
}
