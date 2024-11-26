package com.rhr.lambdaForm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class FormData {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String message;
    private LocalDateTime createdAt;
}
