package com.getir.casestudy.controller;

import com.getir.casestudy.model.request.BookCreateRequest;
import com.getir.casestudy.model.request.BookStockUpdateRequest;
import com.getir.casestudy.model.response.BookResponse;
import com.getir.casestudy.service.BookService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping(value = "/api/book")
@Api
@SwaggerDefinition(tags = {
        @Tag(name = "book-api", description = "Book Api")})
public class BookController {
    private final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create Book", notes = "Create Book", authorizations = {@Authorization(value = "jwtToken")})
    public BookResponse createBook(@Valid @RequestBody BookCreateRequest request) {
        return bookService.createBook(request);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('CUSTOMER') OR hasRole('ADMIN')")
    @ApiOperation(value = "Get book by id ", notes = "Get book by id", authorizations = {@Authorization(value = "jwtToken")})
    public BookResponse getBook(@PathVariable String id) {
        return bookService.getBook(id);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update Book", notes = "Update Book", authorizations = {@Authorization(value = "jwtToken")})
    public BookResponse updateBookStock(@Valid @RequestBody BookStockUpdateRequest request) {
        return bookService.updateBookStock(request);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Book", notes = "Delete Book", authorizations = {@Authorization(value = "jwtToken")})
    public BookResponse deleteBook(@PathVariable String id) {
        return bookService.deleteBook(id);
    }
}
