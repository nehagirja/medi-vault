package com.neu.edu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

    @Id
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    @JsonIgnore
    private String password;


    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private List<Customer> customers;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private List<Medicine> medicines;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private List<Orders> orders;
}
