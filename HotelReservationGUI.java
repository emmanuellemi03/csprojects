import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NoRoomException extends Exception {
    private String date;

    public NoRoomException(String date) {
        super("No rooms available for the requested date: " + date);
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}

class Room {
    private int roomNumber;
    private boolean isAvailable;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void reserve() {
        isAvailable = false;
    }

    public void cancel() {
        isAvailable = true;
    }
}

class HotelReservationSystem {
    private Room[] rooms;
    private String[] dates;

    public HotelReservationSystem(String date1, String date2) {
        this.dates = new String[]{date1, date2};
        this.rooms = new Room[5]; // Only 5 rooms
        for (int i = 0; i < 5; i++) {
            rooms[i] = new Room(i + 1);
        }
    }

    public boolean HotelFull() {
        for (Room room : rooms) {
            if (room.isAvailable()) {
                return false;
            }
        }
        return true;
    }

    public void ReserveRoom(String date, int roomNumber) throws NoRoomException {
        if (rooms[roomNumber - 1].isAvailable()) {
            rooms[roomNumber - 1].reserve();
            JOptionPane.showMessageDialog(null, 
                    "Room reserved: Date - " + date + ", Room Number - " + roomNumber,
                    "Reservation Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            throw new NoRoomException(date);
        }
    }

    public void CancelReservation(int roomNumber) {
        if (roomNumber > 0 && roomNumber <= 5) {
            if (!rooms[roomNumber - 1].isAvailable()) {
                rooms[roomNumber - 1].cancel();
                JOptionPane.showMessageDialog(null, 
                        "Reservation cancelled for Room Number: " + roomNumber,
                        "Cancellation Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                        "Room is already available!",
                        "Cancellation Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, 
                    "Invalid room number!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String[] getAvailableRooms() {
        StringBuilder available = new StringBuilder();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                available.append(room.getRoomNumber()).append(",");
            }
        }
        return available.length() > 0 ? available.toString().split(",") : new String[]{};
    }

    public String[] getReservedRooms() {
        StringBuilder reserved = new StringBuilder();
        for (Room room : rooms) {
            if (!room.isAvailable()) {
                reserved.append(room.getRoomNumber()).append(",");
            }
        }
        return reserved.length() > 0 ? reserved.toString().split(",") : new String[]{};
    }

    public String[] getDates() {
        return dates;
    }
}


public class HotelReservationGUI extends JFrame {
    private HotelReservationSystem hotel;

    public HotelReservationGUI() {
        hotel = new HotelReservationSystem("November 26", "November 27");

        setTitle("Greensboro Hotel Reservation System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        
        JButton reserveButton = new JButton("Reserve Room");
        JButton cancelButton = new JButton("Cancel Reservation");
        JButton checkButton = new JButton("Check Availability");
        JButton exitButton = new JButton("Exit");

        
        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] dates = hotel.getDates();
                String selectedDate = (String) JOptionPane.showInputDialog(
                        null, "Select a date for reservation:", 
                        "Reserve Room", JOptionPane.QUESTION_MESSAGE, 
                        null, dates, dates[0]);

                if (selectedDate != null) {
                    String[] availableRooms = hotel.getAvailableRooms();
                    if (availableRooms.length == 0) {
                        JOptionPane.showMessageDialog(null, 
                                "No rooms available for the selected date.", 
                                "Reservation Error", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    String selectedRoom = (String) JOptionPane.showInputDialog(
                            null, "Select a room for reservation:", 
                            "Reserve Room", JOptionPane.QUESTION_MESSAGE, 
                            null, availableRooms, availableRooms[0]);

                    if (selectedRoom != null) {
                        try {
                            int roomNumber = Integer.parseInt(selectedRoom);
                            hotel.ReserveRoom(selectedDate, roomNumber);
                        } catch (NoRoomException ex) {
                            JOptionPane.showMessageDialog(null, 
                                    ex.getMessage(),
                                    "Reservation Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String[] reservedRooms = hotel.getReservedRooms();
                if (reservedRooms.length == 0) {
                    JOptionPane.showMessageDialog(null, 
                            "No rooms reserved yet!", 
                            "Cancellation Error", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String selectedRoom = (String) JOptionPane.showInputDialog(
                        null, "Select a room to cancel reservation:", 
                        "Cancel Reservation", JOptionPane.QUESTION_MESSAGE, 
                        null, reservedRooms, reservedRooms[0]);

                if (selectedRoom != null) {
                    int roomNumber = Integer.parseInt(selectedRoom);
                    hotel.CancelReservation(roomNumber);
                }
            }
        });

        checkButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String[] availableRooms = hotel.getAvailableRooms();
                if (availableRooms.length == 0) {
                    JOptionPane.showMessageDialog(null, 
                            "No rooms available.", 
                            "Availability Status", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, 
                            "Available Rooms: " + String.join(", ", availableRooms), 
                            "Availability Status", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, 
                        "Thank you for using the Greensboro Hotel Reservation System. Goodbye!",
                        "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        
        add(reserveButton);
        add(cancelButton);
        add(checkButton);
        add(exitButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new HotelReservationGUI();
    }
}
