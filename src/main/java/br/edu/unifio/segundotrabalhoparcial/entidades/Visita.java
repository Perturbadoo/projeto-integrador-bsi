package br.edu.unifio.segundotrabalhoparcial.entidades;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;


@Entity
@Data
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @Column(length = 20, nullable = false, unique = true)
    private String status;

}
