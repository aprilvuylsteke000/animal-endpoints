package io.github.aprilvuylsteke000.animalendpoints.repository;

import io.github.aprilvuylsteke000.animalendpoints.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
