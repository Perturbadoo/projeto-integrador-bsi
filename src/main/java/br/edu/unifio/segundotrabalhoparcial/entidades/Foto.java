package br.edu.unifio.segundotrabalhoparcial.entidades;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;


@Entity
@Data
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column
    private Byte foto;

    @Column(length = 150, nullable = false, unique = true)
    private String comentario;


}