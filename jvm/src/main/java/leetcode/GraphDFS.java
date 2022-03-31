package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphDFS {
    // DFS - node pairs looking for already added path
// [[1,2],[2,3],[3,4],[1,4],[1,5]]
    Set<Integer> seen = new HashSet();
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection(int[][] edges) {
      // Keep a List of arrays, graph[source] = [neghbor, ...]
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] edge: edges) {
            seen.clear();
            // both nodes in graph, and there already is a path
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
                    dfs(graph, edge[0], edge[1])) {
                return edge;
            }
            // either node not in graph, add
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        throw new AssertionError();
    }
    /*
     Search an array of sources to their targets (edges by source) for a a path from source to target
     */
    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
        int s = source;
        int t = target;
        List<Integer>[] g = graph;
        // Haven't seen this source,
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target) return true; // previous path found
            // search graph for edge from a neighbor to the target
            for (int nei: graph[source]) {
                if (dfs(graph, nei, target)) return true;
            }
        }
        return false;
    }

}

