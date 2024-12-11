package com.example.restprojecthotel.repository;

import com.example.restprojecthotel.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Reservation findById(int id);

}

