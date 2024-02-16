package com.example.GestioneDispositivi.controller;

import com.cloudinary.Cloudinary;
import com.example.GestioneDispositivi.exception.BadRequestException;
import com.example.GestioneDispositivi.model.Dipendente;
import com.example.GestioneDispositivi.model.DipendenteRequest;
import com.example.GestioneDispositivi.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.HashMap;

@RestController
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/dipendenti")
    public Page<Dipendente> getAll(Pageable pageable){
        return dipendenteService.findAllEmployees(pageable);
    }

    @GetMapping("/dipendenti/{id}")
    public Dipendente getEmployeeById(@PathVariable int id){
        return dipendenteService.findById(id);
    }

    @PostMapping("/dipendenti")
    public Dipendente saveEmployee(@RequestBody @Validated DipendenteRequest dipendenteRequest,
                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return dipendenteService.saveEmployee(dipendenteRequest);
    }

    @PutMapping("/dipendenti/{id}")
    public Dipendente updateEmployee(@PathVariable int id,
                                     @RequestBody @Validated DipendenteRequest dipendenteRequest,
                                     BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException((bindingResult.getAllErrors().toString()));
        }
        return dipendenteService.updateEmployee(id, dipendenteRequest);
    }

    @DeleteMapping("/dipendenti/{id}")
    public void deleteEmployee(@PathVariable int id){
        dipendenteService.deleteEmployee(id);
    }

    @PatchMapping("/dipendenti/{id}/upload")
    public Dipendente uploadAvatar(
            @PathVariable int id,
            @RequestParam("upload") MultipartFile file) throws IOException{
        return dipendenteService.uploadAvatar(
                id,
                (String) cloudinary.uploader().upload(
                        file.getBytes(),
                        new HashMap()).get("url"));


    }
}
