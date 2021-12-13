package com.getir.casestudy.service;

import com.getir.casestudy.domain.Book;
import com.getir.casestudy.model.request.BookCreateRequest;
import com.getir.casestudy.model.request.BookStockUpdateRequest;
import com.getir.casestudy.repository.IBookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private IBookRepository bookRepository;

    @Test
    public void testSaveBook() {
        BookCreateRequest request = BookCreateRequest.builder()
                .title("Test")
                .description("TestDesc")
                .author("Test Test")
                .price(new BigDecimal(11))
                .remainingStock(10L)
                .build();

        bookService.createBook(request);
    }

    @Test
    public void testUpdateBook() {
        Book book = Book.builder()
                .id("cansu")
                .title("Test")
                .description("TestDesc")
                .author("Test Test")
                .price(new BigDecimal(11))
                .remainingStock(10L)
                .build();
        Mockito.when(bookRepository.findBookById(book.getId())).thenReturn(book);

        BookStockUpdateRequest request = new BookStockUpdateRequest();
        request.setId("cansu");
        request.setRemainingStock(15L);

        bookService.updateBookStock(request);

    }

    @Test
    public void testDeleteBook() {
        Book book = new Book();
        book.setId("cansu");
        book.setTitle("Test");
        book.setAuthor("Test test");
        book.setDescription("Test test test");
        book.setPrice(new BigDecimal(50));

        Mockito.when(bookRepository.findBookById(book.getId())).thenReturn(book);

        bookService.deleteBook(book.getId());
    }

}
