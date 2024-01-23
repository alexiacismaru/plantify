package be.kdg.integration.plantifybackend.presentation;

import be.kdg.integration.plantifybackend.domain.Client;
import be.kdg.integration.plantifybackend.service.ArduinoService;
import be.kdg.integration.plantifybackend.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * Controller for dashboard.html
 */
@Controller
public class DashboardController {
    PlantService plantService;
    ArduinoService arduinoService;

    @Autowired
    public DashboardController(PlantService plantService, ArduinoService arduinoService) {
        this.plantService = plantService;
        this.arduinoService = arduinoService;
    }

    /**
     * checks if the user is logged in. if this is true, the user's plant are shown
     * @param httpSession used to get the user attribute
     * @param model used to add the plants and set loggedInOrNot to true if user is logged in
     * @return returns dashboard.html if user is logged in, otherwise error.html is returned
     */
    @GetMapping("/dashboard")
    public String showIndexView(HttpSession httpSession, Model model) {
        Client client = (Client) httpSession.getAttribute("user");
        if (client != null) {
            String email= ((Client)httpSession.getAttribute("user")).getEmail();
            model.addAttribute("loggedInOrNot",true);
            model.addAttribute("plants", plantService.readPlants().stream()
                    .filter(plant -> plant.getEmailUser()
                            .equals(email)).toList());
            return "dashboard";
        }else {
            return "error";
        }
    }
}
