package com.farinia.desafioRedis.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ciudad implements Serializable {
    private Long id;
    private String nombre;
    private String pais;
}