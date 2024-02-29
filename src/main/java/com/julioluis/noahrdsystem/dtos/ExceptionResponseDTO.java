package com.julioluis.noahrdsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseDTO {

    private Date timeStamp;
    private String message;
    private String detail;


}
