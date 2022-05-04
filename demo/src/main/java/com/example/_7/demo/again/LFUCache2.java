package com.example._7.demo.again;

import java.util.HashMap;

public class LFUCache2 {

    private final int size;
    private final HashMap<Integer, Value> map;
    private final InnerLinkedList list;

    public LFUCache2(int size) {
        this.size = size;
        this.map = new HashMap<>(
                1 << (Integer.bitCount(size) + 1),
                0.85F);
        this.list = new InnerLinkedList(size);
    }

    public Integer get(int key) {

        if (size == 0) {
            return -1;
        }

        if (map.containsKey(key)) {
            Value value = map.get(key);
            Key tKey = value.key;
            tKey.useCount++;
            list.mov(tKey);
            return value.value;
        }
        return -1;
    }

    public void put(int key, int element) {

        if (size == 0) {
            return;
        }

        if (map.containsKey(key)) {
            Value value = map.get(key);
            Key tKey = value.key;
            tKey.useCount++;
            list.mov(tKey);
            value.value = element;
        } else if (size == map.size()) {
            map.remove(list.remove());
            Key newKey = new Key(key);
            list.add(newKey);
            map.put(key, new Value(newKey, element));
        } else {
            Key newKey = new Key(key);
            list.add(newKey);
            map.put(key, new Value(newKey, element));
        }
    }


    private static class InnerLinkedList {

        Node[] nodes = new Node[100];
        final int size;
        Node removeCursor = new Node(new Key(null));

        public InnerLinkedList(int size) {
            this.size = size;
            {
                Key hotKeyHead = new Key(null);
                hotKeyHead.useCount = Integer.MAX_VALUE;
                nodes[0] = new Node(hotKeyHead);
                Key hotKeyEnd = new Key(null);
                hotKeyEnd.useCount = Integer.MIN_VALUE;
                Node after = new Node(hotKeyEnd);
                nodes[0].after = after;

                nodes[1] = new Node(new Key(null));
                nodes[1].after = removeCursor;
                removeCursor.before = nodes[1];

                for (int i = 2; i < 100; i++) {
                    nodes[i] = new Node(new Key(null));
                    after = new Node(new Key(null));
                    nodes[i].after = after;
                    after.before = nodes[i];
                }
            }
        }


        private Integer remove() {
            // TODO: 2022/5/4 当 node[1] 是 null 时，无法移除。
            Node todo = removeCursor.before;
            todo.before.after = removeCursor;
            removeCursor.before = todo.before;

            todo.before = null;
            todo.after = null;

            return todo.key.key;
        }

        private void add(Key key) {
            Node newNode = new Node(key);
            newNode.after = nodes[1].after;
            newNode.after.before = newNode;
            nodes[1].after = newNode;
            newNode.before = nodes[1];
        }

        private void mov(Key key) {

            Node current = key.current;
            current.after.before = current.before;
            current.before.after = current.after;

            if (key.useCount >= 100) {
                Node tmp = nodes[0];
                while (tmp != null) {
                    if (tmp.key.useCount < key.useCount) {
                        tmp.before.after = current;
                        current.before = tmp.before;
                        current.after = tmp;
                        tmp.before = current;
                        break;
                    }
                    tmp = tmp.after;
                }
            } else {
                Node tmp = nodes[key.useCount];
                current.before = tmp;
                current.after = tmp.after;
                tmp.after = current;
                current.after.before = current;
            }

        }

    }

    private static class Node {
        Node before;
        Node after;
        final Key key;

        public Node(Key key) {
            this.key = key;
            this.key.current = this;
        }
    }

    private static class Value {
        final Key key;
        Integer value;

        public Value(Key key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Value{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private static class Key {

        Node current;
        final Integer key;
        int useCount = 1;

        public Key(Integer key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "key=" + key +
                    ", useCount=" + useCount +
                    '}';
        }
    }
}
