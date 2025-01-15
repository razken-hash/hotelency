package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.grpc.AgencyEntities;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Agency {
    private Integer id;
    private String name;
    private List<Offer> offers;

    public Agency(String name) {
        this.name = name;
    }

    public Agency(String name, List<Offer> offers) {
        this.name = name;
        this.offers = offers;
    }

    static public Agency fromGRPC(AgencyEntities.Agency grpcAgency) {
        return new Agency(
                grpcAgency.getId(),
                grpcAgency.getName(),
                grpcAgency.getOffersList().stream().map(Offer::fromGRPC).collect(Collectors.toList())
        );
    }

    public AgencyEntities.Agency buildGRPC() {
        return AgencyEntities.Agency.newBuilder()
                .setId(this.getId())
                .setName(this.getName())
                .addAllOffers(offers.stream().map(Offer::buildGRPC).collect(Collectors.toList()))
                .build();
    }

    @Override
    public String toString() {
        return id + ". " + name + " AGENCY";
    }
}
