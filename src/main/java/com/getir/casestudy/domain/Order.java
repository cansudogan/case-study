package com.getir.casestudy.domain;

import com.getir.casestudy.model.dto.BookDTO;
import com.getir.casestudy.model.dto.BookResponseDTO;
import com.getir.casestudy.model.dto.OrderDTO;
import com.getir.casestudy.model.dto.OrderResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableMongoAuditing
@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    @Version
    private int version;

    private String userId;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price can not be less than 0.")
    private BigDecimal totalPrice;

    private Date dateCreated;

    @DBRef
    private List<Book> book;

    private Long totalBookCount;

    public OrderDTO orderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setDateCreated(order.getDateCreated());
        dto.setUserId(order.getUserId());
        dto.setTotalBookCount(order.getTotalBookCount());

        List<BookDTO> bookDTOS = new ArrayList<>();
        order.getBook().forEach(book -> bookDTOS.add(book.bookDTO(book)));

        dto.setBookList(bookDTOS);

        return dto;
    }

    public OrderResponseDTO responseDTO(Order order) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setDateCreated(order.getDateCreated());
        orderResponseDTO.setTotalPrice(order.getTotalPrice());

        List<BookResponseDTO> bookResponseDTOs = new ArrayList<BookResponseDTO>();
        order.getBook().forEach(book -> bookResponseDTOs.add(book.responseDTO(book)));

        orderResponseDTO.setBookList(bookResponseDTOs);

        return orderResponseDTO;
    }
}
