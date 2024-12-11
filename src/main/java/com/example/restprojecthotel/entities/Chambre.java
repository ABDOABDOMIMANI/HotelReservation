package com.example.restprojecthotel.entities;
import jakarta.persistence.*;
import com.example.restprojecthotel.entities.*;
import lombok.Data;

@Data
@Entity
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numero;
    @Enumerated(EnumType.STRING)
    private TypeChambre type;
    private double prix;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TypeChambre getType() {
        return type;
    }

    public void setType(TypeChambre type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}

