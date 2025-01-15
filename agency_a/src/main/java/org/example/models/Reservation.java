package org.example.models;

import com.example.grpc.Basics;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.grpc.HotelEntities;
import org.example.grpc.HotelServiceOuterClass;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate dateIn;
    private LocalDate dateOut;
    private Double amount;
    @ManyToOne
    @JsonIgnore
    private Room room;

    public Reservation(String client, LocalDate dateIn, LocalDate dateOut, Double amount, Room room) {
        this.client = client;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.amount = amount;
        this.room = room;
    }

    public Reservation(Integer id, String client, LocalDate dateIn, LocalDate dateOut, Double amount) {
        this.id = id;
        this.client = client;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.amount = amount;
    }

    public HotelEntities.Reservation buildGRPC() {
        return HotelEntities.Reservation.newBuilder()
                .setId(this.id)
                .setClient(this.client)
                .setAmount(this.amount)
                .setDateIn(
                        Basics.Date.newBuilder()
                                .setYear(this.dateIn.getYear())
                                .setMonth(this.dateIn.getMonthValue())
                                .setDay(this.dateIn.getDayOfMonth())
                                .build()
                )
                .setDateOut(
                        Basics.Date.newBuilder()
                                .setYear(this.dateOut.getYear())
                                .setMonth(this.dateOut.getMonthValue())
                                .setDay(this.dateOut.getDayOfMonth())
                                .build()
                )
                .build();
    }

    static public Reservation fromGRPC(HotelEntities.Reservation grpcReservation) {
        return new Reservation(
                grpcReservation.getId(),
                grpcReservation.getClient(),
                LocalDate.of(grpcReservation.getDateIn().getYear(), grpcReservation.getDateIn().getMonth(), grpcReservation.getDateIn().getDay()),
                LocalDate.of(grpcReservation.getDateOut().getYear(), grpcReservation.getDateOut().getMonth(), grpcReservation.getDateOut().getDay()),
                grpcReservation.getAmount()
        );
    }
}
