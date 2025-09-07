package com.neu.edu.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class NewOrderRequest {
    @NotEmpty(message = "customer mobile should not be empty")
    private String customerMobileNumber;
    @NotEmpty(message = "medicine name should not be empty")
    private String medicineName;
}
