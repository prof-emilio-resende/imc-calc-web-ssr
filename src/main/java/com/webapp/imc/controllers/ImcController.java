package com.webapp.imc.controllers;

import java.util.ArrayList;
import java.util.List;

import com.webapp.imc.models.ImcLayer;
import com.webapp.imc.models.Person;
import com.webapp.imc.services.ImcCalculatorServiceTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImcController {
    private List<ImcLayer> imcLayers;
    @Autowired
    private ImcCalculatorServiceTemplate imcService;

    public ImcController() {
        super();
        this.imcLayers = new ArrayList<>();
        this.imcLayers.add(new ImcLayer(0.00, 18.49, "Magreza"));
        this.imcLayers.add(new ImcLayer(18.50, 24.99, "Normal"));
        this.imcLayers.add(new ImcLayer(25.00, 29.99, "Sobrepeso"));
        this.imcLayers.add(new ImcLayer(30.00, 99.99, "Obesidade"));
    }

    @RequestMapping(value="/")
    public String index(Model model) {
        model.addAttribute("imcLayers", this.imcLayers);
        model.addAttribute("person", new Person());

        return "index";
    }

    @PostMapping(value="/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String calculate(Person input, Model model) {
        model.addAttribute("imcLayers", this.imcLayers);
        imcService.calculateImc(input);
        model.addAttribute("person", input);

        return "index";
    }
}
