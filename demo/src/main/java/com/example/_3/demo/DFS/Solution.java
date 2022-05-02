package com.example._3.demo.DFS;

import java.util.*;

public class Solution {

	static class Node {
		public int val;
		public List<Node> neighbors;

		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}

	private static final HashMap<Node, Node> remap = new HashMap<>();
	private static final Queue<Node> queue = new LinkedList<>();

	public Node cloneGraph(Node node) {

		if (node == null) {
			return null;
		}

		Node result = new Node(node.val);
		queue.add(node);
		remap.put(node, result);

		Node cur;
		Node copyNode;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			copyNode = remap.get(cur);
			copyAndAddQueue(cur, copyNode);
		}
		return result;
	}

	private void copyAndAddQueue(Node cur, Node copyNode) {

		for (Node node : cur.neighbors) {
			Node tmp = remap.get(node);
			if (tmp == null) {
				tmp = new Node(node.val);
			}
			copyNode.neighbors.add(tmp);
			if (remap.get(node) == null) {
				queue.add(node);
				remap.put(node, tmp);
			}
		}
	}
}
