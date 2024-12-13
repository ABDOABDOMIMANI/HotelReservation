package com.example.restprojecthotel.graphql;

import com.example.restprojecthotel.entities.Reservation;
import com.example.restprojecthotel.service.ReservationService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ReservationMutationResolver {

    private final ReservationService reservationService;

    public ReservationMutationResolver(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @MutationMapping
    public Reservation createReservation(
            @Argument Long clientId,
            @Argument Long chambreId,
            @Argument String dateDebut,
            @Argument String dateFin
    ) {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(java.time.LocalDate.parse(dateDebut));
        reservation.setDateFin(java.time.LocalDate.parse(dateFin));

        // Set client and chambre directly in the reservation
        reservation.setClient(new com.example.restprojecthotel.entities.Client());
        reservation.getClient().setId(clientId);

        reservation.setChambre(new com.example.restprojecthotel.entities.Chambre());
        reservation.getChambre().setId(chambreId);

        return reservationService.createReservation(reservation);
    }

    @MutationMapping
    public Reservation updateReservation(
            @Argument Long id,
            @Argument String dateDebut,
            @Argument String dateFin
    ) {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(java.time.LocalDate.parse(dateDebut));
        reservation.setDateFin(java.time.LocalDate.parse(dateFin));

        return reservationService.updateReservation(id, reservation);
    }

    @MutationMapping
    public boolean deleteReservation(@Argument Long id) {
        reservationService.deleteReservation(id);
        return true;
    }
}
