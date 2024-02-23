package com.julioluis.noahrdsystem.dtos;

import lombok.Data;

@Data
public class ResponseDTO<T> {
    private  T data;
    private boolean success;
    private String message;
}
