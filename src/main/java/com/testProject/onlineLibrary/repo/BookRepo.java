package com.testProject.onlineLibrary.repo;

import com.testProject.onlineLibrary.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, Long> {
}
