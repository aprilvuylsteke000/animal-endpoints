package io.github.aprilvuylsteke000.animalendpoints.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "animalpictures") // Ensure the table name matches your database schema
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates ID values
    private Long id;

    @Lob // Indicates a large object (binary data)
    @Column(name = "picture_data", nullable = false) // Maps this field to a specific column with constraints
    private byte[] pictureData;

    @Column(name = "picture_name", nullable = false) //stores name generated in code
    private String pictureName;

    @Column(name = "animal_name", nullable = false) // Stores the type of animal requested
    private String animalName;


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPictureData() {
        return pictureData;
    }

    public void setPictureData(byte[] pictureData) {
        this.pictureData = pictureData;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

}
