package com.example.GestioneDispositivi.service;

import com.example.GestioneDispositivi.exception.NotFoundException;
import com.example.GestioneDispositivi.model.Dipendente;
import com.example.GestioneDispositivi.model.Dispositivo;
import com.example.GestioneDispositivi.model.DispositivoRequest;
import com.example.GestioneDispositivi.repository.DispositivoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DipendenteService dipendenteService;

    public Page<Dispositivo> findAllDevices(Pageable pageable){
        return dispositivoRepository.findAll(pageable);
    }

    public Dispositivo findDeviceById(int id) throws NotFoundException{
        return dispositivoRepository.findById(id).
                orElseThrow(()-> new NotFoundException("Dispositivo con id="+id+" non trovato"));
    }

    public Dispositivo saveDevice(DispositivoRequest dispositivoRequest) throws NotFoundException{
        Dipendente dipendente = dipendenteService.findById(dispositivoRequest.getDipendente().getId());

        Dispositivo dispositivo = new Dispositivo(
                dispositivoRequest.getTipo(),
                dispositivoRequest.getStato(),
                dipendente);

        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo updateDevice(int id, DispositivoRequest dispositivoRequest) throws NotFoundException{
        Dispositivo device = findDeviceById(id);

        Dipendente dipendente = dipendenteService.findById(dispositivoRequest.getDipendente().getId());

        device.setTipo(dispositivoRequest.getTipo());
        device.setStato(dispositivoRequest.getStato());
        device.setDipendente(dipendente);

        return dispositivoRepository.save(device);
    }

    public void deleteDevice(int id) throws NotFoundException{
        Dispositivo device = findDeviceById(id);
        dispositivoRepository.delete(device);
    }

    public Dispositivo setDeviceToUser(int deviceId, int userId) {
        Dispositivo dispositivo = findDeviceById(deviceId);
        Dipendente dipendente = dipendenteService.findById(userId);

        if (dispositivo != null && dipendente != null) {
            dispositivo.setDipendente(dipendente);
            return saveDevice(dispositivo);
        } else {
            throw new EntityNotFoundException("Dispositivo o Dipendente non trovato");
        }
    }

}
