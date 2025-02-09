package org.example.gestioneventos.Compra;

import org.example.gestioneventos.Evento.Evento;
import org.example.gestioneventos.Evento.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class ServicioCompra {
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private EventoRepository eventoRepository;

    public Compra realizarCompra(Compra compra) {
        //El evento existe
        Evento evento = eventoRepository.findById(compra.getIdEvento().getId()).orElse(null);

        //Si da null es porque no existe, tira una excepcion
        if (evento == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado");
        }

//        //El evento existe
//        Evento evento = eventoRepository.findById(compra.getIdEvento().getId())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado"));

        //Si la fecha del evento ha pasado
        if (evento.getFechaEvento().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El evento ya ha pasado");
        }

        //Si hay entradas disponibles
        if(evento.getEntradasDisponibles()==0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sold Out");
        }

        //Si se hace una compra que se reste una entrada
        evento.setEntradasDisponibles(evento.getEntradasDisponibles()-1);
        eventoRepository.save(evento);

        return compraRepository.save(compra);
    }

}
