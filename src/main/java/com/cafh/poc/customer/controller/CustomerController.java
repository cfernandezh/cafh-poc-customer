package com.cafh.poc.customer.controller;

import com.cafh.poc.customer.business.CustomerOperations;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Customer Services")
@RequestMapping(value = "v1/customers")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerOperations customerOperations;

    @Autowired
    CustomerRestAdapter customerRestAdapter;

    @ApiResponses({@ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 400, message = "The data is not valid."),
            @ApiResponse(code = 500, message = "Error in the process of customer creation.")})
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@ApiParam(required = true, value = "Customer Information",
            name = "request") @RequestBody final CustomerRequest request) {
        customerOperations.createCustomer(customerRestAdapter.convertRequestToDomain(request));
    }

}
