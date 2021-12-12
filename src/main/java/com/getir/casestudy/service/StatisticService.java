package com.getir.casestudy.service;

import com.getir.casestudy.domain.Order;
import com.getir.casestudy.model.dto.UserStatisticDTO;
import com.getir.casestudy.model.response.CustomerStatisticResponse;
import com.getir.casestudy.repository.IOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
@AllArgsConstructor
public class StatisticService {

    private final IOrderRepository orderRepository;

    public CustomerStatisticResponse getCustomerStatistics(String id) {
        CustomerStatisticResponse response = new CustomerStatisticResponse();
        List<UserStatisticDTO> statisticList = new ArrayList<>();

        List<Order> orderList = orderRepository.findByUserId(id);

        Map<Month, List<Order>> orderMap = orderList.stream()
                .collect(groupingBy(order -> {
                    LocalDate localDate = order.getDateCreated().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    return localDate.getMonth();
                }));

        orderMap.forEach((key, orders) -> {
            UserStatisticDTO dto = new UserStatisticDTO();
            dto.setMonth(key);
            dto.setTotalOrderCount(orders.size());
            dto.setTotalBookCount(orders.stream().mapToInt(order -> Math.toIntExact(order.getTotalBookCount())).sum());
            BigDecimal totalAmount = BigDecimal.ZERO;
            for (Order o : orders) {
                totalAmount = totalAmount.add(BigDecimal.valueOf(o.getTotalPrice().doubleValue()));
            }
            dto.setTotalPurchasedAmount(totalAmount);
            statisticList.add(dto);
        });

        response.setCustomerStatistics(statisticList);


        return response;
    }
}
