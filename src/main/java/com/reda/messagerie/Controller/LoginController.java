package com.marwane.messagerie.Controller;

import com.marwane.messagerie.Model.Users;
import com.marwane.messagerie.Repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/index")
    public String showLoginForm() {
        return "index";
    }
    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }

    @PostMapping("/index")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        // Fetch user from database based on the provided username
        Optional<Users> optionalUser = usersRepository.findByUsername(username);

        // Check if the user exists and the password matches
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();  // Get the user from the Optional
            if (user.getPassword().equals(password)) {
                return "redirect:/chat"; // redirect to chat
            }
        }

        model.addAttribute("error", "Invalid username or password");
        return "index"; // return to the login page with error message
    }
}
