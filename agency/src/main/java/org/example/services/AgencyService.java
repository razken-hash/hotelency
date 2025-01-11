package org.example.services;

import com.example.grpc.Basics;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.*;
import org.example.models.Agency;
import org.example.models.Hotel;
import org.example.repositories.AgencyRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

@GrpcService
public class AgencyService extends AgencyServiceGrpc.AgencyServiceImplBase {

    final private AgencyRepository agencyRepository;

    public AgencyService(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public void getAgencyById(Basics.IntegerId request, StreamObserver<AgencyEntities.Agency> responseObserver) {
        System.out.println("ABDERRAZAK");
        Agency agency = agencyRepository.findById(request.getId()).orElseThrow();
        responseObserver.onNext(agency.buildGRPC());
        responseObserver.onCompleted();
    }

    @Override
    public void searchRoom(HotelEntities.SearchRoomMessage request, StreamObserver<HotelEntities.ManyHotel> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8000)
                .usePlaintext() // Disable encryption (useful for local testing)
                .build();

        // Create a stub using the generated gRPC class
        HotelServiceGrpc.HotelServiceBlockingStub stub = HotelServiceGrpc.newBlockingStub(channel);

        // Prepare the request

        // Make the gRPC call
        HotelEntities.Hotel hotelResponse = stub.searchRoom(request);

        // Shut down the channel
        channel.shutdown();
        responseObserver.onNext(HotelEntities.ManyHotel.newBuilder().addAllHotels(new ArrayList<>(Collections.singleton(hotelResponse))).build());
        responseObserver.onCompleted();
    }
}
