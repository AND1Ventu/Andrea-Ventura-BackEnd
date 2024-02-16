package com.example.GestioneDispositivi.controller;

import com.example.GestioneDispositivi.exception.BadRequestException;
import com.example.GestioneDispositivi.model.Dispositivo;
import com.example.GestioneDispositivi.model.DispositivoRequest;
import com.example.GestioneDispositivi.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping("/dispositivi")
    public Page<Dispositivo> getAll(Pageable pageable){

        return dispositivoService.findAllDevices(pageable);
    }

    @GetMapping("/dispositivi/{id}")
    public Dispositivo getDevicesById(@PathVariable int id){
        return dispositivoService.findDeviceById(id);

    }
    @PostMapping("/dispositivi")
    public Dispositivo saveDevice(
            @RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return dispositivoService.saveDevice(dispositivoRequest);
    }

    @PutMapping("/dispositivi/{id}")
    public Dispositivo updateBlogPost(
            @PathVariable int id,
            @RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return dispositivoService.updateDevice(id, dispositivoRequest);
    }

    @DeleteMapping("/dispositivi/{id}")
    public void deleteDevice(@PathVariable int id){
        dispositivoService.deleteDevice(id);
    }

}
