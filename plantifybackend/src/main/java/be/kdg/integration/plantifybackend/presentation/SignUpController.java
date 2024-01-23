package be.kdg.integration.plantifybackend.presentation;

import be.kdg.integration.plantifybackend.domain.Client;
import be.kdg.integration.plantifybackend.presentation.viewModel.SignUpViewModel;
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
 * controller for createUser.html
 */
@Controller
@RequestMapping("/createUser")
public class SignUpController {
    ClientService clientService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SignUpController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * shows createUser.html
     * @param model used to pass on loggedInOrNot, signUpViewModel and loggedInOrNot
     * @return createuser.html
     */
    @GetMapping
    public String showSignUpView(Model model) {
        model.addAttribute("loggedInOrNot",false);
        model.addAttribute("signUpViewModel", new SignUpViewModel());
        model.addAttribute("loggedInOrNot",false);
        return "createUser";
    }


    /**
     * handles form input
     * @param httpSession used to set the current user
     * @param signUpViewModel used to check the form input for basic errors
     * @param errors used to return createuser.html with errors if anything is invalidated in the viewmodel
     * @return createuser.html if any errors in viewmodel, otherwise dashboard.html
     */
    @PostMapping
    public String signUp(HttpSession httpSession, @Valid @ModelAttribute("signUpViewModel") SignUpViewModel signUpViewModel, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                logger.error(error.toString());
            });
            return "createUser";
        }
        logger.debug("Creating new user");
        Client client = clientService.addClient(signUpViewModel.getEmail(), signUpViewModel.getPassword());
        httpSession.setAttribute("user", client);
        return "redirect:/dashboard";
    }
}
