package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.grpc.HotelEntities;
import org.example.grpc.HotelServiceOuterClass;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String city;
    private String country;
    private String street;
    private Integer number;
    private String gps;
    @OneToOne
    private Hotel hotel;

    public Position(String city, String country, String street, Integer number, String gps) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.number = number;
        this.gps = gps;
    }
    public Position(String city, String country, String street, Integer number, String gps, Hotel hotel) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.number = number;
        this.gps = gps;
        this.hotel = hotel;
    }

    public HotelEntities.Position buildRPC() {
        return HotelEntities.Position.newBuilder()
                .setId(this.getId())
                .setCity(this.getCity())
                .setCountry(this.getCountry())
                .setStreet(this.getStreet())
                .setNumber(this.getNumber())
                .setGps(this.getGps())
                .build();
    }
}
