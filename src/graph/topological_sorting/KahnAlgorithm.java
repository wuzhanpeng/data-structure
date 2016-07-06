package graph.topological_sorting;

import java.util.LinkedList;
import java.util.List;

public class KahnAlgorithm {
	
	public List<Integer> Kahn(int numVertices, int[][] edges) {
		boolean[][] adjacencyMatrix = new boolean[numVertices][numVertices];
		List<Integer> sorted = new LinkedList<>();
		int restEdges = 0;
		// 0 degree vertex
		int[] inDegree = new int[numVertices];
		for (int i = 0; i < edges.length; ++ i) {
			if (!adjacencyMatrix[edges[i][1]][edges[i][0]]) {
				++ restEdges; ++ inDegree[edges[i][0]];
				adjacencyMatrix[edges[i][1]][edges[i][0]] = true;
			}
		}
		LinkedList<Integer> vertices = new LinkedList<>();
		for (int i = 0; i < inDegree.length; ++ i) {
			if (0 == inDegree[i]) { vertices.add(i); }
		}
		while (!vertices.isEmpty()) {
			LinkedList<Integer> newAdded = new LinkedList<>();
			while (!vertices.isEmpty()) {
				int vertex = vertices.poll();
				sorted.add(vertex);
				for (int pointed = 0; pointed < numVertices; ++ pointed) {
					if (adjacencyMatrix[vertex][pointed]) {
						adjacencyMatrix[vertex][pointed] = false;
						-- restEdges; -- inDegree[pointed];
						if (0 == inDegree[pointed]) {
							newAdded.add(pointed);
						}
					}
				}
			}
			vertices.addAll(newAdded);
		}
		return 0 == restEdges ? sorted : null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
