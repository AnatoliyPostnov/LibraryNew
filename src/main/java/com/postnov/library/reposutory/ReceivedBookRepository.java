package com.postnov.library.reposutory;

import com.postnov.library.model.ReceivedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReceivedBookRepository extends JpaRepository<ReceivedBook, Long> {

    @Query(value = "select max(rb.id) from ReceivedBook rb")
    Optional<Long> findMaximalId();

}
