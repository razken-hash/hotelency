package org.example.config;

import org.example.models.Hotel;
import org.example.models.Position;
import org.example.models.Reservation;
import org.example.models.Room;
import org.example.repositories.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class HotelConfig {
    @Bean
    public CommandLineRunner initDatabase(HotelRepository repository) {
        return args -> {
            Hotel hotelA = new Hotel("Hotel A", 4, "", null);

            List<Reservation> reservations = new ArrayList<Reservation>();

            List<Room> rooms = new ArrayList<>(List.of(
                    new Room(1, 2, 100.0, hotelA, reservations),
                    new Room(2, 3, 130.0, hotelA, reservations),
                    new Room(3, 1, 60.0, hotelA, reservations),
                    new Room(4, 4, 150.0, hotelA, reservations)
            ));

            Position position = new Position("Montpelier", "France", "SERGE CLAUDE", 1, "12.123, 123.2423", hotelA);

            hotelA.setPosition(position);
            hotelA.setRooms(rooms);

            repository.save(hotelA);
        };
    }
}
