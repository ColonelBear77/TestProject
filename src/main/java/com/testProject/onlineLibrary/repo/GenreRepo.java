package com.testProject.onlineLibrary.repo;

import com.testProject.onlineLibrary.domain.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepo extends CrudRepository<Genre,Long> {
}
