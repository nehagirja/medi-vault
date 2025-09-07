package com.neu.edu.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {

    private String message;
    private Object data;
    private Date time;

    public CommonResponse(String message, Date time) {
        this.message = message;
        this.time = time;
    }
}
