package boj.gold._4_;

// 이분 그래프, BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1707_이분_그래프 {

    static int V, E;
    static Graph graph;
    static int[] colors;    // 1(on), -1(off), 0(unchecked)
    static boolean bipartiteGraph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());    // 테스트 케이스 수 T

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            graph = new Graph(V);
            colors = new int[V + 1];
            bipartiteGraph = true;
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph.add(from, to);
            }
            isBipartiteGraph();     // 이분 그래프 검사 메서드

            if (bipartiteGraph) {   // 결과에 따른 출력
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.println(sb);
    }

    // 각 정점에 대해 탐색하며 미방문 정점의 경우 bfs 실행
    static void isBipartiteGraph() {
        for (int i = 1; i < V; i++) {
            if (!bipartiteGraph) {
                return;
            }

            if (colors[i] == 0) {
                bfs(i);
            }
        }

    }

    static void bfs(int startNode) {
        colors[startNode] = 1;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(startNode);
        while (!queue.isEmpty() && bipartiteGraph) {
            int curNode = queue.poll();
            for (int nextNode : graph.get(curNode)) {
                if (colors[curNode] == colors[nextNode]) {  // 현 정점과 다음 정점의 값이 같다면 false
                    bipartiteGraph = false;
                    return;
                }
                if (colors[nextNode] == 0) {    // 다음 정점이 미방문 정점이라면 값 갱신 후 큐에 추가
                    colors[nextNode] = - 1 * colors[curNode];
                    queue.offer(nextNode);
                }
            }
        }

    }

    static class Graph extends ArrayList<ArrayList<Integer>> {
        // 1 ~ v번까지의 연결리스트 초기화
        Graph(int v) {
            for (int i = 0; i <= v; i++) {
                add(new ArrayList<Integer>());
            }
        }

        // 새로운 Edge를 추가하는 메서드
        void add(int from, int to) {
            get(from).add(to);
            get(to).add(from);
        }
    }

}
