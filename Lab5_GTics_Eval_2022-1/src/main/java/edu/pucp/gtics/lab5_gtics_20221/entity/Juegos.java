package edu.pucp.gtics.lab5_gtics_20221.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "juegos")
public class Juegos implements Serializable {

    @Id
    private int idjuego;

    @Size(min = 3, max = 45, message = "Debe contener entre 3 y 45 caracteres")
    private String nombre;

    @Size(min = 3, max = 400, message = "Debe contener entre 3 y 400 caracteres")
    private String descripcion;

    @DecimalMin(value = "10" , message = "Valor mínimo 10")
    @DecimalMax(value = "500" , message = "Valor máximo 500")
    private double precio;

    private String image;

    @ManyToOne
    @JoinColumn(name = "idplataforma")
    @Valid
    private Plataformas plataforma;

    @ManyToOne
    @JoinColumn(name = "iddistribuidora")
    @Valid
    private Distribuidoras distribuidora;

    @ManyToOne
    @JoinColumn(name = "idgenero")
    @Valid
    private Generos genero;

    public int getIdjuego() {
        return idjuego;
    }

    public void setIdjuego(int idjuego) {
        this.idjuego = idjuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Plataformas getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataformas plataforma) {
        this.plataforma = plataforma;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Distribuidoras getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(Distribuidoras distribuidora) {
        this.distribuidora = distribuidora;
    }

    public Generos getGenero() {
        return genero;
    }

    public void setGenero(Generos genero) {
        this.genero = genero;
    }
}
