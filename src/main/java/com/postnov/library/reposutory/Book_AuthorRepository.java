package com.postnov.library.reposutory;

import com.postnov.library.model.Book_Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface Book_AuthorRepository extends JpaRepository<Book_Author, Long> {

    @Query("SELECT ba.author_id FROM Book_Author ba WHERE ba.book_id = :book_id")
    Set<Long> findAuthorsIdByBookId(@Param("book_id") Long book_id);

    @Query("SELECT ba.book_id FROM Book_Author ba WHERE ba.author_id = :author_id")
    Optional<Long> findBooksIdByAuthorId(Long author_id);

    @Query(value = "select max(ba.id) from Book_Author ba ")
    Optional<Long> findMaximalId();

    @Modifying
    @Query(value = "delete from Book_Author WHERE author_id = :author_id")
    void deleteBook_AuthorByAuthor_id(@Param("author_id") Long author_id);
}