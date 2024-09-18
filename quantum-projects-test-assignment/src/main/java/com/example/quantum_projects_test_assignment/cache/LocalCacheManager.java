package com.example.quantum_projects_test_assignment.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LocalCacheManager {
    private static LocalCacheManager instance;
    private static Object monitor = new Object();
    private Map<String, Object> cache = Collections.synchronizedMap(new HashMap<>());

    private LocalCacheManager() {
    }

    public void put(String cacheKey, Object value) {
        cache.put(cacheKey, value);
    }

    public Object get(String cacheKey) {
        return cache.get(cacheKey);
    }

    public void clear(String cacheKey) {
        cache.put(cacheKey, null);
    }

    public void clear() {
        cache.clear();
    }

    public static synchronized LocalCacheManager getInstance() {
        if (instance == null) {
            synchronized (monitor) {
                if (instance == null) {
                    instance = new LocalCacheManager();
                }
            }
        }
        return instance;
    }
}
