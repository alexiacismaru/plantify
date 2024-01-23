package be.kdg.integration.plantifybackend.presentation;

import be.kdg.integration.plantifybackend.domain.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * controller for index.html
 */
@Controller
public class IndexController {

    /**
     * shows a sign in button if the user is not logged in
     * @param httpSession used to check on the user attribute
     * @param model used to add the loggedInOrNot attribute
     * @return index.html
     */
    @GetMapping("/index")
    public String showIndexView(HttpSession httpSession,Model model) {
        Client client = (Client) httpSession.getAttribute("user");
        if (client != null){
            model.addAttribute("loggedInOrNot",true);
        }else {
            model.addAttribute("loggedInOrNot",false);
        }
        return "index";
    }
}
