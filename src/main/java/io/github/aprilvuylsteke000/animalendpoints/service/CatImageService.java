package io.github.aprilvuylsteke000.animalendpoints.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatImageService {

    private final RestTemplate restTemplate = new RestTemplate();

    public byte[] fetchCatImage() throws Exception {
        String apiUrl = "https://api.thecatapi.com/v1/images/search";
        String response = restTemplate.getForObject(apiUrl, String.class);

        // Parse the JSON response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response);
        String imageUrl = rootNode.get(0).get("url").asText();

        // Fetch the image binary data
        return restTemplate.getForObject(imageUrl, byte[].class);
    }
}
