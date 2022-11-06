import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LRUCacheTests {
    @Test
    @DisplayName("Create Map With Capacity = 0")
    public void mapWithZeroCapacity() {
        Error thrown = Assertions.assertThrows(AssertionError.class, () -> {
            LRUCache<Integer, String> map = new LRUCache<>(0);
        }, "Assertion Error was expected");
        assertEquals("Capacity cannot be zero or negative", thrown.getMessage());
    }

    @Test
    @DisplayName("Create Map With One Element")
    public void mapWithOneElement() {
        int capacity = 10;
        LRUCache<Integer, String> map = new LRUCache<>(capacity);
        map.add(1, "first");
        assertEquals(1, map.getSize(), "Map's size should be 1");
        assertTrue(map.containsKey(1), "Map should contain key 1");
        String value = map.get(1);
        assertEquals("first", value, String.format("Expected \"first\", got %s", value));
    }

    @Test
    @DisplayName("More Elements Than Capacity")
    public void mapWith20Element() {
        int capacity = 10;
        LRUCache<Integer, String> map = new LRUCache<>(capacity);
        for (int i = 0; i < capacity * 2; i++) {
            String val = Integer.toString(i);
            map.add(i, val);
            assertTrue(map.containsKey(i), String.format("Map should contain key %d", i));
            assertEquals(val, map.get(i), String.format("Expected %s, got %s", val, map.get(i)));
        }
        assertEquals(capacity, map.getSize(), String.format("Map's size should be %d", capacity));
        for (int i = 0; i < capacity; i++) {
            assertNull(map.get(i));
        }
    }

    @Test
    @DisplayName("Change Value for Key")
    public void changingValues() {
        int capacity = 10;
        LRUCache<Integer, String> map = new LRUCache<>(capacity);
        for (int i = 0; i < capacity; i++) {
            String val = Integer.toString(i);
            map.add(i, val);
            assertTrue(map.containsKey(i), String.format("Map should contain key %d", i));
            assertEquals(val, map.get(i), String.format("Expected %s, got %s", val, map.get(i)));
        }
        assertEquals(capacity, map.getSize(), String.format("Map's size should be %d", capacity));
        map.add(0, "1111");
        map.add(10, "10");
        assertTrue(map.containsKey(0), String.format("Map should contain key %d", 0));
        assertFalse(map.containsKey(1), String.format("Map shouldn't contain key %d", 1));
        assertTrue(map.containsKey(10), String.format("Map should contain key %d", 10));
    }
}
