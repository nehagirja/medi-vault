package com.neu.edu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders extends CommonFields {
    @Id
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "order_customer",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

    @ManyToMany
    @JoinTable(
            name = "order_medicine",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> medicines;

    private int quantity;
    private double orderTotal;

    @ManyToMany
    @JoinTable(
            name = "order_user",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> users;
}
