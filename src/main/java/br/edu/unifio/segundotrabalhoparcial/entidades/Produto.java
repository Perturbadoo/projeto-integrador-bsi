package br.edu.unifio.segundotrabalhoparcial.entidades;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;


@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 50 ,nullable = true, unique = false)
    private String descricao;

    @Column(length = 10 ,nullable = true, unique = false)
    private Double valor_unidade;

    @Column(length = 10 ,nullable = true, unique = false)
    private Integer quantidade_atual;




}