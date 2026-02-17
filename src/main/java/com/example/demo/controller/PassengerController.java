package com.example.demo.controller;

import com.example.demo.model.Passenger;
import com.example.demo.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PassengerController {

    @Autowired
    private PassengerRepository repo;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("passengers", repo.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Passenger passenger) {
        repo.save(passenger);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
    	model.addAttribute("passenger", repo.findById(id));
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/";
    }
}
