package org.example.gestioneventos.Evento;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.Cacheable;


import java.util.List;

@RestController
@RequestMapping("/eventos")

public class EventoController {

    EventoRepository eventoRepository;

    public EventoController() {}

    @Autowired
    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    //GET ALL
    @GetMapping
    public ResponseEntity <List<Evento>> getCompras() {
        List<Evento> eventoLista = eventoRepository.findAll();
        return ResponseEntity.ok(eventoLista);
    }

    //GET BY ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Evento> getEvento(@PathVariable Integer id) {
        Evento evento = this.eventoRepository.findById(id).get();
        return ResponseEntity.ok(evento);
    }

    //POST
    @PostMapping("/evento")
    public ResponseEntity<Evento> addEvento(@Valid @RequestBody Evento evento) {
        Evento eventoPersistido = this.eventoRepository.save(evento);
        return ResponseEntity.ok(eventoPersistido);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Integer id, @Valid @RequestBody Evento evento) {
        Evento eventoPersistido = eventoRepository.save(evento);
        return ResponseEntity.ok().body(eventoPersistido);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvento(@Valid @PathVariable Integer id) {
        eventoRepository.deleteById(id);
        String mensaje = "Evento removido com sucesso";
        return ResponseEntity.ok().body(mensaje);
    }
}
