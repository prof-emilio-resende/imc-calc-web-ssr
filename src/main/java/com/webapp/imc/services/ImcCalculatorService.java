package com.webapp.imc.services;

import com.webapp.imc.models.Person;

import org.springframework.stereotype.Service;

@Service
public class ImcCalculatorService implements ImcCalculatorServiceTemplate {

    @Override
    public void calculateImc(Person person) {
        if (person == null) return;
        var imc = person.getWeight() / (Math.pow(person.getHeight(), 2));
        person.setImc(imc);

        if (imc < 18.50) person.setImcDescription("Magreza");
        else if (imc >= 18.50 && imc < 25.00) person.setImcDescription("Normal");
        else if (imc >= 25 && imc < 30.00) person.setImcDescription("Sobrepeso");
        else  person.setImcDescription("Obesidade");
    }
    
}
