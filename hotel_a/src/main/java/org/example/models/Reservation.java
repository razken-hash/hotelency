package org.example.models;

import com.example.grpc.Basics;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.type.DateTime;
import lombok.*;
import org.example.grpc.HotelEntities;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String client;
    private DateTime dateIn;
    private DateTime dateOut;
    private Double amount;
    @ManyToOne
    @JsonIgnore
    private Room room;

    public Reservation(String client, DateTime dateIn, DateTime dateOut, Double amount, Room room) {
        this.client = client;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.amount = amount;
        this.room = room;
    }

    HotelEntities.Reservation buildGRPC() {
        return HotelEntities.Reservation.newBuilder()
                .setId(this.id)
                .setClient(this.client)
                .setAmount(this.amount)
                .setDateIn(
                        Basics.Date.newBuilder()
                                .setYear(this.dateIn.getYear())
                                .setMonth(this.dateIn.getMonth())
                                .setDay(this.dateIn.getDay())
                                .build()
                )
                .setDateOut(
                        Basics.Date.newBuilder()
                                .setYear(this.dateOut.getYear())
                                .setMonth(this.dateOut.getMonth())
                                .setDay(this.dateOut.getDay())
                                .build()
                )
                .build();
    }
}
