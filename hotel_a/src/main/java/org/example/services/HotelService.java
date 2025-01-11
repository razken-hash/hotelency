package org.example.services;

import com.example.grpc.Basics;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.HotelEntities;
import org.example.grpc.HotelServiceGrpc;
import org.example.grpc.HotelServiceOuterClass;
import org.example.models.Hotel;
import org.example.models.Reservation;
import org.example.models.Room;
import org.example.repositories.HotelRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@GrpcService
public class HotelService extends HotelServiceGrpc.HotelServiceImplBase {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    @Override
    public void getAllHotels(Basics.EmptyMessage request, StreamObserver<HotelEntities.ManyHotel> responseObserver) {
        Logger logger = Logger.getLogger(HotelService.class.getName());
        logger.info("Received request to fetch all hotels.");
        try {

            List<Hotel> allHotels = hotelRepository.findAll();

            if (allHotels.isEmpty()) {
                responseObserver.onError(Status.NOT_FOUND
                        .withDescription("No hotels found in the database.")
                        .asRuntimeException());
                return;
            }

            List<HotelEntities.Hotel> grpcHotels = new ArrayList<>();

            allHotels.forEach(hotel -> grpcHotels.add(hotel.buildGRPC()));

            HotelEntities.ManyHotel manyHotels = HotelEntities.ManyHotel.newBuilder()
                    .addAllHotels(grpcHotels)
                    .build();

            responseObserver.onNext(manyHotels);
            responseObserver.onCompleted();
        } catch (Exception e) {
            System.out.println(e.toString());

            responseObserver.onError(Status.INTERNAL
                    .withDescription("An error occurred while fetching hotels.")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void getHotelById(Basics.IntegerId request, StreamObserver<HotelEntities.Hotel> responseObserver) {
        Hotel hotel = hotelRepository.findById(request.getId()).orElseThrow();
        HotelEntities.Hotel hotelGRPC = hotel.buildGRPC();
        responseObserver.onNext(hotelGRPC);
        responseObserver.onCompleted();
    }

    @Override
    public void searchRoom(HotelEntities.SearchRoomMessage request, StreamObserver<HotelEntities.Hotel> responseObserver) {

        Hotel hotel = hotelRepository.findById(1).orElseThrow();

        String requestedPosition = request.getPosition();
        int requestedSize = request.getSize();
        double requestedPrice = request.getPrice();

        int requestedRating = request.getRating();
        LocalDate requestedDateIn = LocalDate.of(request.getDateIn().getYear(), request.getDateIn().getMonth(), request.getDateIn().getDay());
        LocalDate requestedDateOut = LocalDate.of(request.getDateOut().getYear(), request.getDateOut().getMonth(), request.getDateOut().getDay());
        List<Room> availableRooms = new ArrayList<Room>();

        if ((hotel.getPosition().getCity().contains(requestedPosition) || hotel.getPosition().getStreet().contains(requestedPosition) || hotel.getPosition().getCountry().contains(requestedPosition)) && hotel.getStars() >= requestedRating) {

            for (Room room : hotel.getRooms()) {

                boolean isOkay = false;
                if (room.getPrice() <= requestedPrice && room.getSize() >= requestedSize) {
                    isOkay = true;
                    for (Reservation reservation : room.getReservations()) {
                        System.out.println(reservation.getId());

                        LocalDate reservationDateIn = LocalDate.of(reservation.getDateIn().getYear(), reservation.getDateIn().getMonth(), reservation.getDateIn().getDay());
                        LocalDate reservationDateOut = LocalDate.of(reservation.getDateOut().getYear(), reservation.getDateOut().getMonth(), reservation.getDateOut().getDay());
                        if (requestedDateIn.isAfter(reservationDateIn)
                                &&
                                requestedDateIn.isBefore(reservationDateOut)
                                ||
                                requestedDateOut.isAfter(reservationDateIn)
                                        &&
                                        requestedDateOut.isBefore(reservationDateOut)
                        ) {
                            isOkay = false;
                            continue;
                        }
                    }
                }
                if (isOkay) {
                    availableRooms.add(room);
                }
            }
        }
        if (!availableRooms.isEmpty()) {
            hotel.setRooms(availableRooms);
            responseObserver.onNext(hotel.buildGRPC());
            responseObserver.onCompleted();
        } else {
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
    }
}
