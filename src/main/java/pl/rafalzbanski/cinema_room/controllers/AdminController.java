package pl.rafalzbanski.cinema_room.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.rafalzbanski.cinema_room.services.SeatService;


@Controller
public class AdminController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/statistics")
    public String viewStatistics(Model model) {
        int availableSeats = seatService.getAvailableSeatsCount();
        int purchasedSeats = seatService.getPurchasedSeatsCount();
        int earnedMoney = seatService.getEarnedMoney();

        model.addAttribute("availableSeats", availableSeats);
        model.addAttribute("purchasedSeats", purchasedSeats);
        model.addAttribute("earnedMoney", earnedMoney);

        return "statistics";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
