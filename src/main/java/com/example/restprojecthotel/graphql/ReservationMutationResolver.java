package com.example.restprojecthotel.graphql;

import com.example.restprojecthotel.entities.Reservation;
import com.example.restprojecthotel.service.ReservationService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ReservationMutationResolver {

    private final ReservationService reservationService;

    public ReservationMutationResolver(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @MutationMapping
    public Reservation createReservation(Long clientId, Long chambreId, String dateDebut, String dateFin) {
        // Construire et créer la réservation en appelant le service
        Reservation reservation = new Reservation();
        // Implémente la logique pour associer un client et une chambre ici
        return reservationService.createReservation(reservation);
    }

    @MutationMapping
    public boolean deleteReservation(Long id) {
        reservationService.deleteReservation(id);
        return true;
    }
}
