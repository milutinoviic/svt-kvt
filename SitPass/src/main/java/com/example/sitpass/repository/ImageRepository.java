package com.example.sitpass.repository;

import com.example.sitpass.model.Image;
import com.example.sitpass.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {



}
