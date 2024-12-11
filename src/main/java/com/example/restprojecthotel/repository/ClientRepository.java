package com.example.restprojecthotel.repository;

import com.example.restprojecthotel.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findById(int id);
}

