package com.example._7.demo.ready;

import java.util.HashMap;

public class LFUCache {

    private final int size;
    private final HashMap<Integer, Value> map;
    private final InnerLinkedList list;

    public LFUCache(int size) {
        this.size = size;
        this.map = new HashMap<>(1 << (Integer.bitCount(size) + 1), 0.85F);
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
            list.mov1LStep(tKey);
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
            list.mov1LStep(tKey);
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

        final int size;
        Node first = new Node(new Key(null));
        Node last = new Node(new Key(null));

        public InnerLinkedList(int size) {
            this.size = size;
            this.first.after = this.last;
            this.last.before = this.first;
            this.first.key.useCount = Integer.MAX_VALUE;
            this.last.key.useCount = Integer.MIN_VALUE;
        }

        private Integer remove() {
            Node todo = this.last.before;
            assert todo != null && todo.before != null;

            todo.before.after = this.last;
            this.last.before = todo.before;

            todo.after = null;
            todo.before = null;

            return todo.key.key;
        }

        private void add(Key key) {
            new Node(key);
            mov1LStep(key);
        }

        //时间复杂度 O(n/2)
        private void mov1LStep(Key key) {
            Node tail = key.current;
            assert tail != null;
            Node tmp = this.last;
            Node _tmp = this.first;

            if (this.first.after.key == key) {
                return;
            }

            while (tmp.key.useCount <= _tmp.key.useCount) {
                _tmp = _tmp.after;
                tmp = tmp.before;
                Key tKey = tmp.key;
                Key _tKey = _tmp.key;
                if (key.useCount >= _tKey.useCount) {

                    if (key == _tKey) {
                        return;
                    }

                    if (tail.after != null && tail.before != null) {
                        tail.before.after = tail.after;
                        tail.after.before = tail.before;
                    }
                    tail.before = _tmp.before;
                    tail.after = _tmp;
                    _tmp.before.after = tail;
                    _tmp.before = tail;
                    break;
                }
                if (key.useCount < tKey.useCount) {
                    if (tail.after != null && tail.before != null) {
                        tail.before.after = tail.after;
                        tail.after.before = tail.before;
                    }
                    tail.after = tmp.after;
                    tail.before = tmp;
                    tmp.after.before = tail;
                    tmp.after = tail;
                    break;
                }
            }
        }
    }

    private static class Node {
        Node before;
        Node after;
        final Key key;

        public Node(Key key) {
            this.key = key;
            key.current = this;
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
            return "Value{" + "key=" + key + ", value=" + value + '}';
        }
    }

    private static class Key {

        Node current;
        Integer key;
        int useCount = 1;

        public Key(Integer key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "Key{" + "key=" + key + ", useCount=" + useCount + '}';
        }
    }
}
