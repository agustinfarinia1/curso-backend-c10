package com.farinia.desafioRedis.controller;

import com.farinia.desafioRedis.model.Restaurante;
import com.farinia.desafioRedis.service.RestauranteServiceInterfaz;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class RestauranteController {
    @Autowired
    RestauranteServiceInterfaz service;

    @GetMapping("/restaurante/ej")
    public String getEjemplo() {
        return "restaurante";
    }

    @GetMapping("/restaurante/{id}")
    public Restaurante getRestauranteById(@PathVariable Long id) {
        return service.getRestauranteById(id);
    }

    @PostMapping("/restaurante")
    public Restaurante createRestaurante(@RequestBody Restaurante restaurante) {
        return service.createRestaurante(restaurante);
    }

    @PutMapping("/restaurante/{id}")
    public Restaurante updateRestaurante(@PathVariable Long id,@RequestBody Restaurante restaurante){
        return service.updateRestauranteById(restaurante,id);
    }

    @DeleteMapping("/restaurante/{id}")
    public void deleteRestaurante(@PathVariable Long id){
        service.deleteRestaurante(id);
    }

    @PostMapping("/restaurante/map")
    public void restaurantetoMap(@RequestBody Restaurante restaurante) throws JsonProcessingException {
        service.mapperToMap(restaurante);
    }
}
