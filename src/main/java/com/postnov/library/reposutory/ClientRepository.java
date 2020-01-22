package com.postnov.library.reposutory;

import com.postnov.library.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByPassportId(Long passportId);

    Optional<Client> findClientById(Long Id);

}
