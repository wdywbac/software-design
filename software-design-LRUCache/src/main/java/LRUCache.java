import java.util.HashMap;

public class LRUCache<K, V> {
    private final HashMap<K, Node<K, V>> map;
    private final LinkedList<K, V> list;
    private final int capacity;

    public LRUCache(int capacity) {
        assert (capacity > 0): "Capacity cannot be zero or negative";
        map = new HashMap<>();
        list = new LinkedList<>();
        this.capacity = capacity;
    }

    public void add(K key, V value) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            list.remove(node);
            list.addFirst(node);
            node.updateValue(value);
            return;
        }

        Node<K, V> node = new Node<>(key, value);
        if (map.size() == capacity) {
            Node<K, V> tail = list.getTail();
            list.removeLast();
            map.remove(tail.key);
        }
        list.addFirst(node);
        map.put(key, node);
        assert (map.size() <= capacity): "Map size is bigger than allowed capacity";
    }

    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }

        Node<K, V> node = map.get(key);
        list.remove(node);
        list.addFirst(node);
        return node.value;
    }

    public int getSize() {
        assert (map.size() <= capacity): "Map size is bigger than allowed capacity";
        return map.size();
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }
}
