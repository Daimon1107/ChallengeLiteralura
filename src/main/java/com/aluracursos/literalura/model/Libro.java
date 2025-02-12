package com.aluracursos.literalura.model;

import com.aluracursos.literalura.enums.IdiomasEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
 //   @ManyToOne
    @JoinColumn(name = "autor_id")
    @JsonManagedReference
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private IdiomasEnum idiomas;
    private Double numeroDeDescargas;

    public Libro(){}

    public Libro(DatosLibro datosLibro) {
        this.id = (long) datosLibro.id();
        this.titulo = datosLibro.titulo();
        if (datosLibro.autor() != null && !datosLibro.autor().isEmpty()) {
            this.autor = new Autor(datosLibro.autor().get(0));
        }
        if (datosLibro.idiomas() != null && !datosLibro.idiomas().isEmpty()) {
            try {
                this.idiomas = IdiomasEnum.fromString(datosLibro.idiomas().get(0));
            } catch (IllegalArgumentException e) {
                System.out.println("Idioma no reconocido: " + datosLibro.idiomas().get(0));
                this.idiomas = IdiomasEnum.ES; // Idioma español por defecto
            }
        }
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
    public Autor getAutor() {
        return autor;
    }

    public IdiomasEnum getIdiomas() {
        return idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setIdiomas(IdiomasEnum idiomas) {
        this.idiomas = idiomas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return  " Titulo='" + titulo + '\'' +
                ", Numero de descargas=" + numeroDeDescargas + '\'' +
                ", Idiomas=" + idiomas + '\'' +
                ", Datos del autor='" + autor ;

    }
}
