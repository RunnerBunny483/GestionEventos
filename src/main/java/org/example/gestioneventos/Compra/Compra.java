package org.example.gestioneventos.Compra;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.gestioneventos.Evento.Evento;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "dni", nullable = false, length = 20)
    @Pattern(regexp = "^[0-9]{8}[A-Za-z]$", message = "Formato del DNI incorrecto")
    private String dni;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre", nullable = false)
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$", message = "El nombre debe comenzar con mayúscula y solo puede contener letras")
    private String nombre;

    @Size(max = 255)
    @NotNull
    @Column(name = "apellidos", nullable = false)
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$", message = "El apellido debe comenzar con mayúscula y solo contener letras")
    private String apellidos;

    @NotNull
    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @Size(max = 16)
    @NotNull
    @Column(name = "numero_tarjeta", nullable = false, length = 16)
    @Pattern(regexp = "^[0-9]{16}$", message = "El numero de la tarjeta solo pueden ser 16 numeros seguidos")
    private String numeroTarjeta;

    //@JsonIgnore
    //@JsonBackReference
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento idEvento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if(esDNICorrecto(dni)){
            this.dni = dni;
        }
    }

    //Método para la letra del dni
    public boolean esDNICorrecto(String dni) {
        if (dni == null || !dni.matches("^[0-9]{8}[A-Za-z]$")) {
            return false;
        }

        String numeroDni = dni.substring(0, 8);
        char letraDni = dni.charAt(8);

        int dniNumero = Integer.parseInt(numeroDni);

        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

        char letraCorrecta = letras.charAt(dniNumero % 23);
        return Character.toUpperCase(letraDni) == letraCorrecta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
    }

    public Compra() {
    }

    public Compra(Integer id, String dni, String nombre, String apellidos, LocalDate fechaCompra, String numeroTarjeta, Evento idEvento) {
        this.id = id;
        setDni(dni);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaCompra = fechaCompra;
        this.numeroTarjeta = numeroTarjeta;
        this.idEvento = idEvento;
    }

    public Compra(String dni, String nombre, String apellidos, LocalDate fechaCompra, String numeroTarjeta, Evento idEvento) {
        setDni(dni);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaCompra = fechaCompra;
        this.numeroTarjeta = numeroTarjeta;
        this.idEvento = idEvento;
    }
}