package com.neu.edu.controller;

import com.neu.edu.exception.AlreadyExistsException;
import com.neu.edu.request.*;
import com.neu.edu.service.MedicineOrderServiceImpl.CommonResponse;
import com.neu.edu.service.MedicineOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MedicineOrderController {

    @Autowired
    private MedicineOrderService medicineOrderService;

    @PostMapping("/register")
    public CommonResponse createUser(@Valid @RequestBody NewUserRequest request) throws AlreadyExistsException {
        return medicineOrderService.createUser(request);
    }

    @GetMapping("/user")
    public CommonResponse getAllUsers() {
        return medicineOrderService.getAllUsers();
    }


    @PostMapping("/customer")
    public CommonResponse createCustomer(@Valid @RequestBody NewCustomerRequest request){
        return medicineOrderService.createCustomer(request);
    }

    @PostMapping("/medicine")
    public CommonResponse createMedicine(@Valid @RequestBody NewMedicineRequest request) throws AlreadyExistsException {
        return medicineOrderService.createMedicine(request);
    }

    @PostMapping("/orders")
    public CommonResponse createOrder(@Valid @RequestBody NewOrderRequest request){
        return medicineOrderService.createOrder(request);
    }

    @GetMapping("/customer")
    public CommonResponse fetAllCustomers(){
        return medicineOrderService.fetchAllCustomers();
    }

    @GetMapping("/medicines")
    public CommonResponse fetAllMedicines(@RequestHeader String userHashId){
        return medicineOrderService.fetchAllMedicines(userHashId);
    }

    @GetMapping("/orders")
    public CommonResponse fetAllOrders(@RequestHeader String userHashId) throws Exception {
        return medicineOrderService.fetchAllOrders(userHashId);
    }

    @PostMapping("/login")
    public CommonResponse loginUser(@Valid @RequestBody LoginRequest request){
        return medicineOrderService.loginUser(request);
    }

}
