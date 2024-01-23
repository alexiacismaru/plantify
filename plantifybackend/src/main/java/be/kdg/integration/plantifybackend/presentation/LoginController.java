package be.kdg.integration.plantifybackend.presentation;

import be.kdg.integration.plantifybackend.domain.Client;
import be.kdg.integration.plantifybackend.presentation.viewModel.LoginViewModel;
import be.kdg.integration.plantifybackend.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * controller for login.html
 */
@Controller
public class LoginController {
    ClientService clientService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public LoginController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * user is not used right now, future implementation
     * @param httpSession redundant
     * @param model redundant
     * @return login.html
     */
    @GetMapping("/login")
    public String showUserView(HttpSession httpSession,Model model){
        Client client = (Client) httpSession.getAttribute("user");
        model.addAttribute("loggedInOrNot",false);
        model.addAttribute("loginViewModel", new LoginViewModel());
        return "login";
    }

    /**
     * handles the login form
     * @param httpSession used to pass on the logged-in user
     * @param loginViewModel used to check the validity of the form data
     * @param errors used to return login.html with errors if anything is invalidated in the viewmodel
     * @return depends on errors and if the user exists
     */
    @PostMapping("/login")
    public String checkUser(HttpSession httpSession, @Valid @ModelAttribute("loginViewModel") LoginViewModel
            loginViewModel, BindingResult errors){
        logger.debug("Checking user");
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                logger.error(error.toString());
            });
            return "login";
        }

        Client clientToCheck = new Client(loginViewModel.getEmail(), loginViewModel.getPassword());
        if(clientService.checkClient(clientToCheck)){
            httpSession.setAttribute("user", clientToCheck);

            return "redirect:/dashboard";
        }
        else{
            return "redirect:/login?error";
        }

    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession, Model model){
        httpSession.setAttribute("user", null);
        model.addAttribute("loggedInOrNot",false);
        model.addAttribute("loginViewModel", new LoginViewModel());
        return "redirect:/index";
    }
}
