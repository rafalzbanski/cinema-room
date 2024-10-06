package pl.rafalzbanski.cinema_room.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.rafalzbanski.cinema_room.services.SeatService;

@Controller
public class SeatController {

    @Autowired
    private SeatService seatService;

    // New mapping for the welcome page
    @GetMapping("/")
    public String welcomePage() {
        return "welcome";
    }

    // Updated mapping for the seats page
    @GetMapping("/seats")
    public String viewSeats(Model model) {
        model.addAttribute("seatsByRow", seatService.getSeatsByRow());
        return "seats";
    }

    @PostMapping("/purchase")
    public String purchaseSeat(@RequestParam("row") int row,
                               @RequestParam("column") int column,
                               Model model) {
        boolean success = seatService.purchaseSeat(row, column);
        if (success) {
            model.addAttribute("message", "Seat purchased successfully!");
        } else {
            model.addAttribute("message", "Seat already purchased or invalid seat!");
        }
        model.addAttribute("seatsByRow", seatService.getSeatsByRow());
        return "seats";
    }

    @GetMapping("/return")
    public String showReturnTicketPage(Model model) {
        return "return_ticket";
    }

    @PostMapping("/return")
    public String returnSeat(@RequestParam("id") String id, Model model) {
        boolean success = seatService.returnSeat(id);
        if (success) {
            model.addAttribute("message", "Seat returned successfully!");
        } else {
            model.addAttribute("message", "Invalid seat ID or seat is already available!");
        }
        return "return_ticket";
    }
}
