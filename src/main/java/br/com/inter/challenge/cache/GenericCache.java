package br.com.inter.challenge.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenericCache<K, V> implements IGenericCache<K, V> {

    public static final Long CACHE_MAX_SIZE = 11L;

    protected Map<K, CacheValue<V>> cacheValueMap;
    protected Long cacheMaxSize;

    public GenericCache() {
        this(CACHE_MAX_SIZE);
    }

    public GenericCache(Long cacheMaxSize) {
        this.cacheMaxSize = cacheMaxSize;
        this.clear();
    }

    @Override
    public void clean() {
        for (K key : this.getExpiredKeys()) {
            this.remove(key);
        }
    }

    protected Set<K> getExpiredKeys() {
        return this.cacheValueMap.keySet().parallelStream()
                .filter(this::isExpired)
                .collect(Collectors.toSet());
    }

    protected boolean isExpired(K key) {
        return this.cacheValueMap.size() == CACHE_MAX_SIZE.intValue();
    }

    @Override
    public void clear() {
        this.cacheValueMap = new HashMap<>();
    }

    @Override
    public boolean containsKey(K key) {
        return this.cacheValueMap.containsKey(key);
    }

    @Override
    public Optional<V> get(K key) {
        this.clean();
        return Optional.ofNullable(this.cacheValueMap.get(key)).map(CacheValue::getValue);
    }

    @Override
    public void put(K key, V value) {
        this.cacheValueMap.put(key, this.createCacheValue(value));
    }

    private CacheValue<V> createCacheValue(V value) {
        return new CacheValue<V>() {
            @Override
            public V getValue() {
                return value;
            }

            @Override
            public Long cacheMaxSize() {
                return 1L;
            }
        };
    }

    @Override
    public void remove(K key) {
        this.cacheValueMap.remove(key);
    }

    protected interface CacheValue<V> {
        V getValue();
        Long cacheMaxSize();
    }
}
