package io.github.aprilvuylsteke000.animalendpoints.controller;

import io.github.aprilvuylsteke000.animalendpoints.entity.Image;
import io.github.aprilvuylsteke000.animalendpoints.repository.ImageRepository;
import io.github.aprilvuylsteke000.animalendpoints.service.CatImageService;
import io.github.aprilvuylsteke000.animalendpoints.util.HtmlResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CatImageService catImageService;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String home() {
        return HtmlResponseBuilder.generateHomeHtml();
    }

    //retrieve endpoint starts here
    @GetMapping("/retrieveandstore")
    public ResponseEntity<String> retrieveAndStoreImages(
            @RequestParam(value = "animal", required = true) String animal,
            @RequestParam(value = "count", defaultValue = "1") int count) {

        // Check if 'animal' is provided
        if (animal == null || animal.isBlank()) {
            String errorHtml = HtmlResponseBuilder.generateErrorHtml("Please specify an animal (cat, dog, or bear).");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            return new ResponseEntity<>(errorHtml, headers, HttpStatus.BAD_REQUEST);
        }

        // Validate animal type
        String url;
        switch (animal.toLowerCase()) {
            case "cat":
                url = null; // Cat URL handled in the service
                break;
            case "dog":
                url = "https://place.dog/300/200";
                break;
            case "bear":
                url = "https://placebear.com/g/200/300";
                break;
            default:
                String errorHtml = HtmlResponseBuilder.generateErrorHtml("Invalid animal type. Choose 'cat', 'dog', or 'bear'.");
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "text/html");
                return new ResponseEntity<>(errorHtml, headers, HttpStatus.BAD_REQUEST);
        }

        // Proceed with valid animal types
        List<String> storedImages = new ArrayList<>();
        try {
            for (int i = 0; i < count; i++) {
                byte[] imageData;

                if ("cat".equalsIgnoreCase(animal)) {
                    // Use CatImageService for cats
                    imageData = catImageService.fetchCatImage();
                } else {
                    // Fetch image data for other animals
                    imageData = restTemplate.getForObject(url, byte[].class);
                }

                if (imageData != null) {
                    // Save image to the database
                    Image image = new Image();
                    image.setPictureName(animal + "_" + System.currentTimeMillis() + "_" + (i + 1));
                    image.setPictureData(imageData);
                    image.setAnimalName(animal);
                    imageRepository.save(image);

                    storedImages.add(image.getPictureName());
                }
            }

            String htmlResponse = HtmlResponseBuilder.generateRetrieveAndStoreHtml(animal, count, storedImages);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            return new ResponseEntity<>(htmlResponse, headers, HttpStatus.OK);

        } catch (Exception e) {
            String errorHtml = HtmlResponseBuilder.generateErrorHtml("Failed to retrieve and store images: " + e.getMessage());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            return new ResponseEntity<>(errorHtml, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get endpoint starts here
    @GetMapping("/get/{id}")
    public ResponseEntity<String> getImage(@PathVariable Long id) {
        try {
            return imageRepository.findById(id)
                    .map(image -> {
                        String htmlResponse = HtmlResponseBuilder.generateImageHtml(
                                image.getAnimalName(), image.getPictureData()
                        );
                        HttpHeaders headers = new HttpHeaders();
                        headers.add("Content-Type", "text/html");
                        return new ResponseEntity<>(htmlResponse, headers, HttpStatus.OK);
                    })
                    .orElseThrow(() -> new IllegalArgumentException("No image with the specified ID exists."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Failed to retrieve requested image - no image of that ID exists.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
