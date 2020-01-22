package com.postnov.library.reposutory;

import com.postnov.library.model.ReceivedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface ReceivedBookRepository extends JpaRepository<ReceivedBook, Long> {

    @Query(value = "select rb from ReceivedBook rb " +
            "where rb.bookId = :bookId and rb.libraryCardId = :libraryCardId " +
            "and rb.dateOfBookReturn = null")
    Optional<ReceivedBook> findReceivedBook(Long libraryCardId, Long bookId);

    @Query(value = "select rb from ReceivedBook rb " +
            "where rb.libraryCardId = :libraryCardId and rb.dateOfBookReturn = null")
    Set<ReceivedBook> findReceivedBookByLibraryCardId(Long libraryCardId);

    @Modifying
    @Query(value = "update ReceivedBook set dateOfBookReturn=:dateOfBookReturn")
    void returnBook(LocalDate dateOfBookReturn);
}
