package org.example.gestioneventos.Compra;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/compras")

public class CompraController {

    @Autowired
    private ServicioCompra servicioCompra;

    CompraRepository compraRepository;

    public CompraController() {}

    @Autowired
    public CompraController(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    //GET ALL
    @RequestMapping
    public ResponseEntity<List<Compra>> getCompras() {
        List<Compra> comprasLista = compraRepository.findAll();
        return ResponseEntity.ok(comprasLista);
    }

    //GET BY ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Compra> getCompra(@PathVariable Integer id) {
        Compra compra = this.compraRepository.findById(id).get();
        return ResponseEntity.ok(compra);
    }

    //POST
//    @PostMapping("/compra")
//    public ResponseEntity<Compra> addCompra(@Valid @RequestBody Compra compra) {
//        Compra compraPersisted = compraRepository.save(compra);
//        return ResponseEntity.ok(compraPersisted);
//    }
    @PostMapping("/compra")
    public ResponseEntity<Object> realizarCompra(@RequestBody @Valid Compra compra) {
        try {
            // Guardar la compra en la base de datos
            Compra compraRealizada = compraRepository.save(compra);

            // Procesar la compra (validaciones, restar entradas, etc.)
            Compra compraPersistida = servicioCompra.realizarCompra(compraRealizada);

            // Si todo está bien, devolver la compra con el estado HTTP 200
            return ResponseEntity.ok(compraPersistida);
        } catch (ResponseStatusException e) {
            // Si ocurre un error (por ejemplo, entradas agotadas), devolver un mapa con el mensaje y el código de estado
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getReason());
            return new ResponseEntity<>(errorResponse, e.getStatusCode());
        }
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable Integer id, @Valid @RequestBody Compra compra) {
        Compra compraPersistida = compraRepository.save(compra);
        return ResponseEntity.ok().body(compraPersistida);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompra(@Valid @PathVariable Integer id) {
        compraRepository.deleteById(id);
        String mensaje = "Compra eliminada con exito";
        return ResponseEntity.ok().body(mensaje);
    }
}
