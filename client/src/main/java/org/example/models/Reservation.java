package org.example.models;

import com.example.grpc.Basics;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.grpc.HotelEntities;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    private Integer id;
    private String client;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private Double amount;
    private Room room;
    private Integer hotelId;
    private Integer roomId;

    public Reservation(Integer id, String client, LocalDate dateIn, LocalDate dateOut, Double amount) {
        this.id = id;
        this.client = client;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.amount = amount;
    }

    public Reservation(String client, LocalDate dateIn, LocalDate dateOut, Double amount, Room room) {
        this.client = client;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.amount = amount;
        this.room = room;
    }

    static public Reservation fromGRPC(HotelEntities.Reservation grpcReservation) {
        return new Reservation(
                grpcReservation.getId(),
                grpcReservation.getClient(),
                LocalDate.of(grpcReservation.getDateIn().getYear(), grpcReservation.getDateIn().getMonth(), grpcReservation.getDateIn().getDay()),
                LocalDate.of(grpcReservation.getDateOut().getYear(), grpcReservation.getDateOut().getMonth(), grpcReservation.getDateOut().getDay()),
                grpcReservation.getAmount()
        );
    }

    public static String formRecipe(int size, String element) {
        String display = "";
        String firstLine = "+";
        String middleLine = "|";
        int wSpace = size - 2;

        if (element.equals("footer")) {
            display = "+";
            for (int i = 0; i < wSpace; i++) {
                display = display + "-";
            }
            return display + "+" + "\n";
        }

        for (int i = 0; i < wSpace; i++) {
            firstLine = firstLine + "-";
        }
        firstLine = firstLine + "+";
        String endLine = firstLine;
        firstLine = firstLine + "\n";
        wSpace = wSpace - element.length();
        int edges = 0;

        if (wSpace % 2 == 0) {
            edges = wSpace / 2;
            String separators = "";
            for (int k = 0; k < edges; k++) {
                separators = separators + " ";
            }
            middleLine = middleLine + separators + element + separators + "|" + "\n";
        } else if (wSpace % 2 != 0) {
            edges = wSpace / 2;
            String separators = "";
            for (int k = 0; k < edges; k++) {
                separators = separators + " ";
            }
            middleLine = middleLine + separators + " " + element + separators + "|" + "\n";
        }

        display = firstLine + middleLine + endLine;
        return display;
    }

    public static String adaptiveDisplay(String choice, String element, int size) {
        int wSpace = 0;
        String startLine = "| ";

        if (choice.equals("hotelName")) {
            String endLine = "";
            startLine = startLine + "Hotel : ";
            wSpace = (size - 11) - element.length();
            for (int i = 0; i < wSpace; i++) {
                endLine = endLine + " ";
            }
            endLine = endLine + "|";
            String display = startLine + element + endLine;
            return display;
        } else if (choice.equals("room")) {
            String endLine = "";
            startLine = startLine + "Room : ";
            wSpace = (size - 10) - element.length();
            for (int i = 0; i < wSpace; i++) {
                endLine = endLine + " ";
            }
            endLine = endLine + "|";
            String display = startLine + element + endLine;
            return display;
        } else if (choice.equals("datein")) {
            String endLine = "";
            startLine = startLine + "Arrivee : ";
            wSpace = (size - 18) - element.length();
            for (int i = 0; i < wSpace; i++) {
                endLine = endLine + " ";
            }
            endLine = endLine + "|";
            String display = startLine + element + endLine;
            return display;
        } else if (choice.equals("dateout")) {
            String endLine = "";
            startLine = startLine + "Depart : ";
            wSpace = (size - 20) - element.length();
            for (int i = 0; i < wSpace; i++) {
                endLine = endLine + " ";
            }
            endLine = endLine + "|";
            String display = startLine + element + endLine;
            return display;
        } else if (choice.equals("price")) {
            String endLine = "";
            startLine = startLine + "Price : ";
            wSpace = (size - 11) - element.length();
            for (int i = 0; i < wSpace; i++) {
                endLine = endLine + " ";
            }
            endLine = endLine + "|";
            String display = startLine + element + endLine;
            return display;
        } else if (choice.equals("info")) {
            wSpace = (size - 3) - element.length();
            String endLine = "";
            for (int i = 0; i < wSpace; i++) {
                endLine = endLine + " ";
            }
            endLine = endLine + "|";
            String display = startLine + element + endLine;
            return display;
        }
        return null;
    }

    public HotelEntities.Reservation buildGRPC() {
        HotelEntities.Reservation.Builder reservationBuilder = HotelEntities.Reservation.newBuilder()
                .setClient(this.client)
                .setAmount(this.amount)
                .setDateIn(
                        Basics.Date.newBuilder()
                                .setYear(this.dateIn.getYear())
                                .setMonth(this.dateIn.getMonth().getValue())
                                .setDay(this.dateIn.getDayOfMonth())
                                .build()
                )
                .setDateOut(
                        Basics.Date.newBuilder()
                                .setYear(this.dateOut.getYear())
                                .setMonth(this.dateOut.getMonth().getValue())
                                .setDay(this.dateOut.getDayOfMonth())
                                .build()
                )
                ;
        if (this.id != null) {
            reservationBuilder.setId(this.id);
        }
        if (this.hotelId != null) {
            reservationBuilder.setId(this.hotelId);
        }
        if (this.roomId != null) {
            reservationBuilder.setId(this.roomId);
        }
        if (this.room != null) {
            reservationBuilder.setRoom(room.buildGRPC());
        }
        return reservationBuilder.build();
    }
}
