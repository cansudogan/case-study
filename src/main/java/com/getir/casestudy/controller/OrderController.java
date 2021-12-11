package com.getir.casestudy.controller;

import com.getir.casestudy.model.request.CustomerPageRequest;
import com.getir.casestudy.model.request.OrderByDateRequest;
import com.getir.casestudy.model.request.OrderCreateRequest;
import com.getir.casestudy.model.response.CustomerPageResponse;
import com.getir.casestudy.model.response.OrderListResponse;
import com.getir.casestudy.model.response.OrderResponse;
import com.getir.casestudy.service.OrderService;
import com.getir.casestudy.util.Util;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/order")
@Api
@AllArgsConstructor
@SwaggerDefinition(tags = {
        @Tag(name = "order-api", description = "Order Api")
})
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('CUSTOMER')")
    @ApiOperation(value = "Create Order", notes = "Create Order", authorizations = {@Authorization(value = "jwtToken")})
    public OrderResponse createOrder(@Validated @RequestBody OrderCreateRequest request) {
        String userId = Util.getUserId();
        return orderService.createOrder(request, userId);
    }

    @GetMapping(value = "/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('CUSTOMER') OR hasRole('ADMIN')")
    @ApiOperation(value = "Get Order By Id", notes = "Get Order By Id", authorizations = {@Authorization(value = "jwtToken")})
    public OrderResponse getOrderById(@PathVariable String orderId) {
        log.debug("OrderController - getOrderById started");
        return orderService.getOrderById(Util.getUserId(), orderId);
    }

    @PostMapping(value = "/date")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('CUSTOMER') OR hasRole('ADMIN')")
    @ApiOperation(value = "Get Order By Date Interval", notes = "Get Order By Date Interval", authorizations = {@Authorization(value = "jwtToken")})
    public OrderListResponse getOrderByDateInterval(@Valid @RequestBody OrderByDateRequest request) {
        log.debug("Get order by date interval started for request {}", request);
        String userId = Util.getUserId();
        return orderService.getOrderByDateInterval(userId, request);
    }

    @PostMapping(value = "/getAllOrders")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('CUSTOMER')")
    @ApiOperation(value = "Get All User Order", notes = "Get All User Order", authorizations = {@Authorization(value = "jwtToken")})
    public CustomerPageResponse getAllOrders(@Valid @RequestBody CustomerPageRequest request) {
        log.info("Get customer orders started for request {}", request);
        String userId = Util.getUserId();
        return orderService.getAllOrders(request, userId);
    }
}
