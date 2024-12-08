package com.example.restprojecthotel.repository;

import com.example.restprojecthotel.entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {
}

