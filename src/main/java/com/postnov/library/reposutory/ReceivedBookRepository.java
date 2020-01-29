package com.postnov.library.reposutory;

import com.postnov.library.model.ReceivedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ReceivedBookRepository extends JpaRepository<ReceivedBook, Long> {

    @Modifying
    @Query(value = "update ReceivedBook set dateOfBookReturn=:dateOfBookReturn")
    void returnBook(
            @Param("dateOfBookReturn") LocalDate dateOfBookReturn);

    @Query(value = "select rb from ReceivedBook rb " +
            "where rb.bookId = :bookId and rb.libraryCardId = :libraryCardId " +
            "and rb.dateOfBookReturn = null")
    Optional<ReceivedBook> findReceivedBook(
            @Param("libraryCardId") Long libraryCardId,
            @Param("bookId") Long bookId);

    @Query(value = "select rb from ReceivedBook rb " +
            "where rb.libraryCardId = :libraryCardId and rb.dateOfBookReturn = null")
    List<ReceivedBook> findReceivedBookByLibraryCardId(
            @Param("libraryCardId") Long libraryCardId);

    @Query(value = "select rb from ReceivedBook rb " +
            "where rb.libraryCardId = :libraryCardId")
    List<ReceivedBook> findHistoryReceivedBookByLibraryCardId(
            @Param("libraryCardId") Long libraryCardId);

    @Query(value = "select rb from ReceivedBook rb " +
            "where rb.id >= :fromReceivedBookId and rb.id <= :toReceivedBookId and rb.dateOfBookReturn = null")
    Set<ReceivedBook> findAllReceivedBook(
            @Param("fromReceivedBookId") Long fromReceivedBookId,
            @Param("toReceivedBookId") Long toReceivedBookId);

    @Query(value = "select rb from ReceivedBook rb where rb.dateOfBookReturn = null")
    Set<ReceivedBook> findAllReceivedBookForScheduled();

    void deleteByBookId(
            @Param("bookId") Long bookId);
}
