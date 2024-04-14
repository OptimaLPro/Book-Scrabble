package test;


public class LRU implements CacheReplacementPolicy {
    private Node head;
    private Node tail;
    private int size;
    
    public LRU() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    
    @Override
public void add(String word) {
    Node existingNode = findNode(word);
    if (existingNode != null) {
        // If word exists, remove it from its current position
        existingNode.prev.next = existingNode.next;
        existingNode.next.prev = existingNode.prev;
        size--;
    }

    Node node = new Node(word);
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
    size++;
}

// Helper method to find a node with the given word
private Node findNode(String word) {
    Node current = head.next;
    while (current != tail) {
        if (current.word != null && current.word.equals(word)) {
            return current;
        }
        current = current.next;
    }
    return null;
}
    
    @Override
    public String remove() {
        if (size == 0) {
            return null;
        }
        Node node = tail.prev;
        node.prev.next = tail;
        tail.prev = node.prev;
        size--;
        return node.word;
    }
    
    private class Node {
        String word;
        Node prev;
        Node next;
        
        public Node() {
            word = null;
            prev = null;
            next = null;
        }
        
        public Node(String word) {
            this.word = word;
            prev = null;
            next = null;
        }
    }

}
