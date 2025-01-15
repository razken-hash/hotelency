package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.grpc.AgencyEntities;
import org.example.grpc.AgencyServiceOuterClass;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    @OneToMany(mappedBy="agency", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Offer> offers;

    public Agency(String name) {
        this.name = name;
    }

    public Agency(String name, List<Offer> offers) {
        this.name = name;
        this.offers = offers;
    }

    public AgencyEntities.Agency buildGRPC() {
        return AgencyEntities.Agency.newBuilder()
                .setId(this.getId())
                .setName(this.getName())
                .addAllOffers(offers.stream().map(Offer::buildGRPC).collect(Collectors.toList()))
                .build();
    }
}
