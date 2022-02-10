package com.farinia.desafioRedis.cache;

import com.farinia.desafioRedis.model.Restaurante;

import java.util.List;

public interface CacheRestauranteInterfaz<T> {
    T save(String key, T data);
    T recover(String key, Class<T> classValue);
    void delete(String id);
}
