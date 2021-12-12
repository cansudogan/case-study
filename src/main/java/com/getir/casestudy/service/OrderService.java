package com.getir.casestudy.service;

import com.getir.casestudy.domain.Book;
import com.getir.casestudy.domain.Order;
import com.getir.casestudy.domain.User;
import com.getir.casestudy.exception.OrderNotFoundException;
import com.getir.casestudy.exception.StockException;
import com.getir.casestudy.model.dto.BookDetailDTO;
import com.getir.casestudy.model.dto.OrderDTO;
import com.getir.casestudy.model.dto.OrderResponseDTO;
import com.getir.casestudy.model.request.CustomerPageRequest;
import com.getir.casestudy.model.request.OrderByDateRequest;
import com.getir.casestudy.model.request.OrderCreateRequest;
import com.getir.casestudy.model.response.CustomerPageResponse;
import com.getir.casestudy.model.response.OrderListResponse;
import com.getir.casestudy.model.response.OrderResponse;
import com.getir.casestudy.repository.IBookRepository;
import com.getir.casestudy.repository.IOrderRepository;
import com.getir.casestudy.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final IBookRepository bookRepository;
    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;

    public OrderResponse createOrder(OrderCreateRequest request, String userId) {
        log.debug("OrderService - createOrder started");

        List<String> bookIDs = new ArrayList<>();
        request.getBookOrders().forEach(bookDetailDTO -> bookIDs.add(bookDetailDTO.getBookId()));
        List<Book> books = (List<Book>) bookRepository.findAllById(bookIDs);

        for (BookDetailDTO dto : request.getBookOrders()) {
            if (dto.getBookCount() < 1) {
                throw new OrderNotFoundException();
            }
        }

        Order order = Order.builder()
                .userId(userId)
                .book(books)
                .dateCreated(new Date())
                .totalBookCount(calculateTotalBookCount(request))
                .totalPrice(calculateTotalPrice(request, books))
                .build();

        updateBookStock(request, books);

        orderRepository.save(order);
        User user = userRepository.getById(userId);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrder(order.responseDTO(order));
        orderResponse.setUser(user.responseDTO(user));

        log.debug("OrderService - createOrder - order created");
        return orderResponse;
    }

    private BigDecimal calculateTotalPrice(OrderCreateRequest request, List<Book> books) {
        log.debug("OrderService - calculateTotalPrice started");
        long totalPrice = 0L;
        for (BookDetailDTO dto : request.getBookOrders()) {
            Book book = books.stream().filter(b -> b.getId().equals(dto.getBookId())).findAny().orElse(null);
            if (book != null) {
                totalPrice += book.getPrice().longValue() * dto.getBookCount();
            } else {
                throw new EntityExistsException();
            }
        }
        log.debug("OrderService - calculateTotalPrice - totalPrice calculated");
        return new BigDecimal(totalPrice);
    }

    private void updateBookStock(OrderCreateRequest request, List<Book> books) {
        log.debug("OrderService - updateBookStock started");
        for (BookDetailDTO dto : request.getBookOrders()) {
            Book book = books.stream().filter(b -> b.getId().equals(dto.getBookId())).findAny().orElse(null);
            if (book != null) {
                if (book.getRemainingStock() >= dto.getBookCount()) {
                    book.setRemainingStock(book.getRemainingStock() - dto.getBookCount());
                    bookRepository.save(book);
                    log.debug("OrderService - updateBookStock - bookStockInformation updated");
                } else {
                    log.error("Book not found");
                    throw new StockException();
                }
            }

        }
    }

    private Integer calculateTotalBookCount(OrderCreateRequest request) {
        log.debug("OrderService - calculateTotalBookCount started");
        Integer totalBookCount = 0;
        for (BookDetailDTO dto : request.getBookOrders()) {
            totalBookCount += dto.getBookCount();
        }
        log.debug("OrderService - calculateTotalBookCount - totalBookCount calculated");
        return totalBookCount;
    }

    public OrderResponse getOrderById(String userId, String orderId) {
        OrderResponse orderResponse = new OrderResponse();
        User user = userRepository.getById(userId);

        orderResponse.setUser(user.responseDTO(user));

        OrderResponseDTO orderResponseDTO;
        Order order = orderRepository.getByIdAndUserId(orderId, userId);

        if (order != null) {
            orderResponseDTO = order.responseDTO(order);
        } else {
            throw new EntityNotFoundException("No order found for customer");
        }
        orderResponse.setOrder(orderResponseDTO);
        //log.debug("Order response is ready for customer {}, response {}", customer.responseDTO(customer), orderResponse);
        return orderResponse;
    }

    public OrderListResponse getOrderByDateInterval(String userId, OrderByDateRequest request) {
        List<Order> orderList = orderRepository.getAllByUserIdAndDateCreatedBetween(userId, request.getStartDate(), request.getEndDate());
        OrderListResponse response = new OrderListResponse();
        response.setOrders(orderList);

        return response;
    }

    public CustomerPageResponse getAllOrders(CustomerPageRequest request, String userId) {
        CustomerPageResponse customerPageResponse = new CustomerPageResponse();

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        List<Order> orderList = orderRepository.findAllByUserId(userId, pageable);

        if (orderList.isEmpty()) {
            customerPageResponse.setOrder(new PageImpl<>(Collections.emptyList()));
            return customerPageResponse;
        }
        customerPageResponse.setOrder(new PageImpl<>(orderList, PageRequest.of(request.getPage(), request.getSize()), orderRepository.count()));
        log.debug("CustomerService - getCustomerOrders is finished {}", customerPageResponse);
        return customerPageResponse;

    }
}
