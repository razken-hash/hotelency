package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.grpc.AgencyEntities;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Offer {
    private Integer id;
    private Integer hotelId;
    private Integer hotelPort;
    private Double amount;
    private Agency agency;

    public Offer(Integer id, Integer hotelId, Integer hotelPort, Double amount) {
        this.id = id;
        this.hotelId = hotelId;
        this.hotelPort = hotelPort;
        this.amount = amount;
    }

    public Offer(Integer hotelId, Integer hotelPort, Double amount, Agency agency) {
        this.hotelId = hotelId;
        this.hotelPort = hotelPort;
        this.amount = amount;
        this.agency = agency;
    }

    static public Offer fromGRPC(AgencyEntities.Offer grpcOffer) {
        return new Offer(
                grpcOffer.getId(),
                grpcOffer.getHotelId(),
                grpcOffer.getHotelPort(),
                grpcOffer.getAmount()
        );
    }

    public AgencyEntities.Offer buildGRPC() {
        return AgencyEntities.Offer.newBuilder()
                .setId(this.getId())
                .setHotelId(this.getHotelId())
                .setHotelPort(this.getHotelPort())
                .setAmount(this.getAmount())
                .build();
    }
}
