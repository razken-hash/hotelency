package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.grpc.AgencyEntities;
import org.example.grpc.AgencyServiceOuterClass;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Integer hotelId;
    private String hotelUri;
    private Double amount;
    @ManyToOne
    @JoinColumn(name="agency_id")
    private Agency agency;

    public Offer(Integer hotelId, String hotelUri, Double amount, Agency agency) {
        this.hotelId = hotelId;
        this.hotelUri = hotelUri;
        this.amount = amount;
        this.agency = agency;
    }

    public AgencyEntities.Offer buildGRPC() {
        return AgencyEntities.Offer.newBuilder()
                .setId(this.getId())
                .setHotelId(this.getHotelId())
                .setHotelUri(this.getHotelUri())
                .setAmount(this.getAmount())
                .build();
    }
}
