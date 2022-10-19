package br.edu.unifio.segundotrabalhoparcial.entidades;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;


@Entity
@Data
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 50 ,nullable = true, unique = false)
    private String nome;

    @Column(length = 70 ,nullable = true, unique = false)
    private String endereco;

    @Column(length = 25 ,nullable = true, unique = false)
    private String telefone;







}
