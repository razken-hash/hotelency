package org.example.models;

import lombok.*;
import org.example.grpc.HotelEntities;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Hotel {
    private Integer id;
    private String name;
    private Integer stars;
    private String imageFolder;
    private Position position;
    private List<Room> rooms;
    private Integer agencyPort;
    private Integer sequentialId;

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
    public Hotel(Integer id,String name, Integer stars, String imageFolder, Position position, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.stars = stars;
        this.imageFolder = imageFolder;
        this.position = position;
        this.rooms = rooms;
    }

    static public Hotel fromGRPC(HotelEntities.Hotel grpcHotel) {
        return new Hotel(
                grpcHotel.getId(),
                grpcHotel.getName(),
                grpcHotel.getStars(),
                grpcHotel.getImagesFolder(),
                Position.fromGRPC(grpcHotel.getPosition()),
                grpcHotel.getRoomsList().stream().map(Room::fromGRPC).collect(Collectors.toList())
        );
    }

    public HotelEntities.Hotel buildGRPC() {
        HotelEntities.Hotel hotel = HotelEntities.Hotel.newBuilder()
                .setId(this.getId())
                .setName(this.getName())
                .setStars(this.getStars())
                .setImagesFolder(this.getImageFolder())
                .setPosition(this.position.buildRPC())
                .addAllRooms(rooms.stream().map(Room::buildGRPC).collect(Collectors.toList()))
                .build();
        return hotel;
    }

    public void displayFullHotelDetails() {
        StringBuilder roomsDisplaying = new StringBuilder();
        int i = 0;
        for (Room room : this.getRooms()) {
            i++;
            roomsDisplaying.append("\n\t\t" + i + ". ").append(room.shortDisplayRoom());
        }
        System.out.println(sequentialId + ". Hotel [" +
                "\n\tName= " + name +
                " (" + stars + " Stars) " +
                "Located At " + position.displayPosition() +
                "\n\tRooms = [" + roomsDisplaying.toString() + "\n\t]"+
                "\n]");
    }
}