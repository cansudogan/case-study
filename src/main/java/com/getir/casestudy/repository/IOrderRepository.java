package com.getir.casestudy.repository;

import com.getir.casestudy.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface IOrderRepository extends MongoRepository<Order, String> {
    List<Order> getAllByUserIdAndDateCreatedBetween(String userId, Date startDate, Date endDate);

    Order getByIdAndUserId(String orderId, String userId);

    List<Order> findByUserId(String id);

    List<Order> findAllByUserId(String userId, Pageable pageable);
}
