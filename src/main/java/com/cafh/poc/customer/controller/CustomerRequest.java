package com.cafh.poc.customer.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CustomerRequest {

    @JsonProperty("Nombre")
    @ApiParam(value = "Customer Name", type = "string", example ="Carlos")
    @NotNull
    private String name;

    @JsonProperty("Apellido")
    @ApiParam(value = "Customer Last Name", type = "string", example ="Fernandez")
    @NotNull
    private String lastName;

    @JsonProperty("Edad")
    @ApiParam(value = "Customer Age", type = "integer", example ="34")
    @NotNull
    @Min(value = 0)
    private Integer age;

    @JsonProperty("Fecha de Nacimiento")
    @ApiParam(value = "Customer Date of Birth", type = "long", example ="472626000000")
    @NotNull
    @Min(value = 0)
    private Long dateOfBirth;

}
