package org.example.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.grpc.HotelServiceOuterClass;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
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

    public HotelServiceOuterClass.Hotel buildGRPC() {
        System.out.println("HOTEL");
        HotelServiceOuterClass.Hotel hotel = HotelServiceOuterClass.Hotel.newBuilder()
                .setId(this.getId())
                .setName(this.getName())
                .setStars(this.getStars())
                .setImagesFolder(this.getImageFolder())
                .setPosition(this.position.buildRPC())
                .addAllRooms(rooms.stream().map(Room::buildGRPC).collect(Collectors.toList()))
                .build();
        System.out.println("HOTEL");
        return hotel;
    }
}