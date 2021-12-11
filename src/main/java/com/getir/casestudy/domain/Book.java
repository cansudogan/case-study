package com.getir.casestudy.domain;

import com.getir.casestudy.model.dto.BookDTO;
import com.getir.casestudy.model.dto.BookResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableMongoAuditing
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    @Version
    private int version;

    @NotEmpty(message = "Title is required.")
    private String title;

    private String description;

    @NotEmpty(message = "Author is required.")
    private String author;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price can not be less than 0.")
    private BigDecimal price;

    @NotNull(message = "Stock is required.")
    @Min(0)
    private Long remainingStock;

    public BookDTO bookDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(getId());
        dto.setTitle(getTitle());
        dto.setAuthor(getAuthor());
        dto.setDescription(getDescription());
        dto.setPrice(getPrice().doubleValue());
        dto.setRemainingStock(getRemainingStock());

        return dto;
    }

    public BookResponseDTO responseDTO(Book book) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setAuthor(book.getAuthor());
        bookResponseDTO.setPrice(book.getPrice());

        return bookResponseDTO;
    }
}
