package br.com.inter.challenge.config;

import br.com.inter.challenge.cache.GenericCache;
import br.com.inter.challenge.cache.IGenericCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public <K, V> IGenericCache<K, V> getCache(@Value("${app.cache-max-size}") Long cacheTimeout) {
        return new GenericCache<K, V>(cacheTimeout);
    }
}
