package com.example.t;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
@Controller
public class UsersController {
    @Autowired
    private UsersRepository entityRepository;



    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Users> entities = entityRepository.findAll();
        modelAndView.addObject("entities", entities);
        return modelAndView;
    }
    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("add");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addEntity(@ModelAttribute Users entity,RedirectAttributes redirectAttributes) {
        if (!entityRepository.existsByFirstNameAndSecondNameAndLastName( entity.getFirstName(), entity.getSecondName(),entity.getLastName())) {
            entityRepository.save(entity);
            redirectAttributes.addFlashAttribute("successMessage", "Entity updated successfully.");
        }else {
            redirectAttributes.addFlashAttribute("errorMessage", "An entity with these parameters already exists");
        }
        return "redirect:/add";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editEntity(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Users entity = entityRepository.findById(id).orElse(null);
        modelAndView.addObject("entity", entity);
        return modelAndView;
    }

    @PostMapping("/update")
    public String updateEntity( @ModelAttribute Users updatedEntity,RedirectAttributes redirectAttributes) {
        Users entity = entityRepository.findById(updatedEntity.getId()).orElse(null);
        if (entity != null ) {

            entity.setSecondName(updatedEntity.getSecondName());
            entity.setFirstName(updatedEntity.getFirstName());
            entity.setLastName(updatedEntity.getLastName());
            if (!entityRepository.existsByFirstNameAndSecondNameAndLastName(entity.getFirstName(),entity.getSecondName(),  entity.getLastName()) || entity.equals(updatedEntity)) {
                entityRepository.save(entity);
                redirectAttributes.addFlashAttribute("successMessage", "Entity updated successfully.");
            }else {
                redirectAttributes.addFlashAttribute("errorMessage", "An entity with these parameters already exists");
                return "redirect:/edit/"+entity.getId();
            }
        }
        return "redirect:/";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteEntity(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        entityRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Entity successfully deleted.");
        return "redirect:/";
    }


    @PostMapping("/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            if (!file.isEmpty()) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] attributes = line.split(",");
                        Users entity = new Users();
                        entity.setSecondName(attributes[0]);
                        entity.setFirstName(attributes[1]);
                        entity.setLastName(attributes[2]);


                        if (!entityRepository.existsByFirstNameAndSecondNameAndLastName(entity.getFirstName(),entity.getSecondName(), entity.getLastName())) {
                            entityRepository.save(entity);
                        }
                    }
                }
                redirectAttributes.addFlashAttribute("successMessage", "CSV file uploaded successfully.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Uploaded file is empty.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error occurred while uploading CSV file: " + e.getMessage());
        }
        return "redirect:/";
    }
}
