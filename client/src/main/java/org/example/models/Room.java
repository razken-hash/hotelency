package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.grpc.HotelEntities;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {
    private Integer id;
    private Integer number;
    private Integer size;
    private Double price;
    private Hotel hotel;
    private List<Reservation> reservations;

    public Room(Integer number, Integer size, Double price, Hotel hotel, List<Reservation> reservations) {
        this.number = number;
        this.size = size;
        this.price = price;
        this.hotel = hotel;
        this.reservations = reservations;
    }

    public Room(Integer id, Integer number, Integer size, Double price, List<Reservation> reservations) {
        this.id = id;
        this.number = number;
        this.size = size;
        this.price = price;
        this.reservations = reservations;
    }

    static public Room fromGRPC(HotelEntities.Room grpcRoom) {
        return new Room(
                grpcRoom.getId(),
                grpcRoom.getNumber(),
                grpcRoom.getSize(),
                grpcRoom.getPrice(),
                grpcRoom.getReservationsList()
                        .stream()
                        .map(Reservation::fromGRPC)
                        .collect(Collectors.toList()));
    }

    public HotelEntities.Room buildGRPC() {
        HotelEntities.Room.Builder roomBuilder =  HotelEntities.Room.newBuilder().setId(this.id).setNumber(this.number).setSize(this.size).setPrice(this.price).addAllReservations(reservations.stream().map(Reservation::buildGRPC).collect(Collectors.toList()));
//        if (this.hotel != null) {
//            roomBuilder.setHotel(hotel.buildGRPC());
//        }
        return roomBuilder.build();
    }

    public void displayRoom() {
        System.out.println("\nROOM [" +
                "\n\tNumber=" + number +
                "\n\tSize=" + size +
                "\n\tPrice=" + price +
                "\n\tHotel=" + hotel +
                "\n]");
    }

    public String shortDisplayRoom() {
        return "\tRoom " + number +
                ", Size = " + size +
                ", Price = " + price;
    }
}
