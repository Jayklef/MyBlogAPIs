package com.jayklef.my_blog_apis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetailsDto {
    private Integer status;
    private String message;
    private String path;
    private Date timestamp;
}
