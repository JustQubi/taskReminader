package pl.justqubi.taskReminader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.justqubi.taskReminader.models.forms.LoginForm;
import pl.justqubi.taskReminader.models.forms.RegisterForm;
import pl.justqubi.taskReminader.models.services.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/add")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "registerForm";
    }

    @PostMapping("/user/add")
    public String getDataFromRegister(@ModelAttribute @Valid RegisterForm registerForm,
                                      BindingResult bindingResult,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registerInfo", "Błędne dane");
            return "registerForm";
        }

        if (userService.checkIfLoginExists(registerForm.getLogin())) {
            model.addAttribute("registerInfo", "Login zajety");
            return "registerForm";
        }

        userService.addUser(registerForm);
        model.addAttribute("registerInfo", "Zarejestrowano poprawnie!");
        return "registerForm";
    }

    @GetMapping("/user/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "loginForm";
    }

    @PostMapping("/user/login")
    public String loginData(@ModelAttribute LoginForm loginForm,
                            Model model) {
        if (userService.tryLogin(loginForm)) {
            model.addAttribute("loginInfo", "Zalogowano!");
            return "loginForm";
        }

        model.addAttribute("loginInfo", "Niepoprawne dane");
        return "loginForm";
    }
}