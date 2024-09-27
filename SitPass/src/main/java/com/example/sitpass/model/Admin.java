package com.example.sitpass.model;

import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@DiscriminatorValue("Admin")
public class Admin extends User {

}
