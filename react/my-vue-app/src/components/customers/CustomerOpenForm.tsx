// import React, { useState } from 'react';
import "../../styles/dashboard/dashboard.css";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { CustomerModel } from "../../types/types";
import { SubmitHandler, useForm } from "react-hook-form";

export default function CustomerPopupForm({ onClose }: any) {
  const navigate = useNavigate(); // Initialize the navigate function
  const {
    register,
    handleSubmit,
    // watch,
    // reset,
    formState: { errors },
  } = useForm<CustomerModel>();

  const onSubmit: SubmitHandler<CustomerModel> = async (data) => {
    const userId: any = localStorage.getItem("userHashId");
    try {
      const response = await fetch("http://localhost:9999/customer", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          userHashId: userId,
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
        body: JSON.stringify(data),
      });
      const updatedResponse: any = await response.json();

      if (updatedResponse?.status === "SUCCESS") {
        toast.success("Customer created successfully");
      } else {
        console.log(updatedResponse?.message);
        toast.error("Customer Creation failed. Please try again.");
      }
    } catch (error) {
      console.log(error);
      toast.error("SOMETHING WENT WRONG");
    } finally {
      onClose();
    }
    window.location.reload();
  };

  const closePopup = () => {
    onClose();
    navigate("/dashboard/customers"); // Navigate back to the dashboard after closing the popup
  };
  
  return (
    <div className="popup">
      <div className="popup-content">
        <form onSubmit={handleSubmit(onSubmit)}>
          <button className="closebutton" onClick={closePopup}>
            X
          </button>
          <input
            placeholder="Full Name"
            {...register("name", { minLength: 1 })}
          />
          {errors?.name?.type === "required" && <p>This field is required</p>}
          {errors?.name?.type === "minLength" && (
            <p>First name cannot be empty</p>
          )}

          <input placeholder="City" {...register("city")} />

          <input placeholder="Address" {...register("address")} />

          <input placeholder="Phone Number" {...register("phoneNumber")} />
          <input type="submit" className="btn" />
        </form>
      </div>
    </div>
  );
}
