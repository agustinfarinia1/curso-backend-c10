package com.farinia.desafioRedis.cache;

import com.farinia.desafioRedis.utils.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheRestaurante<T> implements CacheRestauranteInterfaz<T> {

    private final RedisTemplate<String, T> redisTemplate;
    private HashOperations<String, String, String> hashOperations;
    private final ObjectMapper mapper;

    @PostConstruct
    void setHashOperations() {
        hashOperations = this.redisTemplate.opsForHash();
    }

    @Override
    public T save(String key, T data) {
        try {
            hashOperations.put(Constantes.NAME_MAP_Restaurante, key, serializeItem(data));
            return data;
        } catch (JsonProcessingException e) {
            log.error("Error converting restaurante to string", e);
        }
        return data;
    }

    @Override
    public T recover(String key, Class<T> classValue) {
        try {
            var item = hashOperations.get(Constantes.NAME_MAP_Restaurante, key);
            if (item == null) return null;
            return deserializeItem(item, classValue);
        } catch (JsonProcessingException e) {
            log.error("Error converting restaurante to restaurante", e);
        }
        return null;
    }

    @Override
    public void delete(String id){
        hashOperations.delete(Constantes.NAME_MAP_Restaurante,id);
    }

    private String serializeItem(T item) throws JsonProcessingException {
        var serializeItem = mapper.writeValueAsString(item);
        log.info("restaurante en formato String: {}", serializeItem);
        return serializeItem;
    }

    private T deserializeItem(String jsonInput, Class<T> classValue) throws JsonProcessingException {
        return mapper.readValue(jsonInput, classValue);
    }
}
