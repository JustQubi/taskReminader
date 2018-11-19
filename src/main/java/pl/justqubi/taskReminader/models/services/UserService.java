package pl.justqubi.taskReminader.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.justqubi.taskReminader.models.UserSession;
import pl.justqubi.taskReminader.models.enteties.UserEntity;
import pl.justqubi.taskReminader.models.forms.LoginForm;
import pl.justqubi.taskReminader.models.forms.RegisterForm;
import pl.justqubi.taskReminader.models.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserSession userSession;
    final PasswordHashingService passwordHashingService;


    @Autowired
    public UserService(UserRepository userRepository, UserSession userSession, PasswordHashingService passwordHashingService) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.passwordHashingService = passwordHashingService;
    }

    public boolean checkIfLoginExists(String login) {
        return userRepository.existsByLogin(login);
    }

    public void addUser(RegisterForm registerForm) {
        System.out.println(registerForm);


        UserEntity newUser = new UserEntity();
        newUser.setLogin(registerForm.getLogin());
        newUser.setPassword(passwordHashingService.hash(registerForm.getPassword()));
        newUser.setName(registerForm.getName());
        newUser.setSurname(registerForm.getSurname());
        newUser.setZipCode(registerForm.getZipCode());
        newUser.setCity(registerForm.getCity());

        userRepository.save(newUser);
    }

    public boolean tryLogin(LoginForm loginForm) {
        Optional<UserEntity> userOptional = userRepository.getUserByLogin(loginForm.getLogin());
        if (userOptional.isPresent()) {
            if (passwordHashingService.matches(loginForm.getPassword(), userOptional.get().getPassword())) {

                userSession.setLogin(true);
                userSession.setUserEntity(userOptional.get());
            }
        }
        return userSession.isLogin();
    }
}