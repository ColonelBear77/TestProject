package com.testProject.onlineLibrary.repo;

import com.testProject.onlineLibrary.domain.Book;
import com.testProject.onlineLibrary.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {

    @Transactional
    List<Book> findAllByNameIgnoreCaseContaining(String keyword);

    @Transactional
    List<Book> findAllByWriterIgnoreCaseContaining(String keyword);

    @Transactional
    List<Book> findAllByGenre_id(Long genreId);
}
