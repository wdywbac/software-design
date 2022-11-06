public class Node<K, V> {
    public Node<K, V> prev;
    public Node<K, V> next;

    public final K key;
    public V value;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void updateValue(V newValue) {
        value = newValue;
    }
}
