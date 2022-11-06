import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTests {
    @Test
    @DisplayName("Create List With One Element")
    public void listWithOneElement() {
        LinkedList<Integer, String> list = new LinkedList<>();
        list.add(1, "first");
        assertEquals(1, list.getSize(), "List's size should be 1");
        assertEquals("first", list.getHead().value, "List's head should be \"first\"");
        assertEquals("first", list.getTail().value, "List's tail should be \"first\"");
    }

    @Test
    @DisplayName("Remove From One-Element List")
    public void removeOneElement() {
        LinkedList<Integer, String> list = new LinkedList<>();
        list.add(1, "first");
        assertEquals(1, list.getSize(), "List's size should be 1");
        list.removeLast();
        assertEquals(0, list.getSize(), "List should be empty");
    }

    @Test
    @DisplayName("Create List With 10 Elements")
    public void listWithTenElements() {
        LinkedList<Integer, String> list = new LinkedList<>();
        String[] letters = {"I", "Love", "To", "Eat", "Pomelos", "And", "Oranges", "And", "Other", "Fruits"};
        for (int i = 0; i < 10; i++) {
            list.add(i, letters[i]);
        }
        assertEquals(10, list.getSize(), "List's size should be 10");
        assertEquals(letters[0], list.getHead().value, String.format("List's head should be %s", letters[0]));
        assertEquals(letters[9], list.getTail().value, String.format("List's tail should be %s", letters[9]));
    }

    @Test
    @DisplayName("Remove From 10-Elements List")
    public void removeTenElements() {
        LinkedList<Integer, String> list = new LinkedList<>();
        String[] letters = {"I", "Love", "To", "Eat", "Pomelos", "And", "Oranges", "And", "Other", "Fruits"};
        for (int i = 0; i < 10; i++) {
            list.add(i, letters[i]);
        }
        int size = 10;
        for (int i = 0; i < 9; i++) {
            String removed = list.removeFirst();
            size--;
            assertEquals(letters[i], removed, String.format("Expected %s, got %s", letters[i], removed));
            assertEquals(size, list.getSize(), String.format("List's size should be %d", size));
            assertEquals(letters[i + 1], list.getHead().value, String.format("List's head should be %s", letters[i + 1]));
            assertEquals(letters[9], list.getTail().value, String.format("List's tail should be %s", letters[size - 1]));
        }
        assertEquals(1, list.getSize(), "List's size should be 1");
        String removed = list.removeLast();
        assertEquals(0, list.getSize(), "List should be empty");
        assertEquals(letters[9], removed, String.format("Expected %s, got %s", letters[9], removed));
    }
}
