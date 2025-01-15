package org.example.gui;

import org.example.functions.MainFunctions;
import org.example.models.Hotel;
import org.example.models.Reservation;
import org.example.models.Room;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class ClientGUI extends JFrame {

    private JPanel contentPane;
    private JTextField destinationInput;
    private JTextField personNumberInput;
    private JTextField priceSelectedMax;
    private JTextField dateOut;
    private JTextField dateIn;
    private JCheckBox AgencyCheck2;
    private JTextField reservationName;
    private JTextField reservationFirstName;
    private JTextField phoneNumberReservation;
    private JTextField cardNumberReservation;
    private JTextField cardCVVReservation;
    private JTextField cardExpirationReservation;


    public ClientGUI(RestTemplate proxy) {
        setResizable(false);

        setTitle("Comparateur");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1143, 743);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JComboBox hotelChoice = new JComboBox();
        hotelChoice.setMaximumRowCount(30);
        hotelChoice.setModel(new DefaultComboBoxModel(new String[]{}));
        hotelChoice.setVisible(false);

        JLabel clientInfos = new JLabel("");
        clientInfos.setHorizontalAlignment(SwingConstants.CENTER);
        clientInfos.setForeground(Color.BLACK);
        clientInfos.setFont(new Font("Tahoma", Font.BOLD, 20));
        clientInfos.setBounds(410, 344, 300, 24);
        contentPane.add(clientInfos);
        clientInfos.setVisible(false);

        JLabel reservedRoom = new JLabel("");
        reservedRoom.setHorizontalAlignment(SwingConstants.CENTER);
        reservedRoom.setForeground(Color.BLACK);
        reservedRoom.setFont(new Font("Tahoma", Font.BOLD, 20));
        reservedRoom.setBounds(410, 295, 300, 24);
        contentPane.add(reservedRoom);
        reservedRoom.setVisible(false);

        JLabel reservedHotel = new JLabel("");
        reservedHotel.setHorizontalAlignment(SwingConstants.CENTER);
        reservedHotel.setForeground(new Color(0, 0, 0));
        reservedHotel.setFont(new Font("Tahoma", Font.BOLD, 20));
        reservedHotel.setBounds(410, 243, 300, 24);
        contentPane.add(reservedHotel);
        reservedHotel.setVisible(false);

        JLabel roomNumber = new JLabel("");
        roomNumber.setEnabled(false);
        roomNumber.setBounds(984, 180, 45, 13);
        contentPane.add(roomNumber);

        JLabel hotelName = new JLabel("");
        hotelName.setEnabled(false);
        hotelName.setBounds(984, 156, 45, 13);
        contentPane.add(hotelName);

        phoneNumberReservation = new JTextField();
        phoneNumberReservation.setFont(new Font("Tahoma", Font.BOLD, 15));
        phoneNumberReservation.setBounds(573, 368, 173, 24);
        contentPane.add(phoneNumberReservation);
        phoneNumberReservation.setColumns(10);
        phoneNumberReservation.setVisible(false);
        phoneNumberReservation.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        cardNumberReservation = new JTextField();
        cardNumberReservation.setFont(new Font("Tahoma", Font.BOLD, 15));
        cardNumberReservation.setBounds(573, 308, 173, 24);
        contentPane.add(cardNumberReservation);
        cardNumberReservation.setColumns(10);
        cardNumberReservation.setVisible(false);
        cardNumberReservation.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        cardCVVReservation = new JTextField();
        cardCVVReservation.setFont(new Font("Tahoma", Font.BOLD, 15));
        cardCVVReservation.setColumns(10);
        cardCVVReservation.setBounds(573, 488, 173, 24);
        contentPane.add(cardCVVReservation);
        cardCVVReservation.setVisible(false);
        cardCVVReservation.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        cardExpirationReservation = new JTextField();
        cardExpirationReservation.setFont(new Font("Tahoma", Font.BOLD, 15));
        cardExpirationReservation.setColumns(10);
        cardExpirationReservation.setBounds(573, 428, 173, 24);
        contentPane.add(cardExpirationReservation);
        cardExpirationReservation.setVisible(false);
        cardExpirationReservation.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        JLabel cvvLabel = new JLabel("CVV");
        cvvLabel.setForeground(Color.BLACK);
        cvvLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        cvvLabel.setBounds(440, 490, 107, 24);
        contentPane.add(cvvLabel);
        cvvLabel.setVisible(false);

        JLabel cardExpirationLabel = new JLabel("Expiration");
        cardExpirationLabel.setForeground(Color.BLACK);
        cardExpirationLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        cardExpirationLabel.setBounds(420, 430, 107, 24);
        contentPane.add(cardExpirationLabel);
        cardExpirationLabel.setVisible(false);

        JLabel phoneNumberLabel = new JLabel("Téléphone");
        phoneNumberLabel.setForeground(Color.BLACK);
        phoneNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        phoneNumberLabel.setBounds(420, 310, 107, 24);
        contentPane.add(phoneNumberLabel);
        phoneNumberLabel.setVisible(false);

        JLabel cardNumberLabel = new JLabel("Numéro Carte");
        cardNumberLabel.setForeground(Color.BLACK);
        cardNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        cardNumberLabel.setBounds(400, 370, 150, 24);
        contentPane.add(cardNumberLabel);
        cardNumberLabel.setVisible(false);

        JLabel firstNameLabel = new JLabel("Prénom");
        firstNameLabel.setForeground(Color.BLACK);
        firstNameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        firstNameLabel.setBounds(430, 250, 107, 24);
        contentPane.add(firstNameLabel);
        firstNameLabel.setVisible(false);

        JLabel reservationNameLabel = new JLabel("Nom");
        reservationNameLabel.setForeground(new Color(0, 0, 0));
        reservationNameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        reservationNameLabel.setBounds(440, 192, 107, 24);
        contentPane.add(reservationNameLabel);
        reservationNameLabel.setVisible(false);

        JLabel cardNumberCover = new JLabel("");
        cardNumberCover.setBounds(560, 360, 200, 40);
        contentPane.add(cardNumberCover);
        //cardNumberCover.setIcon(new ImageIcon(inputFirstName));
        cardNumberCover.setVisible(false);

        JLabel cardCVVCover = new JLabel("");
        cardCVVCover.setBounds(560, 480, 200, 40);
        contentPane.add(cardCVVCover);
//		cardCVVCover.setIcon(new ImageIcon(inputFirstName));
        cardCVVCover.setVisible(false);

        JLabel cardExpirationCover = new JLabel("");
        cardExpirationCover.setBounds(560, 420, 200, 40);
        contentPane.add(cardExpirationCover);
//		cardExpirationCover.setIcon(new ImageIcon(inputFirstName));
        cardExpirationCover.setVisible(false);

        JLabel phoneCover = new JLabel("");
        phoneCover.setBounds(560, 300, 200, 40);
        contentPane.add(phoneCover);
        phoneCover.setVisible(false);

        JButton payBtn = new JButton("Confirm");
        payBtn.setBounds(500, 560, 148, 42);
        payBtn.setBackground(new Color(243, 254, 255));

        contentPane.add(payBtn);
        payBtn.setVisible(false);

        JLabel payBtnCover = new JLabel("");
        payBtnCover.setBounds(485, 560, 180, 40);
        contentPane.add(payBtnCover);
        payBtnCover.setVisible(false);

        reservationFirstName = new JTextField();
        reservationFirstName.setFont(new Font("Tahoma", Font.BOLD, 15));
        reservationFirstName.setColumns(10);
        reservationFirstName.setBounds(573, 248, 173, 24);
        contentPane.add(reservationFirstName);
        reservationFirstName.setVisible(false);
        reservationFirstName.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        reservationName = new JTextField();
        reservationName.setFont(new Font("Tahoma", Font.BOLD, 15));
        reservationName.setBounds(573, 189, 173, 24);
        contentPane.add(reservationName);
        reservationName.setColumns(10);
        reservationName.setVisible(false);
        reservationName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        JLabel firstNameCover = new JLabel("");
        firstNameCover.setBounds(560, 180, 200, 40);
        contentPane.add(firstNameCover);
        firstNameCover.setVisible(false);
//		firstNameCover.setIcon(new ImageIcon(inputFirstName));

        JLabel nameCover = new JLabel("");
        nameCover.setBounds(560, 240, 200, 40);
        contentPane.add(nameCover);
        nameCover.setVisible(false);
        //	nameCover.setIcon(new ImageIcon(inputFirstName));
        hotelChoice.setBounds(103, 236, 269, 27);
        contentPane.add(hotelChoice);

        JComboBox roomChoice = new JComboBox();
        roomChoice.setMaximumRowCount(30);
        roomChoice.setVisible(false);
        roomChoice.setBounds(103, 322, 269, 27);
        contentPane.add(roomChoice);

        JComboBox starsSelector = new JComboBox();
        starsSelector.setBounds(580, 320, 148, 24);
        starsSelector.setForeground(new Color(64, 0, 64));
        starsSelector.setModel(new DefaultComboBoxModel(new String[]{"1 étoile (★)", "2 étoiles (★★)", "3 étoiles (★★★)", "4 étoiles (★★★★)", "5 étoiles (★★★★★)"}));

        JLabel chooseAgencyLabel = new JLabel("Choisir agence :");
        chooseAgencyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chooseAgencyLabel.setForeground(Color.BLACK);
        chooseAgencyLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        chooseAgencyLabel.setBounds(450, 89, 240, 42);
        contentPane.add(chooseAgencyLabel);

        JLabel bestRateAgence = new JLabel("Agence 2");
        bestRateAgence.setForeground(new Color(0, 0, 0));
        bestRateAgence.setFont(new Font("Tahoma", Font.BOLD, 18));
        bestRateAgence.setBounds(818, 84, 193, 22);
        bestRateAgence.setVisible(false);

        JLabel bestPriceAgence = new JLabel("Agence 3");
        bestPriceAgence.setForeground(new Color(0, 0, 0));
        bestPriceAgence.setFont(new Font("Tahoma", Font.BOLD, 18));
        bestPriceAgence.setBounds(274, 85, 176, 21);
        bestPriceAgence.setVisible(false);

        JLabel bestPricePrix = new JLabel("10");
        bestPricePrix.setForeground(new Color(0, 0, 0));
        bestPricePrix.setFont(new Font("Tahoma", Font.BOLD, 14));
        bestPricePrix.setBounds(474, 46, 68, 27);
        bestPricePrix.setVisible(false);

        JLabel bestRateStars = new JLabel("4");
        bestRateStars.setForeground(new Color(0, 0, 0));
        bestRateStars.setFont(new Font("Tahoma", Font.BOLD, 14));
        bestRateStars.setBounds(802, 46, 53, 21);
        bestRateStars.setVisible(false);

        JLabel backCover = new JLabel("");

        backCover.setBounds(-14, 650, 180, 40);
        contentPane.add(backCover);
        backCover.setVisible(false);

        JCheckBox AgencyCheck1 = new JCheckBox("Agence 1");
        AgencyCheck1.setFont(new Font("Tahoma", Font.BOLD, 14));
        AgencyCheck1.setForeground(new Color(0, 0, 0));
        AgencyCheck1.setBounds(353, 130, 135, 21);
        contentPane.add(AgencyCheck1);

        AgencyCheck2 = new JCheckBox("Agence 2");
        AgencyCheck2.setForeground(new Color(0, 0, 0));
        AgencyCheck2.setFont(new Font("Tahoma", Font.BOLD, 14));
        AgencyCheck2.setBounds(510, 130, 117, 21);
        contentPane.add(AgencyCheck2);

        JCheckBox AgencyCheck3 = new JCheckBox("Agence 3");
        AgencyCheck3.setForeground(new Color(0, 0, 0));
        AgencyCheck3.setFont(new Font("Tahoma", Font.BOLD, 14));
        AgencyCheck3.setBounds(672, 130, 167, 21);
        contentPane.add(AgencyCheck3);


        JLabel titleLabel = new JLabel("Comparateur");
        titleLabel.setBounds(417, 31, 303, 48);
        titleLabel.setVerticalAlignment(SwingConstants.TOP);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(new Color(0, 0, 0));
        titleLabel.setFont(new Font("Gayathri Thin", Font.BOLD, 40));

        JSeparator titleSeparator = new JSeparator();
        titleSeparator.setBounds(440, 80, 257, 10);
        titleSeparator.setForeground(new Color(0, 0, 0));

        destinationInput = new JTextField();
        destinationInput.setBounds(429, 202, 323, 28);
        destinationInput.setFont(new Font("Tahoma", Font.BOLD, 17));
        destinationInput.setHorizontalAlignment(SwingConstants.LEFT);
        destinationInput.setColumns(10);
        contentPane.add(destinationInput);

        JLabel destinationCover = new JLabel("");
        destinationCover.setBounds(385, 188, 380, 57);
        contentPane.add(destinationCover);

        personNumberInput = new JTextField();
        personNumberInput.setBounds(555, 255, 28, 36);
        personNumberInput.setForeground(new Color(0, 0, 0));
        personNumberInput.setFont(new Font("Tahoma", Font.BOLD, 17));
        personNumberInput.setColumns(10);

        JLabel errorMessage = new JLabel("");
        errorMessage.setForeground(new Color(255, 0, 0));
        errorMessage.setFont(new Font("Tahoma", Font.BOLD, 17));
        errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        errorMessage.setBounds(264, 637, 621, 36);
        contentPane.add(errorMessage);

        JSlider priceSelector = new JSlider();
        priceSelector.setMinimum(10);
        priceSelector.setBounds(560, 376, 200, 22);
        priceSelector.setMaximum(1000);

        JLabel topPrice = new JLabel("");
        topPrice.setBounds(35, 31, 510, 90);
        contentPane.add(topPrice);
        topPrice.setVisible(false);

        JLabel topStars = new JLabel("");
        topStars.setBounds(580, 31, 510, 90);
        contentPane.add(topStars);

        topStars.setVisible(false);


        JSlider personNumberSelector = new JSlider();
        personNumberSelector.setMinimum(1);
        personNumberSelector.setBounds(585, 263, 200, 22);
        personNumberSelector.setValue(2);
        personNumberSelector.setMaximum(20);

        JButton searchButton = new JButton("SEARCH");
        searchButton.setBounds(500, 573, 148, 42);
        searchButton.setBackground(new Color(243, 254, 255));

        JLabel homeMessage = new JLabel("Destination :");
        homeMessage.setBounds(450, 156, 240, 42);
        homeMessage.setFont(new Font("Tahoma", Font.BOLD, 15));
        homeMessage.setForeground(new Color(0, 0, 0));
        homeMessage.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel chooseHotelLabel = new JLabel("Choisir l'hotel ");
        chooseHotelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chooseHotelLabel.setForeground(Color.BLACK);
        chooseHotelLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        chooseHotelLabel.setBounds(120, 198, 234, 24);
        contentPane.add(chooseHotelLabel);
        chooseHotelLabel.setVisible(false);

        JLabel roomHotelImage = new JLabel("");
        roomHotelImage.setBounds(510, 243, 570, 370);
        contentPane.add(roomHotelImage);
        roomHotelImage.setVisible(false);

        JLabel chooseRoomLabel = new JLabel("- Choisissez la chambre - ");
        chooseRoomLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chooseRoomLabel.setForeground(Color.BLACK);
        chooseRoomLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        chooseRoomLabel.setBounds(80, 285, 319, 24);
        contentPane.add(chooseRoomLabel);
        chooseRoomLabel.setVisible(false);

        JLabel imagePreviewLabel = new JLabel("Image de la chambre ");
        imagePreviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePreviewLabel.setForeground(Color.BLACK);
        imagePreviewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        imagePreviewLabel.setBounds(640, 200, 319, 24);
        contentPane.add(imagePreviewLabel);
        imagePreviewLabel.setVisible(false);

        JLabel personNumberLabel = new JLabel("Nombre de Personnes : ");
        personNumberLabel.setBounds(340, 261, 234, 24);
        personNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        personNumberLabel.setForeground(new Color(0, 0, 0));

        JLabel priceLabel = new JLabel("Prix Max : ");
        priceLabel.setBounds(395, 374, 117, 24);
        priceLabel.setForeground(Color.BLACK);
        priceLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel dateInCover = new JLabel("");
        dateInCover.setBounds(580, 429, 140, 40);

        JLabel dateOutCover = new JLabel("");
        dateOutCover.setBounds(580, 490, 140, 40);

        JLabel departureLabel = new JLabel("Date de départ");
        departureLabel.setBounds(435, 440, 167, 24);
        departureLabel.setForeground(Color.BLACK);
        departureLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel simplePub = new JLabel("");
        simplePub.setBounds(35, 31, 1055, 90);
        contentPane.add(simplePub);
        simplePub.setVisible(false);

        JLabel returnDateLabel = new JLabel("Date de retour");
        returnDateLabel.setBounds(435, 500, 138, 24);
        returnDateLabel.setForeground(Color.BLACK);
        returnDateLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel quitCover = new JLabel("");
        quitCover.setBounds(962, 650, 180, 40);

        JButton exitBtn = new JButton("EXIT");
        exitBtn.setBounds(1021, 650, 74, 40);
        exitBtn.setBackground(new Color(243, 254, 255));

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JButton backButton = new JButton("BACK");
        backButton.setBackground(new Color(243, 254, 255));

        JSeparator roomInfoSeparator = new JSeparator();
        roomInfoSeparator.setBounds(115, 406, 247, 28);
        contentPane.add(roomInfoSeparator);
        roomInfoSeparator.setVisible(false);

        JButton checkoutBtn = new JButton("Checkout");

        checkoutBtn.setForeground(Color.BLACK);
        checkoutBtn.setBackground(new Color(243, 254, 255));

        checkoutBtn.setBounds(164, 561, 148, 42);
        contentPane.add(checkoutBtn);
        checkoutBtn.setVisible(false);

        JLabel checkoutBtnCover = new JLabel("");
        checkoutBtnCover.setBounds(148, 562, 180, 40);
        contentPane.add(checkoutBtnCover);
        checkoutBtnCover.setVisible(false);

        JLabel starsNumberLabel = new JLabel("Nombre d'étoiles");
        starsNumberLabel.setBounds(420, 322, 182, 24);
        starsNumberLabel.setForeground(Color.BLACK);
        starsNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(starsNumberLabel);

        JLabel paymentInfoTitle = new JLabel("- Informations de commande -");
        paymentInfoTitle.setHorizontalAlignment(SwingConstants.CENTER);
        paymentInfoTitle.setForeground(new Color(0, 0, 0));
        paymentInfoTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        paymentInfoTitle.setBounds(320, 100, 500, 30);
        contentPane.add(paymentInfoTitle);
        paymentInfoTitle.setVisible(false);

        JLabel roomSizeDisplay = new JLabel("");
        roomSizeDisplay.setFont(new Font("Tahoma", Font.BOLD, 17));
        roomSizeDisplay.setForeground(new Color(0, 0, 0));
        roomSizeDisplay.setBackground(new Color(0, 0, 0));
        roomSizeDisplay.setBounds(265, 453, 193, 48);
        contentPane.add(roomSizeDisplay);
        roomSizeDisplay.setVisible(false);

        JLabel roomPriceDisplay = new JLabel("");
        roomPriceDisplay.setForeground(Color.BLACK);
        roomPriceDisplay.setFont(new Font("Tahoma", Font.BOLD, 17));
        roomPriceDisplay.setBackground(Color.BLACK);
        roomPriceDisplay.setBounds(295, 490, 135, 48);
        contentPane.add(roomPriceDisplay);
        roomPriceDisplay.setVisible(false);

        JLabel roomNumberDisplay = new JLabel("");
        roomNumberDisplay.setForeground(Color.BLACK);
        roomNumberDisplay.setFont(new Font("Tahoma", Font.BOLD, 17));
        roomNumberDisplay.setBackground(Color.BLACK);
        roomNumberDisplay.setBounds(279, 415, 84, 48);
        contentPane.add(roomNumberDisplay);
        roomNumberDisplay.setVisible(false);

        JLabel roomNumberLabel = new JLabel("Numéro chambre :");
        roomNumberLabel.setForeground(new Color(0, 0, 0));
        roomNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        roomNumberLabel.setBounds(120, 416, 180, 48);
        contentPane.add(roomNumberLabel);
        roomNumberLabel.setVisible(false);

        JLabel roomSizeLabel = new JLabel("Nombre de lits  :");
        roomSizeLabel.setForeground(Color.BLACK);
        roomSizeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        roomSizeLabel.setBounds(120, 453, 180, 48);
        contentPane.add(roomSizeLabel);
        roomSizeLabel.setVisible(false);

        JLabel roomPriceLabel = new JLabel("Prix de la chambre :");
        roomPriceLabel.setForeground(Color.BLACK);
        roomPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        roomPriceLabel.setBounds(120, 490, 180, 48);
        contentPane.add(roomPriceLabel);
        roomPriceLabel.setVisible(false);

        JLabel roomInfosLabel = new JLabel(" Informations chambre ");
        roomInfosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roomInfosLabel.setForeground(Color.BLACK);
        roomInfosLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        roomInfosLabel.setBounds(69, 366, 337, 48);
        contentPane.add(roomInfosLabel);
        roomInfosLabel.setVisible(false);


        AgencyCheck1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (AgencyCheck1.isSelected()) {
                    searchButton.setEnabled(true);
                    errorMessage.setText("");
                }
            }
        });

        AgencyCheck2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (AgencyCheck2.isSelected()) {
                    searchButton.setEnabled(true);
                    errorMessage.setText("");
                }
            }
        });

        AgencyCheck3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (AgencyCheck3.isSelected()) {
                    searchButton.setEnabled(true);
                    errorMessage.setText("");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String SERVICE_URL1 = "http://localhost:5009/agence3/api/";
                String SERVICE_URL2 = "http://localhost:5007/agence1/api/";
                String SERVICE_URL3 = "http://localhost:5008/agence2/api/";
                boolean BestRatePriceDisplay = false;

                String URI_HOTEL = "agency";
                String URI_HOTEL_ID = URI_HOTEL + "/{id}";
                Map<String, String> URIS = new HashMap<String, String>();

                String destination = destinationInput.getText();
                String personNum = personNumberInput.getText();
                String prce = priceSelectedMax.getText();
                String DateIn = dateIn.getText();
                String DateOut = dateOut.getText();
                int price = Integer.valueOf(prce);
                ;
                int bedNumber = Integer.valueOf(personNum);
                int stars = starsSelector.getSelectedIndex() + 1;
                Map<String, String> params = new HashMap<>();
                params.put("position", destination);
                params.put("datein", DateIn);
                params.put("dateout", DateOut);
                params.put("size", String.valueOf(bedNumber));
                params.put("rating", String.valueOf(stars));
                params.put("price", String.valueOf(price));

                if (AgencyCheck1.isSelected() && AgencyCheck2.isSelected() && AgencyCheck3.isSelected()) {
                    URIS.put(SERVICE_URL1 + URI_HOTEL, SERVICE_URL1 + URI_HOTEL + URI_HOTEL_ID);
                    URIS.put(SERVICE_URL2 + URI_HOTEL, SERVICE_URL2 + URI_HOTEL + URI_HOTEL_ID);
                    URIS.put(SERVICE_URL3 + URI_HOTEL, SERVICE_URL3 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = true;
                } else if (AgencyCheck1.isSelected() && AgencyCheck2.isSelected() && !(AgencyCheck3.isSelected())) {
                    URIS.put(SERVICE_URL1 + URI_HOTEL, SERVICE_URL1 + URI_HOTEL + URI_HOTEL_ID);
                    URIS.put(SERVICE_URL2 + URI_HOTEL, SERVICE_URL2 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = true;
                } else if (AgencyCheck1.isSelected() && !(AgencyCheck2.isSelected()) && AgencyCheck3.isSelected()) {
                    URIS.put(SERVICE_URL1 + URI_HOTEL, SERVICE_URL1 + URI_HOTEL + URI_HOTEL_ID);
                    URIS.put(SERVICE_URL3 + URI_HOTEL, SERVICE_URL3 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = true;
                } else if (!(AgencyCheck1.isSelected()) && AgencyCheck2.isSelected() && AgencyCheck3.isSelected()) {
                    URIS.put(SERVICE_URL2 + URI_HOTEL, SERVICE_URL2 + URI_HOTEL + URI_HOTEL_ID);
                    URIS.put(SERVICE_URL3 + URI_HOTEL, SERVICE_URL3 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = true;
                } else if (AgencyCheck1.isSelected() && !(AgencyCheck2.isSelected()) && !(AgencyCheck3.isSelected())) {
                    URIS.put(SERVICE_URL1 + URI_HOTEL, SERVICE_URL1 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = false;
                } else if (!(AgencyCheck1.isSelected()) && AgencyCheck2.isSelected() && !(AgencyCheck3.isSelected())) {
                    URIS.put(SERVICE_URL2 + URI_HOTEL, SERVICE_URL2 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = false;
                } else if (!(AgencyCheck1.isSelected()) && !(AgencyCheck2.isSelected()) && AgencyCheck3.isSelected()) {
                    URIS.put(SERVICE_URL3 + URI_HOTEL, SERVICE_URL3 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = false;
                }

                HashMap<Hotel, HashMap<String, Double>> hotelMap = new HashMap<>();
                for (String uri : URIS.keySet()) {
                    try {
                        String url = uri + "/search?position={position}&size={size}&rating={rating}&datein={datein}&dateout={dateout}&price={price}";
                        Hotel[] returnedHotel = proxy.getForObject(url, Hotel[].class, params);
                        for (Hotel hotel : returnedHotel) {
                            if (!hotel.getName().equals("Undefined")) {
                                HashMap<String, Double> agencyMap = new HashMap<>();
                                int lastIndex = hotel.getImageFolder().lastIndexOf("/");
                                double discount = 10.0;
                                agencyMap.put(uri, discount);
                                //hotel.setImageFolder(hotel.getImageFolder().substring(0,lastIndex));
                                hotelMap.put(hotel, agencyMap);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < hotelMap.size(); i++) {
                    for (int j = 0; j < hotelMap.size(); j++) {
                        if (i != j) {
                            try {
                                Hotel hotel = (Hotel) hotelMap.keySet().toArray()[i];
                                Hotel toCompare = (Hotel) hotelMap.keySet().toArray()[j];
                                HashMap<String, Double> agency1 = hotelMap.get(hotel);
                                String agencyUrl1 = (String) agency1.keySet().toArray()[0];
                                double discount1 = agency1.get(agencyUrl1);
                                if (hotel.getName().equals(toCompare.getName())) {
                                    HashMap<String, Double> agency2 = hotelMap.get(toCompare);
                                    String agencyUrl2 = (String) agency2.keySet().toArray()[0];
                                    double discount2 = agency2.get(agencyUrl2);
                                    if (discount1 >= discount2) {
                                        hotelMap.remove(toCompare);
                                    }
                                }
                            } catch (Exception e) {
                                continue;
                            }
                        }
                    }

                }

                double bestPrice = 1000;
                String bestPriceHotel = "Aucune info";
                double BestRate = 0;
                String bestRateHotel = "Aucune info";

                for (Hotel key : hotelMap.keySet()) {
                    if (key.getStars() > BestRate) {
                        BestRate = key.getStars();
                        bestRateHotel = key.getName();
                    }
                    for (Room room : key.getRooms()) {
                        if (room.getPrice() < bestPrice) {
                            bestPrice = room.getPrice();
                            bestPriceHotel = key.getName();
                        }
                    }
                }

                bestPriceAgence.setText(bestPriceHotel);
                bestRateAgence.setText(bestRateHotel);
                bestPricePrix.setText(String.valueOf(bestPrice) + "€");
                bestRateStars.setText(String.valueOf(BestRate));


                String firstHotel = null;
                if (!(hotelMap.isEmpty())) {
                    Hotel firstHotelValue = (Hotel) hotelMap.keySet().toArray()[0];

                }

                for (Hotel hotel : hotelMap.keySet()) {
                    hotelChoice.addItem(hotel.getName());
                }
                roomChoice.removeAllItems();
                Hotel selectedH = null;
                Room selectedR = null;
                String selectedHotel = (String) hotelChoice.getSelectedItem();
                for (Hotel key : hotelMap.keySet()) {
                    if (key.getName().equals(selectedHotel)) {
                        for (Room room : key.getRooms()) {
                            roomChoice.addItem(room);
                            selectedH = key;
                            selectedR = room;
                        }
                    }
                }

                hotelChoice.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String selectedHotel = (String) hotelChoice.getSelectedItem();
                        roomChoice.removeAllItems();

                        for (Hotel key : hotelMap.keySet()) {
                            if (key.getName().equals(selectedHotel)) {
                                hotelName.setText((String) hotelChoice.getSelectedItem());
                                for (Room room : key.getRooms()) {
                                    roomChoice.addItem(room);
                                    roomNumberDisplay.setText(String.valueOf(room.getNumber()));
                                    roomPriceDisplay.setText(String.valueOf(room.getPrice()) + "€");
                                    roomSizeDisplay.setText(String.valueOf(room.getSize()) + " personne(s)");

                                }
                            }
                        }
                    }
                });


                roomChoice.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String selectedHotel = (String) hotelChoice.getSelectedItem();
                        String selectedRoom = String.valueOf(roomChoice.getSelectedItem());

                        String number = "0";

                        java.util.regex.Matcher m = Pattern.compile("[^0-9]*([0-9]+).*").matcher(selectedRoom);
                        if (m.matches()) {
                            number = m.group(1);
                        }

                        for (Hotel key : hotelMap.keySet()) {
                            if (key.getName().equals(selectedHotel)) {
                                for (Room room : key.getRooms()) {
                                    if (room.getNumber() == (Integer.parseInt(number))) {
                                        roomNumberDisplay.setText(String.valueOf(room.getNumber()));
                                        roomPriceDisplay.setText(String.valueOf(room.getPrice()) + "€");
                                        roomSizeDisplay.setText(String.valueOf(room.getSize()) + " personne(s)");

                                        roomNumber.setText(String.valueOf(room.getNumber()));

//										if(room.getRoomNumber() == (Integer.parseInt(number))) {
//											BufferedImage roomImg = null;
//											try {
//												roomImg = ImageIO.read(new URL(key.getImageFolder() + "/" + String.valueOf(room.getRoomNumber()) + ".jpg"));
//												if(roomImg == null) {
//													roomImg = ImageIO.read(new URL(key.getImageFolder() + "/" + String.valueOf(room.getRoomNumber()) + ".jpg"));
//												}
//											} catch (MalformedURLException e1) {
//												e1.printStackTrace();
//											} catch (IOException e1) {
//												e1.printStackTrace();
//											}
////											roomHotelImage.setIcon(new ImageIcon(roomImg));
//										}
                                    }
                                }
                            }
                        }
                    }
                });

                errorMessage.setVisible(false);
                destinationInput.setVisible(false);
                homeMessage.setVisible(false);
                titleLabel.setVisible(false);
                titleSeparator.setVisible(false);
                destinationCover.setVisible(false);
                destinationInput.setVisible(false);
                personNumberLabel.setVisible(false);
                personNumberInput.setVisible(false);
                personNumberSelector.setVisible(false);
                quitCover.setVisible(false);
                exitBtn.setVisible(false);
                starsSelector.setVisible(false);
                starsNumberLabel.setVisible(false);
                priceLabel.setVisible(false);
                priceSelector.setVisible(false);
                priceSelectedMax.setVisible(false);
                departureLabel.setVisible(false);
                returnDateLabel.setVisible(false);
                dateIn.setVisible(false);
                dateOut.setVisible(false);
                dateInCover.setVisible(false);
                dateOutCover.setVisible(false);
                searchButton.setVisible(false);
                AgencyCheck1.setVisible(false);
                AgencyCheck2.setVisible(false);
                AgencyCheck3.setVisible(false);
                chooseAgencyLabel.setVisible(false);
                backButton.setVisible(true);
                backCover.setVisible(true);

                if (BestRatePriceDisplay) {
                    topPrice.setVisible(true);
                    topStars.setVisible(true);
                    bestRateAgence.setVisible(true);
                    bestRateStars.setVisible(true);
                    bestPriceAgence.setVisible(true);
                    bestPricePrix.setVisible(true);
                } else {
                    simplePub.setVisible(true);
                }

                roomChoice.setVisible(true);
                hotelChoice.setVisible(true);
                chooseRoomLabel.setVisible(true);
                chooseHotelLabel.setVisible(true);
                imagePreviewLabel.setVisible(true);
                roomHotelImage.setVisible(true);
                roomNumberDisplay.setVisible(true);
                roomSizeDisplay.setVisible(true);
                roomPriceDisplay.setVisible(true);
                roomInfosLabel.setVisible(true);
                roomSizeLabel.setVisible(true);
                roomPriceLabel.setVisible(true);
                roomNumberLabel.setVisible(true);
                checkoutBtn.setVisible(true);
                checkoutBtnCover.setVisible(true);
                roomInfoSeparator.setVisible(true);
                hotelName.setVisible(false);
                roomNumber.setVisible(false);
            }
        });

        checkoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                errorMessage.setVisible(false);
                destinationInput.setVisible(false);
                homeMessage.setVisible(false);
                titleLabel.setVisible(false);
                titleSeparator.setVisible(false);
                destinationCover.setVisible(false);
                destinationInput.setVisible(false);
                personNumberLabel.setVisible(false);
                personNumberInput.setVisible(false);
                personNumberSelector.setVisible(false);
                quitCover.setVisible(true);
                exitBtn.setVisible(true);
                starsSelector.setVisible(false);
                starsNumberLabel.setVisible(false);
                priceLabel.setVisible(false);
                priceSelector.setVisible(false);
                priceSelectedMax.setVisible(false);
                departureLabel.setVisible(false);
                returnDateLabel.setVisible(false);
                dateIn.setVisible(false);
                dateOut.setVisible(false);
                dateInCover.setVisible(false);
                dateOutCover.setVisible(false);
                AgencyCheck1.setVisible(false);
                AgencyCheck2.setVisible(false);
                AgencyCheck3.setVisible(false);
                chooseAgencyLabel.setVisible(false);
                backButton.setVisible(false);
                backCover.setVisible(false);
                topPrice.setVisible(false);
                topStars.setVisible(false);
                bestRateAgence.setVisible(false);
                bestRateStars.setVisible(false);
                bestPriceAgence.setVisible(false);
                bestPricePrix.setVisible(false);
                roomChoice.removeAllItems();
                hotelChoice.removeAllItems();
                roomChoice.setVisible(false);
                hotelChoice.setVisible(false);
                chooseRoomLabel.setVisible(false);
                chooseHotelLabel.setVisible(false);
                imagePreviewLabel.setVisible(false);
                roomHotelImage.setVisible(false);
                simplePub.setVisible(false);
                roomNumberDisplay.setVisible(false);
                roomSizeDisplay.setVisible(false);
                roomPriceDisplay.setVisible(false);
                roomInfosLabel.setVisible(false);
                roomSizeLabel.setVisible(false);
                roomPriceLabel.setVisible(false);
                roomNumberLabel.setVisible(false);
                checkoutBtn.setVisible(false);
                checkoutBtnCover.setVisible(false);
                roomInfoSeparator.setVisible(false);
                payBtn.setVisible(true);
                payBtnCover.setVisible(true);
                firstNameCover.setVisible(true);
                nameCover.setVisible(true);
                reservationName.setVisible(true);
                reservationFirstName.setVisible(true);
                errorMessage.setText("");

                cvvLabel.setVisible(true);
                cardExpirationLabel.setVisible(true);
                phoneNumberLabel.setVisible(true);
                cardNumberLabel.setVisible(true);
                firstNameLabel.setVisible(true);
                reservationNameLabel.setVisible(true);
                cardNumberCover.setVisible(true);
                cardCVVCover.setVisible(true);
                cardExpirationCover.setVisible(true);
                phoneCover.setVisible(true);
                phoneNumberReservation.setVisible(true);
                cardNumberReservation.setVisible(true);
                cardCVVReservation.setVisible(true);
                cardExpirationReservation.setVisible(true);
                hotelName.setVisible(false);
                roomNumber.setVisible(false);
                paymentInfoTitle.setVisible(true);
            }
        });

        payBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String SERVICE_URL1 = "http://localhost:5009/agence3/api/";
                String SERVICE_URL2 = "http://localhost:5007/agence1/api/";
                String SERVICE_URL3 = "http://localhost:5008/agence2/api/";
                boolean BestRatePriceDisplay = false;

                String URI_HOTEL = "agency";
                String URI_HOTEL_ID = URI_HOTEL + "/{id}";
                Map<String, String> URIS = new HashMap<String, String>();

                String destination = destinationInput.getText();
                String personNum = personNumberInput.getText();
                String prce = priceSelectedMax.getText();
                String DateIn = dateIn.getText();
                String DateOut = dateOut.getText();
                int price = Integer.valueOf(prce);
                ;
                int bedNumber = Integer.valueOf(personNum);
                int stars = starsSelector.getSelectedIndex() + 1;
                Map<String, String> params = new HashMap<>();
                params.put("position", destination);
                params.put("datein", DateIn);
                params.put("dateout", DateOut);
                params.put("size", String.valueOf(bedNumber));
                params.put("rating", String.valueOf(stars));
                params.put("price", String.valueOf(price));

                if (AgencyCheck1.isSelected() && AgencyCheck2.isSelected() && AgencyCheck3.isSelected()) {
                    URIS.put(SERVICE_URL1 + URI_HOTEL, SERVICE_URL1 + URI_HOTEL + URI_HOTEL_ID);
                    URIS.put(SERVICE_URL2 + URI_HOTEL, SERVICE_URL2 + URI_HOTEL + URI_HOTEL_ID);
                    URIS.put(SERVICE_URL3 + URI_HOTEL, SERVICE_URL3 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = true;
                } else if (AgencyCheck1.isSelected() && AgencyCheck2.isSelected() && !(AgencyCheck3.isSelected())) {
                    URIS.put(SERVICE_URL1 + URI_HOTEL, SERVICE_URL1 + URI_HOTEL + URI_HOTEL_ID);
                    URIS.put(SERVICE_URL2 + URI_HOTEL, SERVICE_URL2 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = true;
                } else if (AgencyCheck1.isSelected() && !(AgencyCheck2.isSelected()) && AgencyCheck3.isSelected()) {
                    URIS.put(SERVICE_URL1 + URI_HOTEL, SERVICE_URL1 + URI_HOTEL + URI_HOTEL_ID);
                    URIS.put(SERVICE_URL3 + URI_HOTEL, SERVICE_URL3 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = true;
                } else if (!(AgencyCheck1.isSelected()) && AgencyCheck2.isSelected() && AgencyCheck3.isSelected()) {
                    URIS.put(SERVICE_URL2 + URI_HOTEL, SERVICE_URL2 + URI_HOTEL + URI_HOTEL_ID);
                    URIS.put(SERVICE_URL3 + URI_HOTEL, SERVICE_URL3 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = true;
                } else if (AgencyCheck1.isSelected() && !(AgencyCheck2.isSelected()) && !(AgencyCheck3.isSelected())) {
                    URIS.put(SERVICE_URL1 + URI_HOTEL, SERVICE_URL1 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = false;
                } else if (!(AgencyCheck1.isSelected()) && AgencyCheck2.isSelected() && !(AgencyCheck3.isSelected())) {
                    URIS.put(SERVICE_URL2 + URI_HOTEL, SERVICE_URL2 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = false;
                } else if (!(AgencyCheck1.isSelected()) && !(AgencyCheck2.isSelected()) && AgencyCheck3.isSelected()) {
                    URIS.put(SERVICE_URL3 + URI_HOTEL, SERVICE_URL3 + URI_HOTEL + URI_HOTEL_ID);
                    BestRatePriceDisplay = false;
                }

                HashMap<Hotel, HashMap<String, Double>> hotelMap = new HashMap<>();
                for (String uri : URIS.keySet()) {
                    try {
                        String url = uri + "/search?position={position}&size={size}&rating={rating}&datein={datein}&dateout={dateout}&price={price}";
                        Hotel[] returnedHotel = proxy.getForObject(url, Hotel[].class, params);
                        for (Hotel hotel : returnedHotel) {
                            if (!hotel.getName().equals("Undefined")) {
                                HashMap<String, Double> agencyMap = new HashMap<>();
                                int lastIndex = hotel.getImageFolder().lastIndexOf("/");
                                double discount = Double.parseDouble(hotel.getImageFolder().substring(lastIndex + 1));
                                agencyMap.put(uri, discount);
//								hotel.setImageFolder(hotel.getImageFolder().substring(0,lastIndex));
                                hotelMap.put(hotel, agencyMap);
                            }
                        }
                    } catch (Exception e12) {
                        e12.printStackTrace();
                    }
                }
                for (int i = 0; i < hotelMap.size(); i++) {
                    for (int j = 0; j < hotelMap.size(); j++) {
                        if (i != j) {
                            try {
                                Hotel hotel = (Hotel) hotelMap.keySet().toArray()[i];
                                Hotel toCompare = (Hotel) hotelMap.keySet().toArray()[j];
                                HashMap<String, Double> agency1 = hotelMap.get(hotel);
                                String agencyUrl1 = (String) agency1.keySet().toArray()[0];
                                double discount1 = agency1.get(agencyUrl1);
                                if (hotel.getName().equals(toCompare.getName())) {
                                    HashMap<String, Double> agency2 = hotelMap.get(toCompare);
                                    String agencyUrl2 = (String) agency2.keySet().toArray()[0];
                                    double discount2 = agency2.get(agencyUrl2);
                                    if (discount1 >= discount2) {
                                        hotelMap.remove(toCompare);
                                    }
                                }
                            } catch (Exception e11) {
                                continue;
                            }
                        }
                    }

                }

                LocalDate ind = LocalDate.parse(DateIn);
                LocalDate outd = LocalDate.parse(DateOut);
                String firstname = reservationFirstName.getText();
                String name = reservationName.getText();

                int roomNumberChoice = Integer.valueOf(roomNumber.getText());
                String hotelNameChoice = hotelName.getText();

                Hotel selectedHotel = null;
                Room selectedRoom = null;

                for (Hotel key : hotelMap.keySet()) {
                    if (key.getName().equals(hotelNameChoice)) {
                        selectedHotel = key;
                        for (Room room : key.getRooms()) {
                            if (room.getNumber() == roomNumberChoice) {
                                selectedRoom = room;
                            }
                        }
                    }
                }

                //int lastIndex = selectedHotel.getImageFolder().lastIndexOf("/");
                double discount = 0;
                for (Entry<Hotel, HashMap<String, Double>> map : hotelMap.entrySet()) {
                    if (map.getKey().equals(selectedHotel)) {
                        String agencyURI = (String) map.getValue().keySet().toArray()[0];
                        discount = map.getValue().get(agencyURI);
                        break;
                    }
                }

                int hotelChoiceNumber = hotelChoice.getSelectedIndex();

                Reservation reservation = new Reservation(firstname + " " + name, ind, outd, discount, selectedRoom);
                selectedRoom.getReservations().add(reservation);
                String agencyURI = "";
                for (Entry<Hotel, HashMap<String, Double>> map : hotelMap.entrySet()) {
                    if (map.getKey().equals(selectedHotel)) {
                        agencyURI = (String) map.getValue().keySet().toArray()[0];
                        break;
                    }
                }
                String url = agencyURI + "/resa/" + String.valueOf(selectedHotel.getId());
                proxy.put(url, selectedHotel);
                MainFunctions.getRecipe(selectedHotel, reservation.getClient(), reservation);
                MainFunctions.makePdf(selectedHotel, reservation.getClient(), reservation);

                payBtn.setVisible(false);
                payBtnCover.setVisible(false);
                firstNameCover.setVisible(false);
                nameCover.setVisible(false);
                reservationName.setVisible(false);
                reservationFirstName.setVisible(false);

                cvvLabel.setVisible(false);
                cardExpirationLabel.setVisible(false);
                phoneNumberLabel.setVisible(false);
                cardNumberLabel.setVisible(false);
                firstNameLabel.setVisible(false);
                reservationNameLabel.setVisible(false);
                cardNumberCover.setVisible(false);
                cardCVVCover.setVisible(false);
                cardExpirationCover.setVisible(false);
                phoneCover.setVisible(false);
                phoneNumberReservation.setVisible(false);
                cardNumberReservation.setVisible(false);
                cardCVVReservation.setVisible(false);
                cardExpirationReservation.setVisible(false);

                errorMessage.setVisible(true);
                errorMessage.setForeground(Color.green);
                errorMessage.setText("Réservation réussie");
                quitCover.setVisible(true);
                exitBtn.setVisible(true);

                JOptionPane.showMessageDialog(null,
                        "Votre commande est enregistrée",
                        url, JOptionPane.WARNING_MESSAGE);

                reservedHotel.setText(hotelNameChoice);
                reservedRoom.setText(String.valueOf(selectedRoom));
                clientInfos.setText(firstname + " " + name);
                reservedRoom.setVisible(true);
                reservedHotel.setVisible(true);
                clientInfos.setVisible(true);
            }
        });

        priceSelectedMax = new JTextField();
        priceSelectedMax.setBounds(490, 367, 66, 40);
        priceSelectedMax.setForeground(new Color(0, 0, 0));
        priceSelectedMax.setFont(new Font("Tahoma", Font.BOLD, 17));
        priceSelectedMax.setColumns(10);

        dateOut = new JTextField();
        dateOut.setBounds(614, 496, 95, 28);
        dateOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateOut.setColumns(10);

        dateIn = new JTextField();
        dateIn.setBounds(614, 435, 95, 28);
        dateIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateIn.setColumns(10);

        dateIn.setText("2024-01-01");
        dateOut.setText("2024-01-10");
        contentPane.setLayout(null);
        contentPane.add(starsSelector);
        contentPane.add(titleLabel);


        contentPane.add(titleSeparator);
        contentPane.add(destinationInput);
        contentPane.add(destinationCover);
        contentPane.add(personNumberInput);
        contentPane.add(homeMessage);
        contentPane.add(searchButton);
        contentPane.add(exitBtn);
        contentPane.add(quitCover);
        contentPane.add(personNumberLabel);
        contentPane.add(priceLabel);
        contentPane.add(departureLabel);
        contentPane.add(returnDateLabel);
        contentPane.add(priceSelectedMax);
        contentPane.add(dateOut);
        contentPane.add(dateIn);
        contentPane.add(dateInCover);
        contentPane.add(dateOutCover);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                errorMessage.setVisible(true);
                destinationInput.setVisible(true);
                homeMessage.setVisible(true);
                titleLabel.setVisible(true);
                titleSeparator.setVisible(true);
                destinationCover.setVisible(true);
                destinationInput.setVisible(true);
                personNumberLabel.setVisible(true);
                personNumberInput.setVisible(true);
                personNumberSelector.setVisible(true);
                quitCover.setVisible(true);
                exitBtn.setVisible(true);
                starsSelector.setVisible(true);
                starsNumberLabel.setVisible(true);
                priceLabel.setVisible(true);
                priceSelector.setVisible(true);
                priceSelectedMax.setVisible(true);
                departureLabel.setVisible(true);
                returnDateLabel.setVisible(true);
                dateIn.setVisible(true);
                dateOut.setVisible(true);
                dateInCover.setVisible(true);
                dateOutCover.setVisible(true);
                searchButton.setVisible(true);
                AgencyCheck1.setVisible(true);
                AgencyCheck2.setVisible(true);
                AgencyCheck3.setVisible(true);
                chooseAgencyLabel.setVisible(true);
                backButton.setVisible(false);
                backCover.setVisible(false);
                topPrice.setVisible(false);
                topStars.setVisible(false);
                bestRateAgence.setVisible(false);
                bestRateStars.setVisible(false);
                bestPriceAgence.setVisible(false);
                bestPricePrix.setVisible(false);
                roomChoice.removeAllItems();
                hotelChoice.removeAllItems();
                roomChoice.setVisible(false);
                hotelChoice.setVisible(false);
                chooseRoomLabel.setVisible(false);
                chooseHotelLabel.setVisible(false);
                imagePreviewLabel.setVisible(false);
                roomHotelImage.setVisible(false);
                simplePub.setVisible(false);
                roomNumberDisplay.setVisible(false);
                roomSizeDisplay.setVisible(false);
                roomPriceDisplay.setVisible(false);
                roomInfosLabel.setVisible(false);
                roomSizeLabel.setVisible(false);
                roomPriceLabel.setVisible(false);
                roomNumberLabel.setVisible(false);
                checkoutBtn.setVisible(false);
                checkoutBtnCover.setVisible(false);
                roomInfoSeparator.setVisible(false);
                errorMessage.setText("");

            }
        });
        backButton.setBounds(42, 650, 74, 40);
        contentPane.add(backButton);

        JLabel background = new JLabel("");
        background.setBounds(0, 5, 1142, 709);
        background.setFont(new Font("Tahoma", Font.BOLD, 14));


        JLabel backgroundSearch = new JLabel("");
        backgroundSearch.setBounds(0, 0, 1142, 709);
        backgroundSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(backgroundSearch);
        contentPane.add(background);


    }
}
