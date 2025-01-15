package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.grpc.HotelEntities;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private Integer stars;
    private String imageFolder;
    @OneToOne(cascade = CascadeType.ALL)
    private Position position;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Room> rooms;

    public Hotel(String name, Integer stars, String imageFolder, Position position) {
        this.name = name;
        this.stars = stars;
        this.imageFolder = imageFolder;
        this.position = position;
    }

    public Hotel(String name, Integer stars, String imageFolder, Position position, List<Room> rooms) {
        this.name = name;
        this.stars = stars;
        this.imageFolder = imageFolder;
        this.position = position;
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", imageFolder='" + imageFolder + '\'' +
                ", position=" + position +
                ", rooms=" + rooms +
                '}';
    }

    public HotelEntities.Hotel buildGRPC() {
        return HotelEntities.Hotel.newBuilder()
                .setId(this.getId())
                .setName(this.getName())
                .setStars(this.getStars())
                .setImagesFolder(this.getImageFolder())
                .setPosition(this.position.buildRPC())
                .addAllRooms(rooms.stream().map(Room::buildGRPC).collect(Collectors.toList()))
                .build();
    }
}