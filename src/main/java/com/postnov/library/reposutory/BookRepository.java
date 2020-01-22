package com.postnov.library.reposutory;

import com.postnov.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByNameAndVolume(String name, Integer volume);

    Optional<Book> findBookById(Long id);

    @Query(value = "select max(b.id) from Book b ")
    Optional<Long> findMaximalId();

    void deleteBookByNameAndVolume(String name, Integer volume);

    @Modifying
    @Query(value = "update Book set isReceivedBook = 'false' where id = :Id")
    void receivedBookById(Long Id);
}
