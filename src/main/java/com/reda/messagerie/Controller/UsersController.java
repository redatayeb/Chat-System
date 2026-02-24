package com.marwane.messagerie.Controller;

import com.marwane.messagerie.Model.Users;
import com.marwane.messagerie.Repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;


    // Show form to add new user
    @GetMapping("/form")
    public String showForm() {
        return "signUp";  // Render the user form
    }


    // Save user to the database
    @PostMapping("/save")
    public String saveUser(@ModelAttribute Users user) {
        // Attribuer le rôle par défaut si non défini
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }
        usersRepository.save(user);
        return "redirect:/index";
    }


    // Show the list of users
    @GetMapping("/list")
    public String showUsersList(Model model) {
        List<Users> users = usersRepository.findAll();
        model.addAttribute("users", users);  // Add users to the model
        return "users-list";  // Render the users list page
    }

    // Show the edit form with user data
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Users user = usersRepository.findById(id).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);  // Pass the user data to the form
            return "edit-user-form";  // Render the edit form
        }
        return "redirect:/users/list";  // Redirect to list if user not found
    }

    // Update user data
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute Users updatedUser) {
        return usersRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());

            usersRepository.save(user);  // Save the updated user data to the database
            return "redirect:/users/list";  // Redirect to the list page after update
        }).orElse("redirect:/users/list");  // Redirect if user not found
    }
    // Delete a user by ID
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        // Check if the user exists in the database
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);  // Delete the user by ID
        }
        return "redirect:/users/list";  // Redirect back to the users list
    }


}
