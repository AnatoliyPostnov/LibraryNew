package com.postnov.library.reposutory;

import com.postnov.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorById(Long author_id);

    List<Author> findAuthorByNameAndSurname(String name, String surname);

    @Query(value = "select max(a.id) from Author a")
    Optional<Long> findMaximalId();

    void deleteAuthorById(Long Id);
}