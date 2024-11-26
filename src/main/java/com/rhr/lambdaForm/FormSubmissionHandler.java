package com.rhr.lambdaForm;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FormSubmissionHandler implements RequestHandler<Map<String, Object>, Map<String, String>> {
    private ObjectMapper objectMapper = new ObjectMapper();
    private S3Client s3Client = S3Client.builder().build();

    public FormSubmissionHandler() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());

        this.s3Client = S3Client.builder().build();
    }

    @Override
    public Map<String, String> handleRequest(Map<String, Object> input, Context context) {
        String body = input.get("body").toString();

        Map<String, String> bodyMap;

        try {
            bodyMap = objectMapper.readValue(body, Map.class);
        } catch (Exception exception) {
            throw new RuntimeException("Error parsing JSON body: " + exception.getMessage(), exception);
        }

        String id = UUID.randomUUID().toString();
        String name = bodyMap.get("name");
        String email = bodyMap.get("email");
        String phone = bodyMap.get("phone");
        String message = bodyMap.get("message");
        LocalDateTime createdAt = LocalDateTime.now();

        FormData formData = new FormData(id, name, email, phone, message, createdAt);

        try {
            String formDataJson = objectMapper.writeValueAsString(formData);

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket("form-contact-lambda-storage-s3-bucket")
                    .key(id + ".json")
                    .build();

            s3Client.putObject(request, RequestBody.fromString(formDataJson));
        } catch (Exception exception) {
            context.getLogger().log("Error saving data to S3: " + exception.getMessage());

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error: Unable to save form data.");

            return errorResponse;
        }

        Map<String, String> response = new HashMap<>();
        response.put("id", id);

        return response;
    }
}
