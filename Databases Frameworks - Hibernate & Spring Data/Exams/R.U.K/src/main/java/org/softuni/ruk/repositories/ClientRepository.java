package org.softuni.ruk.repositories;

import org.softuni.ruk.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> getClientByFullName(String fullName);

    @Query(value = "SELECT c FROM Client as c ORDER BY c.bankAccount.cards.size DESC")
    List<Client> clientWithMostCards();
}