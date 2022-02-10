package com.farinia.desafioRedis.model;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurante implements Serializable {
    private Long id;
    private String nombre;
    private String hora_inicio;
    private String hora_fin;
    private String fecha_creacion;
    private Ciudad ciudad;
}