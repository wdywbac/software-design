public class LinkedList<K, V> {
    private Node<K, V> head;
    private Node<K, V> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(K key, V value) {
        Node<K, V> node = new Node<>(key, value);

        if (size == 0) {
            initWithElement(node);
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
            tail.next = null;
        }
        size++;
    }

    public void addFirst(Node<K, V> node) {
        assert (node != null): "Node shouldn't be null";
        if (size == 0) {
            initWithElement(node);
        } else {
            assert (head != null && tail != null): "Head or/and tail cannot be null because list is not empty";
            node.next = head;
            head.prev = node;
            if (size == 1) {
                tail = head;
            }
            head = node;
        }
        size++;
    }

    private void initWithElement(Node<K, V> node) {
        assert (size == 0): "List is not empty";
        head = node;
        tail = node;
    }

    public V remove(Node<K, V> node) {
        if (head == node) {
            return removeFirst();
        }
        if (tail == node) {
            return removeLast();
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return node.value;
    }

    public V removeLast() {
        assert (size > 0): "list shouldn't be empty";
        if (size == 1) {
            return removeFirst();
        }
        V value = tail.value;
        tail.prev.next = null;
        tail = tail.prev;
        size--;
        assert size != 1 || head == tail;
        return value;
    }

    public V removeFirst() {
        assert (size > 0): "list shouldn't be empty";
        V value = head.value;
        head = head.next;
        size--;
        assert size != 1 || head == tail;
        return value;
    }

    public int getSize() {
        return size;
    }

    public Node<K, V> getHead() {
        return head;
    }

    public Node<K, V> getTail() {
        return tail;
    }
}
