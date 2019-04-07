package com.cafh.poc.customer.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerRequest {

    @ApiModelProperty("Nombre")
    @ApiParam(value = "Customer Name", type = "string", example ="Carlos")
    @NotNull
    private String name;

    @ApiModelProperty("Apellido")
    @ApiParam(value = "Customer Last Name", type = "string", example ="Fernandez")
    @NotNull
    private String lastName;

    @ApiModelProperty("Edad")
    @ApiParam(value = "Customer Age", type = "integer", example ="34")
    @NotNull
    private Integer age;

    @ApiModelProperty("Fecha de Nacimiento")
    @ApiParam(value = "Customer Date of Birth", type = "long", example ="472626000000")
    @NotNull
    private Long dateOfBirth;

}
