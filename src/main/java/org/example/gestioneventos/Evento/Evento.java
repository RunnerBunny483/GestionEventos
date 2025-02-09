package org.example.gestioneventos.Evento;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre", nullable = false)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ0-9 ]+$", message = "Solo se permiten caracteres alfanuméricos")
    private String nombre;

    @NotNull
    @Column(name = "fecha_evento", nullable = false)
    private LocalDate fechaEvento;

    @NotNull
    @Column(name = "entradas_disponibles", nullable = false)
    private Integer entradasDisponibles;

    @NotNull
    @Column(name = "precio", nullable = false)
    private Double precio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDate fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Integer getEntradasDisponibles() {
        return entradasDisponibles;
    }

    public void setEntradasDisponibles(Integer entradasDisponibles) {
        if(entradasDisponibles < 0){
            this.entradasDisponibles = 0;
        } else if(entradasDisponibles >= 0){
            this.entradasDisponibles = entradasDisponibles;
        }
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Evento() {
    }

    public Evento(Integer id, String nombre, LocalDate fechaEvento, Integer entradasDisponibles, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.fechaEvento = fechaEvento;
        setEntradasDisponibles(entradasDisponibles);
        this.precio = precio;
    }

    public Evento(String nombre, LocalDate fechaEvento, Integer entradasDisponibles, Double precio) {
        this.nombre = nombre;
        this.fechaEvento = fechaEvento;
        setEntradasDisponibles(entradasDisponibles);
        this.precio = precio;
    }


}