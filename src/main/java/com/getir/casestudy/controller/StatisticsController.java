package com.getir.casestudy.controller;

import com.getir.casestudy.model.response.CustomerStatisticResponse;
import com.getir.casestudy.service.StatisticService;
import com.getir.casestudy.util.Util;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/statistics")
@Api
@AllArgsConstructor
@SwaggerDefinition(tags = {
        @Tag(name = "statistics-api", description = "Statistics Api")
})
public class StatisticsController {
    private static final Logger log = LoggerFactory.getLogger(StatisticsController.class);

    private final StatisticService statisticService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('CUSTOMER')")
    @ApiOperation(value = "Get User Statistics", notes = "Get User Statistics", authorizations = {@Authorization(value = "jwtToken")})
    public CustomerStatisticResponse getCustomerStatistics() {
        String userId = Util.getUserId();
        log.debug("Get customer statistics by user id {}", userId);
        return statisticService.getCustomerStatistics(userId);
    }
}
