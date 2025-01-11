package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.grpc.HotelServiceOuterClass;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public HotelServiceOuterClass.Position buildRPC() {
        return HotelServiceOuterClass.Position.newBuilder()
                .setId(this.getId())
                .setCity(this.getCity())
                .setCountry(this.getCountry())
                .setStreet(this.getStreet())
                .setNumber(this.getNumber())
                .setGps(this.getGps())
                .build();
    }
}
