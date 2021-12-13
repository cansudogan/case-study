package com.getir.casestudy.service;

import com.getir.casestudy.domain.Book;
import com.getir.casestudy.domain.Order;
import com.getir.casestudy.repository.IOrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {
    @InjectMocks
    private StatisticService statisticService;

    @Mock
    private IOrderRepository orderRepository;

    @Test
    public void testGetCustomerStatistics() {
        Book book = new Book();
        book.setId("cansubook");
        book.setTitle("Test");
        book.setDescription("Test test test");
        book.setAuthor("Test test");
        book.setPrice(new BigDecimal(500));
        book.setRemainingStock(10L);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book);
        bookList.add(book);

        Order order1 = new Order();
        order1.setId("cansuorder");
        order1.setDateCreated(new Date(2021, Calendar.DECEMBER, 12));
        order1.setTotalPrice(new BigDecimal(500));
        order1.setUserId("cansu");
        order1.setBook(bookList);
        order1.setTotalBookCount(10);

        Order order2 = new Order();
        order2.setId("cansuorder2");
        order2.setDateCreated(new Date(2021, Calendar.OCTOBER, 19));
        order2.setTotalPrice(new BigDecimal(500));
        order2.setUserId("cansu");
        order2.setBook(bookList);
        order2.setTotalBookCount(15);

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);

        Mockito.when(orderRepository.findByUserId("cansu")).thenReturn(orderList);
        statisticService.getCustomerStatistics("cansu");
    }
}
