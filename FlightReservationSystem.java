import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

class Ticket {
    protected String customerName;
    protected String flightNumber;
    protected String departureCity;
    protected String destinationCity;
    protected String departureDate;
    protected String departureTime;
    protected double basePrice;
    protected boolean isReserved;

    public Ticket(String customerName, String flightNumber, String departureCity,
                  String destinationCity, String departureDate, String departureTime, double basePrice) {
        this.customerName = customerName;
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.basePrice = basePrice;
        this.isReserved = false;
    }

    public String getTicketDetails() {
        return "Customer: " + customerName + "\n" +
               "Flight Number: " + flightNumber + "\n" +
               "Departure City: " + departureCity + "\n" +
               "Destination: " + destinationCity + "\n" +
               "Departure Date: " + departureDate + "\n" +
               "Departure Time: " + departureTime + "\n" +
               "Price: $" + basePrice;
    }

    public void reserve() {
        this.isReserved = true;
    }
}

class EconomyTicket extends Ticket {
    public EconomyTicket(String customerName, String flightNumber, String departureCity,
                         String destinationCity, String departureDate, String departureTime) {
        super(customerName, flightNumber, departureCity, destinationCity, departureDate, departureTime, 400.0);
    }
}

class BusinessTicket extends Ticket {
    private String seatPreference;
    private String snackChoice;

    public BusinessTicket(String customerName, String flightNumber, String departureCity,
                          String destinationCity, String departureDate, String departureTime,
                          String seatPreference, String snackChoice) {
        super(customerName, flightNumber, departureCity, destinationCity, departureDate, departureTime, 600.0);
        this.seatPreference = seatPreference;
        this.snackChoice = snackChoice;
    }

    
    public String getTicketDetails() {
        return super.getTicketDetails() + "\n" +
               "Seat Preference: " + seatPreference + "\n" +
               "Snack Choice: " + snackChoice;
    }
}

public class FlightReservationSystem extends JFrame implements ActionListener {
    private JTextField nameField;
    private JComboBox<String> flightNumberBox, classBox, seatPreferenceBox, snackBox, dateBox;
    private JTextArea resultArea;
    private int economySeats = 50;
    private int businessSeats = 20;

 
    private HashMap<String, Integer> reservationCounts = new HashMap<>();

    public FlightReservationSystem() {
        setTitle("Greensboro Airlines Ticket Reservation System");
        setLayout(new GridLayout(8, 2));

        // Customer Name
        add(new JLabel("Customer Name:"));
        nameField = new JTextField(10);
        add(nameField);

        // Flight Number
        add(new JLabel("Flight Number:"));
        String[] flights = {"GOS 1 (7:00 AM - 9:00 AM)", "GOS 2 (5:30 PM - 7:00 PM)"};
        flightNumberBox = new JComboBox<>(flights);
        add(flightNumberBox);

       
        add(new JLabel("Departure Date:"));
        dateBox = new JComboBox<>(getUpcomingDates(7)); 
        add(dateBox);

        
        add(new JLabel("Class:"));
        String[] classes = {"Economy", "Business"};
        classBox = new JComboBox<>(classes);
        classBox.addActionListener(this);
        add(classBox);

        
        add(new JLabel("Seat Preference (Business only):"));
        String[] seats = {"Window", "Aisle"};
        seatPreferenceBox = new JComboBox<>(seats);
        seatPreferenceBox.setEnabled(false);
        add(seatPreferenceBox);

        add(new JLabel("Snack (Business only):"));
        String[] snacks = {"Cookies", "Peanuts"};
        snackBox = new JComboBox<>(snacks);
        snackBox.setEnabled(false);
        add(snackBox);

        
        JButton reserveButton = new JButton("Reserve Ticket");
        reserveButton.addActionListener(this);
        add(reserveButton);

        
        resultArea = new JTextArea(10, 20);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private String[] getUpcomingDates(int daysAhead) {
        String[] dates = new String[daysAhead];
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < daysAhead; i++) {
            dates[i] = today.plusDays(i).format(formatter);
        }

        return dates;
    }

    
    public void actionPerformed(ActionEvent e) {
        String customerName = nameField.getText();
        String flightNumber = (String) flightNumberBox.getSelectedItem();
        String departureDate = (String) dateBox.getSelectedItem();
        String departureCity = flightNumber.startsWith("GOS 1") ? "Greensboro" : "Newark";
        String destinationCity = flightNumber.startsWith("GOS 1") ? "Newark" : "Greensboro";
        String departureTime = flightNumber.startsWith("GOS 1") ? "7:00 AM" : "5:30 PM";

        
        reservationCounts.putIfAbsent(customerName, 0);
        int reservations = reservationCounts.get(customerName);
        if (reservations >= 3) {
            resultArea.setText("Error: Customer " + customerName + " has reached the maximum reservation limit of 3.");
            return;
        }

        Ticket ticket;
        if (classBox.getSelectedItem().equals("Economy")) {
            if (economySeats > 0) {
                ticket = new EconomyTicket(customerName, flightNumber, departureCity, destinationCity, departureDate, departureTime);
                economySeats--;
            } else {
                resultArea.setText("All Economy seats are sold out!");
                return;
            }
        } else {
            if (businessSeats > 0) {
                String seatPreference = (String) seatPreferenceBox.getSelectedItem();
                String snackChoice = (String) snackBox.getSelectedItem();
                ticket = new BusinessTicket(customerName, flightNumber, departureCity, destinationCity, departureDate, departureTime, seatPreference, snackChoice);
                businessSeats--;
            } else {
                resultArea.setText("All Business seats are sold out!");
                return;
            }
        }

        
        reservationCounts.put(customerName, reservations + 1);
        ticket.reserve();
        resultArea.setText(ticket.getTicketDetails());
    }

    public static void main(String[] args) {
        new FlightReservationSystem();
    }
}
