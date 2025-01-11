package org.example.config;

import org.example.models.*;
import org.example.repositories.AgencyRepository;
import org.example.services.AgencyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AgencyConfig {
    @Bean
    public CommandLineRunner initDatabase(AgencyRepository repository) {
        return args -> {
            Agency agency = new Agency("RAKUTEN");
            List<Offer> offers = new ArrayList<Offer>();
            Offer o1 = new Offer(1, "http://localhost:8001/hotel1/api/hotels", 10D, agency);
            Offer o2 = new Offer(2, "http://localhost:8002/hotel2/api/hotels", 15D, agency);
            Offer o3 = new Offer(3, "http://localhost:8003/hotel3/api/hotels", 05D, agency);

            offers.add(o1);
            offers.add(o2);
            offers.add(o3);

            agency.setOffers(offers);

            repository.save(agency);
        };
    }
}
