package com.cafh.poc.customer.controller;

import com.cafh.poc.customer.business.CustomerOperations;
import com.cafh.poc.customer.util.ValidAge;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Customer Services")
@RequestMapping(value = "v1/customers")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerOperations customerOperations;

    @Autowired
    private CustomerRestAdapter customerRestAdapter;

    @ApiOperation(value = "Create customer",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 201, message = "Success."),
            @ApiResponse(code = 400, message = "The data is not valid."),
            @ApiResponse(code = 500, message = "Error in the process of customer creation.")})
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@ApiParam(required = true, value = "Customer Information",
            name = "request") @Valid @RequestBody final CustomerRequest request) {
        customerOperations.createCustomer(
                customerRestAdapter.convertRequestToDomain(request));
    }

    @ApiOperation(value = "Get customer KPI", response = CustomerKpiResponse.class,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 400, message = "The data is not valid."),
            @ApiResponse(code = 500, message = "Error in the process of customer creation.")})
    @GetMapping(value = "/kpi", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public CustomerKpiResponse getCustomerKpi() {
        return customerRestAdapter.convertKpiToResponse(
                customerOperations.getCustomerKpi());
    }

    @ApiOperation(value = "Get customer list", response = CustomerResponse.class ,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 400, message = "The data is not valid."),
            @ApiResponse(code = 500, message = "Error in the process of customer creation.")})
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getCustomerList() {
        return customerRestAdapter.convertDomainListToResponseList(
                customerOperations.getCustomerList());
    }

}
