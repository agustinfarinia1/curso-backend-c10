package com.farinia.desafioRedis.service;

import com.farinia.desafioRedis.cache.CacheRestauranteInterfaz;
import com.farinia.desafioRedis.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestauranteService implements RestauranteServiceInterfaz {

    private final CacheRestauranteInterfaz<Restaurante> cache;
    private final ObjectMapper mapper;

    @PostConstruct
    private void PostConstruct() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
    }

    @Override
    public Restaurante createRestaurante(Restaurante restaurante) {
        try {

            mapperToString(restaurante);
            mapperToMap(restaurante);
            cache.save(restaurante.getId().toString(), restaurante);
        } catch (JsonProcessingException e) {
            log.error("Error converting message to string", e);
        }
        return restaurante;
    }

    @Override
    public Restaurante getRestauranteById(Long id) {
        Restaurante restaurante = cache.recover(id.toString(), Restaurante.class);
        return restaurante;
    }

    @Override
    public Restaurante updateRestauranteById(Restaurante restaurante, Long id) {
        if(getRestauranteById(id) != null){
            restaurante.setId(id);
            return cache.save(restaurante.getId().toString(), restaurante);
        }
        return null;
    }

    @Override
    public void deleteRestaurante(Long id) {
        cache.delete(id.toString());
    }

    public void mapperToString(Restaurante restaurante) throws JsonProcessingException {
        var userString = mapper.writeValueAsString(restaurante);
        log.info("Mensaje en formato String : {}", userString);
    }
    @Override
    public void mapperToMap(Restaurante restaurante) throws JsonProcessingException {
        var userString = mapper.writeValueAsString(restaurante);
        var userMap = mapper.readValue(userString, Map.class);
        log.info("Mensaje en formato de Mapa : {}", userMap);
    }

    public void mapperToClass(Restaurante restaurante) throws JsonProcessingException {
        var userString = mapper.writeValueAsString(restaurante);
        var userClass = mapper.readValue(userString, Restaurante.class);
        log.info("Mensaje en formato de Clase : {}", userClass);
    }
}
