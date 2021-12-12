package com.getir.casestudy.repository;

import com.getir.casestudy.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IBookRepository extends MongoRepository<Book, String> {
    Page<Book> findAll(Pageable pageable);
    Book findBookById(String id);
}
