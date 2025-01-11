package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.grpc.HotelEntities;
import org.example.grpc.HotelServiceOuterClass;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Integer number;
    private Integer size;
    private Double price;
    @ManyToOne
    private Hotel hotel;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    public Room(Integer number, Integer size, Double price, Hotel hotel, List<Reservation> reservations) {
        this.number = number;
        this.size = size;
        this.price = price;
        this.hotel = hotel;
        this.reservations = reservations;
    }

    public HotelEntities.Room buildGRPC() {
        return HotelEntities.Room.newBuilder()
                .setId(this.id)
                .setNumber(this.number)
                .setSize(this.size)
                .setPrice(this.price)
                .addAllReservations(
                        reservations
                                .stream()
                                .map(Reservation::buildGRPC)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
