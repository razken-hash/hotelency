package org.example.services;

import com.example.grpc.Basics;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.grpc.AgencyEntities;
import org.example.grpc.AgencyServiceGrpc;
import org.example.grpc.HotelEntities;
import org.example.models.Agency;
import org.example.models.Hotel;
import org.example.models.Reservation;
import org.example.models.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientService {
    static public Map<String, Integer> agenciesPorts = new HashMap<>() {{
        put("Agency A", 7001);
    }};
    static List<Agency> agencies = new ArrayList<Agency>();

    static List<Hotel> searchedHotels = new ArrayList<Hotel>();

    static List<Hotel> allHotels = new ArrayList<Hotel>();


    static public List<Agency> getAllAgencies() {

        if (!agencies.isEmpty()) {
            return agencies;
        }

        for (Integer agencyPort : agenciesPorts.values()) {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", agencyPort).usePlaintext() // Disable encryption (useful for local testing)
                    .build();

            AgencyServiceGrpc.AgencyServiceBlockingStub stub = AgencyServiceGrpc.newBlockingStub(channel);

            AgencyEntities.Agency agencyResponse = stub.getAgencyById(Basics.IntegerId.newBuilder().setId(1).build());

            if (agencyResponse != null) {
                agencies.add(Agency.fromGRPC(agencyResponse));
            }

            channel.shutdown();
        }
        return agencies;
    }

    static public List<Hotel> getAllHotels() {
        if (!allHotels.isEmpty()) return allHotels;

        for (Integer agencyPort : agenciesPorts.values()) {

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", agencyPort).usePlaintext() // Disable encryption (useful for local testing)
                    .build();

            AgencyServiceGrpc.AgencyServiceBlockingStub stub = AgencyServiceGrpc.newBlockingStub(channel);

            HotelEntities.ManyHotel agencyHotels = stub.getAllHotels(Basics.EmptyMessage.newBuilder().build());
            if (agencyHotels != null) {
                for (HotelEntities.Hotel grpcHotel : agencyHotels.getHotelsList()) {
                    Hotel hotel = Hotel.fromGRPC(grpcHotel);
                    hotel.setSequentialId(allHotels.size() + 1);
                    hotel.setAgencyPort(agencyPort);
                    allHotels.add(hotel);
                }
            }
            channel.shutdown();
        }


        return allHotels;
    }

    static public Room selectBestRoom() {
        if (!searchedHotels.isEmpty()) {
            Room selectedRoom = searchedHotels.get(0).getRooms().get(0);
            for (Hotel hotel : searchedHotels) {
                for (Room room : hotel.getRooms())
                    if (room.getPrice() < selectedRoom.getPrice()) {
                        selectedRoom = room;
                        selectedRoom.setHotel(hotel);
                    }
            }
            return selectedRoom;
        }
        return null;
    }

    static public List<Hotel> searchRooms(String position, Integer rating, LocalDate dateIn, LocalDate dateOut, Integer size, Double price) {

        for (Integer agencyPort : agenciesPorts.values()) {

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", agencyPort).usePlaintext() // Disable encryption (useful for local testing)
                    .build();

            AgencyServiceGrpc.AgencyServiceBlockingStub stub = AgencyServiceGrpc.newBlockingStub(channel);

            HotelEntities.SearchRoomMessage searchRoomQuery = HotelEntities.SearchRoomMessage
                    .newBuilder()
                    .setRating(rating)
                    .setPosition(position)
                    .setPrice(price)
                    .setSize(size)
                    .setDateIn(
                            Basics.Date
                                    .newBuilder()
                                    .setYear(dateIn.getYear())
                                    .setMonth(dateIn.getMonthValue())
                                    .setDay(dateIn.getDayOfMonth())
                                    .build())
                    .setDateOut(
                            Basics.Date
                                    .newBuilder()
                                    .setYear(dateOut.getYear())
                                    .setMonth(dateOut.getMonthValue())
                                    .setDay(dateOut.getDayOfMonth())
                                    .build())
                    .build();

            HotelEntities.ManyHotel agencyHotels = stub.searchRoom(searchRoomQuery);
            for (HotelEntities.Hotel grpcHotel : agencyHotels.getHotelsList()) {
                Hotel hotel = Hotel.fromGRPC(grpcHotel);
                hotel.setId(searchedHotels.size() + 1);
                hotel.setAgencyPort(agencyPort);
                searchedHotels.add(hotel);
            }
            channel.shutdown();
        }
        return searchedHotels;
    }

    static public Reservation makeReservation(
            Reservation reservation
    ) {
        System.out.println("1");
        Integer agencyPort = getAllHotels().stream().filter(hotel -> hotel.getId() == reservation.getHotelId()).findFirst().get().getAgencyPort();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", agencyPort).usePlaintext() // Disable encryption (useful for local testing)
                .build();

        AgencyServiceGrpc.AgencyServiceBlockingStub stub = AgencyServiceGrpc.newBlockingStub(channel);

        HotelEntities.Reservation newReservation = stub.makeReservation(reservation.buildGRPC());

        if (newReservation != null) {
            return Reservation.fromGRPC(newReservation);
        } else {
            return null;
        }

    }
}
