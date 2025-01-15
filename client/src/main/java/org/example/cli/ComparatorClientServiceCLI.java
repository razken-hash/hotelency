package org.example.cli;

import org.example.exceptions.ReservationException;
import org.example.functions.MainFunctions;
import org.example.models.Agency;
import org.example.models.Hotel;
import org.example.models.Reservation;
import org.example.models.Room;
import org.example.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ComparatorClientServiceCLI extends AbstractMain implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        BufferedReader inputReader;
        String userInput = "";
        try {
            inputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Choisir l'interface ?\n1. GUI\n2. CLI");
            int choice = Integer.parseInt(inputReader.readLine());
            if (choice == 1) {
                System.out.println("GUI");
//                EventQueue.invokeLater(new Runnable() {
//                    public void run() {
//                        try {
//                            ClientGUI frame = new ClientGUI(proxy);
//                            frame.setVisible(true);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
            } else {
                do {
                    menu();
                    userInput = inputReader.readLine();
                    processUserInput(inputReader, userInput);

                } while (!userInput.equals(QUIT));
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void menu() {
        StringBuilder builder = new StringBuilder();
        builder.append(QUIT + ". Quit.");
        builder.append("\n1. Afficher les hotels");
        builder.append("\n2. Recherche");
        builder.append("\n3. Afficher les agences");
        System.out.println(builder);
    }

    private void processUserInput(BufferedReader reader, String userInput) {
        Map<String, String> params = new HashMap<>();
        try {
            switch (userInput) {
                case "1":
                    List<Hotel> hotels = ClientService.getAllHotels();
                    for (Hotel hotel : hotels) {
                        hotel.displayFullHotelDetails();
                    }
                    break;

                case "2":
//                    System.out.println("Choisir destination ? (City or country)\n");
//                    String position = reader.readLine();
//                    System.out.println("\nNombre Etoile: ");
//                    Integer rating = Integer.parseInt(reader.readLine());
//                    System.out.println("\nPrice: ");
//                    Double price = Double.parseDouble(reader.readLine());
//                    System.out.println("\nDate Entree (yyyy-mm-dd): ");
//                    String stringDateIn = reader.readLine();
//                    LocalDate dateIn = LocalDate.parse(stringDateIn);
//                    System.out.println("\nDate Sortie (yyyy-mm-dd): ");
//                    String stringDateOut = reader.readLine();
//                    LocalDate dateOut = LocalDate.parse(stringDateOut);
//                    System.out.println("\nNombre de personnes: ");
//                    int size = Integer.parseInt(reader.readLine());

//                    List<Hotel> searchedHotels = ClientService.searchRooms(
//                            position, rating, dateIn, dateOut, size, price
//                    );
                    List<Hotel> searchedHotels = ClientService.searchRooms(
                            "France", 2,  LocalDate.of(2025, 12, 12), LocalDate.of(2025, 12, 20), 2, 200D
                    );

                    System.out.println("RESULTS:");
                    for (Hotel hotel : searchedHotels) {
                        hotel.displayFullHotelDetails();
                    }

                    System.out.println("BEST OFFER: ");

                    ClientService.selectBestRoom().displayRoom();

                    System.out.println("Choose \n");

                    int hotelChoice = -1;
                    int roomChoice = -1;
                    while (hotelChoice == -1) {
                        System.out.println("Enter number (0 to exit): ");
                        hotelChoice = Integer.parseInt(reader.readLine());
                        if (hotelChoice == 0) {
                            break;
                        } else if (hotelChoice > searchedHotels.size() || hotelChoice <= -1) {
                            System.err.println("Choose available !");
                            hotelChoice = -1;
                        } else {
                            Hotel selectedHotel = searchedHotels.get(hotelChoice - 1);
                            selectedHotel.displayFullHotelDetails();
                            System.out.println("Room number : ");
                            roomChoice = Integer.parseInt(reader.readLine());
                            while (roomChoice == -1) {
                                System.out.println("Enter number (0 to exit): ");
                                roomChoice = Integer.parseInt(reader.readLine());
                                if (roomChoice == 0) {
                                    break;
                                } else if (roomChoice > selectedHotel.getRooms().size() || roomChoice <= -1) {
                                    System.err.println("Choose available !");
                                    hotelChoice = -1;
                                } else {
                                    selectedHotel.displayFullHotelDetails();
                                    System.out.println("Room number : ");
                                    roomChoice = Integer.parseInt(reader.readLine());
                                }
                            }
                        }
                    }


                    if (hotelChoice != 0 && roomChoice != 0) {
                        try {
                            Hotel selectedHotel = searchedHotels.get(hotelChoice - 1);
                            Room selectedRoom = selectedHotel.getRooms().get(roomChoice - 1);

//                            selectedRoom.setHotel(selectedHotel);

                            Reservation reservation = MainFunctions.makeReservation(reader, LocalDate.of(2025, 12, 12), LocalDate.of(2025, 12, 20), selectedRoom);
                            reservation.setHotelId(selectedHotel.getId());
                            reservation.setRoomId(selectedHotel.getId());
                            ClientService.makeReservation(reservation);

                            System.out.println("Thank you !\n");
                            MainFunctions.getRecipe(selectedHotel, reservation.getClient(), reservation);
                            MainFunctions.makePdf(selectedHotel, reservation.getClient(), reservation);

                        } catch (ReservationException e) {
                            e.printStackTrace();
                            break;
                        }
                    }

                    break;
                case "3":
                    System.out.println("Agencies:");
                    List<Agency> agencies = ClientService.getAllAgencies();
                    for (Agency agency : agencies) {
                        try {
                            agencies.forEach(System.out::println);
                        } catch (Exception e) {
                            continue;
                        }
                        System.out.println();
                    }
                    break;

                case QUIT:
                    return;
                default:
                    System.err.println("Error. Try again: ");
                    return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpClientErrorException e) {
            System.err.println(e.getMessage());
        }
    }
}
