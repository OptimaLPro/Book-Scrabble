package test;

import java.util.HashMap;
import java.util.Map;

public class LFU implements CacheReplacementPolicy {
    private Node head;
    private Node tail;
    private int size;
    private Map<String, Node> cache;

    public LFU() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        size = 0;
        cache = new HashMap<>();
    }

    @Override
    public void add(String word) {
        Node node = cache.get(word);
        if (node != null) {
            // If word exists, update its frequency and move it to the last position
            node.frequency++;
            adjustFrequency(node);
        } else {
            // If word is new, create a new node and add it to the last position
            node = new Node(word);
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
            cache.put(word, node);
        }
    }

    @Override
    public String remove() {
        if (size == 0) {
            return null;
        }
        Node current = head.next;
        Node leastFrequentNode = current;
        while (current != tail) {
            if (current.frequency < leastFrequentNode.frequency) {
                leastFrequentNode = current;
            }
            current = current.next;
        }
        leastFrequentNode.prev.next = leastFrequentNode.next;
        leastFrequentNode.next.prev = leastFrequentNode.prev;
        size--;
        cache.remove(leastFrequentNode.word);
        return leastFrequentNode.word;
    }

    private void adjustFrequency(Node node) {
        Node current = node.prev;
        while (current != head && current.frequency <= node.frequency) {
            current = current.prev;
        }
        // Move node to the position after current
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = current;
        node.next = current.next;
        current.next.prev = node;
        current.next = node;
    }

    private class Node {
        String word;
        Node prev;
        Node next;
        int frequency;

        public Node() {
            word = null;
            prev = null;
            next = null;
            frequency = 0;
        }

        public Node(String word) {
            this.word = word;
            prev = null;
            next = null;
            frequency = 1;
        }
    }
}
