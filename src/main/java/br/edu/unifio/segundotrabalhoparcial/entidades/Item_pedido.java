package br.edu.unifio.segundotrabalhoparcial.entidades;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;


@Entity
@Data
public class Item_pedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @Column(length = 10)
    private Short quantidade;
}