package com.mss.product.repository.redis;

public interface RedisRepository {
    void put(String key, Object value);
    void put(String key, Object value, long ttl);
    <T> T get(String key, Class<T> clazz);
}
