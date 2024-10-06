// src/main/java/com/example/cinemaroomapp/services/SeatService.java
package pl.rafalzbanski.cinema_room.services;

import org.springframework.stereotype.Service;
import pl.rafalzbanski.cinema_room.models.Seat;

import java.util.*;

@Service
public class SeatService {
    private List<Seat> seats;

    public SeatService() {
        seats = new ArrayList<>();
        initializeSeats();
    }

    private void initializeSeats() {
        for (int row = 1; row <= 9; row++) {
            for (int column = 1; column <= 9; column++) {
                seats.add(new Seat(row, column));
            }
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public boolean purchaseSeat(int row, int column) {
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                if (seat.isAvailable()) {
                    seat.setAvailable(false);
                    return true;
                } else {
                    return false; // Seat already purchased
                }
            }
        }
        return false; // Seat not found
    }

    public boolean returnSeat(String id) {
        String[] parts = id.split("_");
        if (parts.length != 2) return false;

        int row, column;
        try {
            row = Integer.parseInt(parts[0]);
            column = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return false;
        }

        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                if (!seat.isAvailable()) {
                    seat.setAvailable(true);
                    return true;
                } else {
                    return false; // Seat is already available
                }
            }
        }
        return false; // Seat not found
    }

    public int getAvailableSeatsCount() {
        return (int) seats.stream().filter(Seat::isAvailable).count();
    }

    public int getPurchasedSeatsCount() {
        return (int) seats.stream().filter(seat -> !seat.isAvailable()).count();
    }

    public int getEarnedMoney() {
        return seats.stream()
                .filter(seat -> !seat.isAvailable())
                .mapToInt(Seat::getPrice)
                .sum();
    }

    public Map<Integer, List<Seat>> getSeatsByRow() {
        Map<Integer, List<Seat>> seatsByRow = new TreeMap<>();
        for (Seat seat : seats) {
            seatsByRow.computeIfAbsent(seat.getRow(), k -> new ArrayList<>()).add(seat);
        }
        return seatsByRow;
    }
}
