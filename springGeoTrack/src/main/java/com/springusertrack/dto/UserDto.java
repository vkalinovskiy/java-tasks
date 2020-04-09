package com.springusertrack.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
}
