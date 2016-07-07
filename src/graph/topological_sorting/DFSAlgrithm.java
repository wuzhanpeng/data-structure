package graph.topological_sorting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class DFSAlgrithm {
	
	/** unmarked nodes */
	private boolean[] marked;
	private boolean isDAG = true;
	/** adjacency list */
	private ArrayList<HashSet<Integer>> adjacencyList;
	private LinkedList<Integer> sorted;
	
	private void visit(int node, boolean[] temp_marked) {
		if (temp_marked[node]) {
			isDAG = false;
			return;
		}
		temp_marked[node] = true;
		for (int next : adjacencyList.get(node)) {
			visit(next, temp_marked);
			if (!isDAG) { break; }
		}
		temp_marked[node] = false;
		marked[node] = true;
		// add to head
		sorted.addFirst(node);
	}
	
	public boolean DFSBase(int numVertices, int[][] edges) {
		marked = new boolean[numVertices];
		adjacencyList = new ArrayList<>(numVertices);
		sorted = new LinkedList<>();
		for (int i = 0; i < numVertices; ++ i) { adjacencyList.add(new HashSet<>()); }
		for (int i = 0; i < edges.length; ++ i) {
				adjacencyList.get(edges[i][1]).add(edges[i][0]);
		}
		for (int node = 0; node < numVertices; ++ node) {
			if (!marked[node]) { visit(node, new boolean[numVertices]); }
			if (!isDAG) { return false; }
		}
		return true;
	}
	
	public List<Integer> getSortedOrder() {
		return sorted;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
