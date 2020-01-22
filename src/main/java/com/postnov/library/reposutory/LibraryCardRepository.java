package com.postnov.library.reposutory;

import com.postnov.library.model.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {

    Optional<LibraryCard> findLibraryCardByClientId(Long clientId);

    Optional<LibraryCard> findLibraryCardById(Long Id);

    void deleteByClientId(Long Id);

}
