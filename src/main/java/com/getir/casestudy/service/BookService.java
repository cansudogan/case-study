package com.getir.casestudy.service;

import com.getir.casestudy.domain.Book;
import com.getir.casestudy.exception.BookNotFoundException;
import com.getir.casestudy.model.request.BookCreateRequest;
import com.getir.casestudy.model.request.BookStockUpdateRequest;
import com.getir.casestudy.model.response.BookResponse;
import com.getir.casestudy.repository.IBookRepository;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class BookService {
    private final Logger log = LoggerFactory.getLogger(BookService.class);

    private final IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse createBook(BookCreateRequest request) {
        log.debug("BookService - createBook started");
        Book book = Book.builder().title(request.getTitle())
                .description(request.getDescription())
                .author(request.getAuthor())
                .price(request.getPrice())
                .remainingStock(request.getRemainingStock()).build();

        bookRepository.save(book);

        BookResponse response = new BookResponse();
        response.setBook(book);

        log.debug("BookService - createBook - book created");
        return response;
    }

    public BookResponse getBook(String id) {
        log.debug("BookService - getBook started");
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        BookResponse response = new BookResponse();
        response.setBook(book);

        log.debug("BookService - getBook ended");
        return response;
    }

    public BookResponse updateBookStock(BookStockUpdateRequest request) {
        log.debug("BookService - updateBookStock started");
        Book book = bookRepository.findBookById(request.getId());

        if (book == null) { throw new BookNotFoundException();}
        book.setRemainingStock(request.getRemainingStock());

        bookRepository.save(book);

        BookResponse response = new BookResponse();
        response.setBook(book);

        log.debug("BookService - updateBookStock - bookStock updated");
        return response;
    }

    public String deleteBook(String id) {
        log.debug("BookService - deleteBook started");
        Book book = bookRepository.findBookById(id);
        if (book == null) { throw new BookNotFoundException();}

        bookRepository.delete(book);

        log.debug("BookService - deleteBook - book is deleted");
        return "Book is deleted";
    }
}
