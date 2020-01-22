package com.postnov.library.reposutory;

import com.postnov.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select b from Book b " +
            "WHERE b.name = :bookName and b.volume = :volume and b.isReceivedBook = 'true'")
    Optional<Book> findBookByNameAndVolume(String bookName, Integer volume);

    @Query(value = "select b from Book b " +
            "WHERE b.name = :bookName and b.volume = :volume and b.isReceivedBook = 'false'")
    Optional<Book> findReceivedBookByBookNameAndVolume(String bookName, Integer volume);

    Optional<Book> findBookById(Long id);

    void deleteBookByNameAndVolume(String name, Integer volume);

    @Modifying
    @Query(value = "update Book set isReceivedBook = 'false' where id = :Id")
    void receivedBookById(Long Id);

    @Query(value = "select id from Book where name = :booksName and isReceivedBook = 'false'")
    Set<Long> findBooksIdByBooksName(String booksName);

    @Modifying
    @Query(value = "update Book set isReceivedBook = 'true' where id = :bookId")
    void returnBook(Long bookId);
}
