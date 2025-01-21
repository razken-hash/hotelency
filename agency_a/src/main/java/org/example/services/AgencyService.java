package org.example.services;

import com.example.grpc.Basics;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.AgencyEntities;
import org.example.grpc.AgencyServiceGrpc;
import org.example.grpc.HotelEntities;
import org.example.grpc.HotelServiceGrpc;
import org.example.models.Agency;
import org.example.models.Offer;
import org.example.repositories.AgencyRepository;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class AgencyService extends AgencyServiceGrpc.AgencyServiceImplBase {

    final private AgencyRepository agencyRepository;

    public AgencyService(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public void getAgencyById(Basics.IntegerId request, StreamObserver<AgencyEntities.Agency> responseObserver) {
        Agency agency = agencyRepository.findById(request.getId()).orElseThrow();
        responseObserver.onNext(agency.buildGRPC());
        responseObserver.onCompleted();
    }

    @Override
    public void searchRoom(HotelEntities.SearchRoomMessage request, StreamObserver<HotelEntities.ManyHotel> responseObserver) {
        Agency agency = agencyRepository.findById(1).orElseThrow();

        List<HotelEntities.Hotel> availableHotels = new ArrayList<HotelEntities.Hotel>();

        for (Offer offer : agency.getOffers()) {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", offer.getHotelPort()).usePlaintext() // Disable encryption (useful for local testing)
                    .build();

            // Create a stub using the generated gRPC class
            HotelServiceGrpc.HotelServiceBlockingStub stub = HotelServiceGrpc.newBlockingStub(channel);

            // Make the gRPC call
            HotelEntities.Hotel hotelResponse = stub.searchRoom(request);

            if (hotelResponse != null) {
                availableHotels.add(hotelResponse);
            }

            // Shut down the channel
            channel.shutdown();
        }

        HotelEntities.ManyHotel manyHotel = HotelEntities.ManyHotel.newBuilder().addAllHotels(availableHotels).build();

        responseObserver.onNext(manyHotel);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllHotels(Basics.EmptyMessage request, StreamObserver<HotelEntities.ManyHotel> responseObserver) {
        Agency agency = agencyRepository.findById(1).orElseThrow();

        List<HotelEntities.Hotel> allHotels = new ArrayList<HotelEntities.Hotel>();

        for (Offer offer : agency.getOffers()) {

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", offer.getHotelPort()).usePlaintext() // Disable encryption (useful for local testing)
                    .build();

            HotelServiceGrpc.HotelServiceBlockingStub stub = HotelServiceGrpc.newBlockingStub(channel);

            HotelEntities.Hotel hotelResponse = stub.getHotelById(Basics.IntegerId.newBuilder().setId(offer.getHotelId()).build());

            if (hotelResponse != null) {
                allHotels.add(hotelResponse);
            }

            channel.shutdown();
        }


        HotelEntities.ManyHotel manyHotel = HotelEntities.ManyHotel.newBuilder().addAllHotels(allHotels).build();

        responseObserver.onNext(manyHotel);
        responseObserver.onCompleted();
    }


    @Override
    public void makeReservation(HotelEntities.Reservation request, StreamObserver<HotelEntities.Reservation> responseObserver) {
        System.out.println("LLLLL");
        System.out.println("ID" + request.getHotelId());
        System.out.println("ROOM ID" + request.getRoomId());
        System.out.println("HHHHHHH");
        Agency agency = agencyRepository.findById(1).orElseThrow();
        System.out.println("HHHHHHH");

        System.out.println(agency.getOffers());
        Integer selectedPort = agency.getOffers().stream()
                .filter(offer -> offer.getHotelId() == request.getHotelId())
                .findFirst().get().getHotelPort();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", selectedPort).usePlaintext() // Disable encryption (useful for local testing)
                .build();

        // Create a stub using the generated gRPC class
        HotelServiceGrpc.HotelServiceBlockingStub stub = HotelServiceGrpc.newBlockingStub(channel);

        // Make the gRPC call
        HotelEntities.Reservation reservation = stub.makeReservation(request);
        System.out.println("RES");
        System.out.println(reservation.getId());
        // Shut down the channel
        channel.shutdown();

        responseObserver.onNext(reservation);
        responseObserver.onCompleted();
    }
}
