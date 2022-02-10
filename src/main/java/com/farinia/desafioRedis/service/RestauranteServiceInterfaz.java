package com.farinia.desafioRedis.service;

import com.farinia.desafioRedis.model.Restaurante;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RestauranteServiceInterfaz {
    Restaurante createRestaurante(Restaurante restaurante);
    Restaurante getRestauranteById(Long id);
    Restaurante updateRestauranteById(Restaurante restaurante, Long id);
    void deleteRestaurante(Long id);
    void mapperToMap(Restaurante restaurante) throws JsonProcessingException;
}
