package ru.sherievtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import ru.sherievtest.DAO.PeopleDAO;
import ru.sherievtest.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleDAO peopleDAO;
    private final SpringResourceTemplateResolver springResourceTemplateResolver;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO, SpringResourceTemplateResolver springResourceTemplateResolver) {
        this.peopleDAO = peopleDAO;
        this.springResourceTemplateResolver = springResourceTemplateResolver;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", peopleDAO.getAllPeople());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleDAO.getPerson(id));
        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping
    public String addPerson(@ModelAttribute("person") Person person) {
        peopleDAO.add(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleDAO.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String editDao(@ModelAttribute("person") Person person,
                          @PathVariable("id") int id) {
        peopleDAO.edit(person, id);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {
        peopleDAO.delete(id);
        return "redirect:/people";
    }

}

