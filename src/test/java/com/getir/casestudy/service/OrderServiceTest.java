package com.getir.casestudy.service;

import com.getir.casestudy.domain.Book;
import com.getir.casestudy.domain.Order;
import com.getir.casestudy.domain.User;
import com.getir.casestudy.model.dto.BookDetailDTO;
import com.getir.casestudy.model.request.OrderByDateRequest;
import com.getir.casestudy.model.request.OrderCreateRequest;
import com.getir.casestudy.repository.IBookRepository;
import com.getir.casestudy.repository.IOrderRepository;
import com.getir.casestudy.repository.IUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private IBookRepository bookRepository;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IOrderRepository orderRepository;

    @Test
    public void testCreateOrder() {
        Book book = Book.builder()
                        .id("cansu")
                        .title("Test")
                        .description("Test test test")
                        .author("Test test")
                        .price(new BigDecimal(500))
                        .remainingStock(10L).build();
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book);
        bookList.add(book);

        Mockito.when(bookRepository.findAllById(Mockito.anyCollection())).thenReturn(bookList);

        User user = User.builder()
                .id("cansu")
                .username("cansudogan")
                .email("cansudogan95@gmail.com")
                .roles(new HashSet())
                .password("12345")
                .build();

        Mockito.when(userRepository.getById(user.getId())).thenReturn(user);

        List<BookDetailDTO> orders = new ArrayList<>();
        BookDetailDTO dto = new BookDetailDTO();
        dto.setBookCount(1);
        dto.setBookId("cansu");

        OrderCreateRequest request = new OrderCreateRequest();
        request.setBookOrders(orders);

        orderService.createOrder(request, "cansu");

    }

    @Test
    public void testGetOrderById() {
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

        Order order = new Order();
        order.setId("cansuorder");
        order.setDateCreated(new Date(2021, Calendar.DECEMBER, 12));
        order.setTotalPrice(new BigDecimal(500));
        order.setUserId("cansu");
        order.setBook(bookList);

        User user = new User();
        user.setId("cansu");
        user.setUsername("cansudogan");
        user.setEmail("cansudogan95@gmail.com");
        user.setRoles(new HashSet());
        user.setPassword("12345");

        Mockito.when(orderRepository.getByIdAndUserId(order.getId(), user.getId())).thenReturn(order);

    }

    @Test
    public void testGetOrderByDateInterval() {
        OrderByDateRequest request = new OrderByDateRequest();
        request.setStartDate(new Date(2020, Calendar.DECEMBER, 12));
        request.setEndDate(new Date(2021, Calendar.DECEMBER, 12));

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

        List<Order> orderList = new ArrayList<>();
        Order order = new Order();
        order.setId("cansuorder");
        order.setDateCreated(new Date(2021, Calendar.AUGUST, 7));
        order.setTotalPrice(new BigDecimal(500));
        order.setUserId("cansu");
        order.setBook(bookList);

        orderList.add(order);
        orderList.add(order);

        Mockito.when(orderRepository.getAllByUserIdAndDateCreatedBetween("cansu", request.getStartDate(), request.getEndDate()))
                .thenReturn(orderList);

        orderService.getOrderByDateInterval("cansu", request);

    }
}
