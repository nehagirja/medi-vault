package com.neu.edu.service;

import com.neu.edu.exception.AlreadyExistsException;
import com.neu.edu.request.*;
import com.neu.edu.service.MedicineOrderServiceImpl.CommonResponse;


public interface MedicineOrderService {
    CommonResponse createCustomer(NewCustomerRequest request);

    CommonResponse createMedicine(NewMedicineRequest request) throws AlreadyExistsException;

    CommonResponse createOrder(NewOrderRequest request);

    CommonResponse fetchAllCustomers();

    CommonResponse fetchAllMedicines(String userHashId);

    CommonResponse fetchAllOrders(String userHashId);

    CommonResponse createUser(NewUserRequest request) throws AlreadyExistsException;

    CommonResponse loginUser(LoginRequest request);

    CommonResponse getAllUsers();

}
