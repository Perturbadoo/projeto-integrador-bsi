package br.edu.unifio.segundotrabalhoparcial.entidades;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;


@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 10, nullable = false, unique = false)
    private Double valor_total;

    @Column(nullable = false)
    private Date data_pedido;


}
