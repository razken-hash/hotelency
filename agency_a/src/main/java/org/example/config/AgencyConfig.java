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
            Offer o1 = new Offer(1, 8001, 10D, agency);
//            Offer o2 = new Offer(1, 8006, 15D, agency);
//            Offer o3 = new Offer(3, 8003, 05D, agency);

            offers.add(o1);
//            offers.add(o2);
//            offers.add(o3);

            agency.setOffers(offers);

            repository.save(agency);
        };
    }
}
