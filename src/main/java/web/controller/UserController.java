package web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import org.springframework.stereotype.Controller;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping( "/")
    public String allUsers(Model model) {
        model.addAttribute("usersList",userService.allUsers());
        return "usersPage";
    }

    @GetMapping("/add")
    public String addPage() {
        return "addUser";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user",userService.getById(id));
        return "editPage";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/";
    }

}
