package com.example.restprojecthotel.graphql;

import com.example.restprojecthotel.entities.Reservation;
import com.example.restprojecthotel.service.ReservationService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReservationQueryResolver {

    private final ReservationService reservationService;

    public ReservationQueryResolver(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @QueryMapping
    public List<Reservation> getReservations() {
        return reservationService.getAllReservations();
    }

    @QueryMapping
    public Reservation getReservationById(Long id) {
        return reservationService.getReservationById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
    }
}
