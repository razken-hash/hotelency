package org.example.functions;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.exceptions.ReservationException;
import org.example.models.Hotel;
import org.example.models.Reservation;
import org.example.models.ReservationTicket;
import org.example.models.Room;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class MainFunctions {

    @SuppressWarnings("unused")
    public static Reservation makeReservation(BufferedReader reader, LocalDate dateIn, LocalDate dateOut, Room room) throws ReservationException {
        Reservation resa = null;
        try {
            System.out.println("Firstname: ");
            String firstName = reader.readLine();
            System.out.println("Name: ");
            String lastName = reader.readLine();
//            System.out.println("E-mail: ");
//            String mail = reader.readLine();
//            System.out.println("Phone number: ");
//            String phone = reader.readLine();
//            System.out.println("Card number: ");
//            String num = reader.readLine();
//            System.out.println("CVC number: ");
//            String cvv = reader.readLine();
//            System.out.println("Expiration date (yyyy-mm-dd): ");
//            LocalDate exp = LocalDate.parse(reader.readLine());
            resa = new Reservation(
                    firstName + " " + lastName,
                    dateIn,
                    dateOut,
                    room.getPrice(),
                    room
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resa;
    }


    public static void getRecipe(Hotel hotel, String client, Reservation resa) {
        int size = 31;
        System.out.println(Reservation.formRecipe(size, "Client Infos"));
        System.out.println("|                             |");
        System.out.println(Reservation.adaptiveDisplay("info", client, size));
        System.out.println("|                             |");
        System.out.println(Reservation.formRecipe(size, "Reservation Infos"));
        System.out.println("|                             |");
        System.out.println(Reservation.adaptiveDisplay("hotelName", hotel.getName(), size));
        System.out.println(Reservation.adaptiveDisplay("room", String.valueOf(resa.getRoom().getNumber()), size));
        System.out.println(Reservation.adaptiveDisplay("datein", String.valueOf(resa.getDateIn()), size));
        System.out.println(Reservation.adaptiveDisplay("dateout", String.valueOf(resa.getDateOut()), size));
        System.out.println(Reservation.adaptiveDisplay("price", String.valueOf(Double.parseDouble(new DecimalFormat("##.##").format(resa.getRoom().getPrice()))) + "€", size));
        System.out.println(Reservation.formRecipe(size, "footer"));
        System.out.println("Reservation ajoutee \n");
    }

    public static void makePdf(Hotel hotel, String client, Reservation resa) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(ReservationTicket.FILE));
            document.open();
            ReservationTicket.addMetaData(document);
            ReservationTicket.addTitlePage(document, hotel, client, resa);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
