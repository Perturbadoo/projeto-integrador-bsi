package br.edu.unifio.segundotrabalhoparcial.entidades;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Vendedor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 50, nullable = true, unique = false)
    private String nome;




}
