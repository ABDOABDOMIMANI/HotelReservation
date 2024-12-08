package com.example.restprojecthotel.service;

import com.example.restprojecthotel.entities.Chambre;
import com.example.restprojecthotel.entities.Client;
import com.example.restprojecthotel.entities.Reservation;
import com.example.restprojecthotel.repository.ChambreRepository;
import com.example.restprojecthotel.repository.ClientRepository;
import com.example.restprojecthotel.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Transactional
    public Reservation createReservation(Reservation reservation) {
        // Ensure the client is saved
        Client client = reservation.getClient();
        if (client.getId() == null) {
            client = clientRepository.save(client);
        } else {
            client = clientRepository.findById(client.getId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
        }

        // Ensure the chambre is saved
        Chambre chambre = reservation.getChambre();
        if (chambre.getId() == null) {
            chambre = chambreRepository.save(chambre);
        } else {
            chambre = chambreRepository.findById(chambre.getId())
                    .orElseThrow(() -> new RuntimeException("Chambre not found"));
        }

        // Set the saved entities back to the reservation
        reservation.setClient(client);
        reservation.setChambre(chambre);

        // Save and return the reservation
        return reservationRepository.save(reservation);
    }

    @Transactional
    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));

        // Update client if changed
        if (!reservation.getClient().equals(reservationDetails.getClient())) {
            Client client = clientRepository.findById(reservationDetails.getClient().getId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            reservation.setClient(client);
        }

        // Update chambre if changed
        if (!reservation.getChambre().equals(reservationDetails.getChambre())) {
            Chambre chambre = chambreRepository.findById(reservationDetails.getChambre().getId())
                    .orElseThrow(() -> new RuntimeException("Chambre not found"));
            reservation.setChambre(chambre);
        }

        reservation.setDateDebut(reservationDetails.getDateDebut());
        reservation.setDateFin(reservationDetails.getDateFin());

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}