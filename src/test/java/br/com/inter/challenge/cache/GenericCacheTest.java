package br.com.inter.challenge.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
class GenericCacheTest {

    private GenericCache<String, Integer> cache;

    @BeforeEach
    void setUp() {
        this.cache = new GenericCache<>();
    }

    @Test
    @DisplayName("get cache when successful")
    void getCache_WhenSuccessful() {
        cache.put("n", 1);
        Optional<Integer> n = cache.get("n");
        assertThat(n.get()).isNotNull();
        assertThat(n.get()).isEqualTo(1);
    }

    @Test
    @DisplayName("put cache when successful")
    void putCache_WhenSuccessful() {
        assertThatCode(() -> cache.put("n", 1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("clear cache when successful")
    void clearCache_WhenSuccessful() {
        cache.put("n", 1);
        cache.clear();
        Optional<Integer> n = cache.get("n");
        assertThat(n).isEmpty();
    }

    @Test
    @DisplayName("clean cache when successful")
    void cleanCache_WhenSuccessful() {
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.put("d", 4);
        cache.put("e", 5);
        cache.put("f", 6);
        cache.put("g", 7);
        cache.put("h", 8);
        cache.put("i", 9);
        cache.put("j", 10);

        cache.clean();
        Optional<Integer> n = cache.get("n");
        assertThat(n).isEmpty();
    }

    @Test
    @DisplayName("contains key cache when successful")
    void containsKeyCache_WhenSuccessful() {
        cache.put("n", 1);
        boolean isContainsKey = cache.containsKey("n");
        assertThat(isContainsKey).isTrue();
    }

    @Test
    @DisplayName("remove cache when successful")
    void removeCache_WhenSuccessful() {
        cache.put("n", 1);
        cache.remove("n");
        Optional<Integer> n = cache.get("n");
        assertThat(n).isEmpty();
    }

}