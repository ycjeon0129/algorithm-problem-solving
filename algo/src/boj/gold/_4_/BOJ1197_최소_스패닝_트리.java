package boj.gold._4_;
// MST, Prim

import java.io.*;
import java.util.*;

public class BOJ1197_최소_스패닝_트리 {

    static int V, E, weight, idx;
    static boolean[] checked;
    static ArrayList<ArrayList<int[]>> graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        idx = 1;
        checked = new boolean[V+1];
        graph = new ArrayList<ArrayList<int[]>>();
        graph.add(null);    // 0번째 노드는 사용하지 않음
        int from, to, w;
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<int[]>());
        }
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph.get(from).add(new int[] {to, w}); // 양방향 그래프의 간선 추가
            graph.get(to).add(new int[] {from, w});
        }
        prim();
        System.out.println(weight);
    }

    static void prim() {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {    // {from, to, w}
                return o1[2] - o2[2];
            }
        });
        int num = V - 1;
        checked[1] = true;
        for (int[] cur : graph.get(1)) {    // 1번 노드에 연결된 모든 간선을 큐에 저장
            pq.offer(new int[] {1, cur[0], cur[1]});
        }
        while (num > 0) {   // 연결된 간선의 개수가 V-1개가 될 때까지 반복
            int cur[] = pq.poll();
            while (checked[cur[1]]) cur = pq.poll();    // 선택한 최소 경로 간선의 도착점이 이미 방문한 노드라면 다음 최소 경로 간선 선택
            int to = cur[1], w = cur[2];
            if (!checked[to]) { // 도착점이 방문하지 않은 노드라면
                checked[to] = true; // 최소 경로 연결
                weight += w;    // 선택된 간선의 가중치 합 연산
                num--;
                for (int[] next : graph.get(to)) {  // 도착점에서 갈 수 있는 모든 간선에 대
                    int nextTo = next[0], nextW = next[1];
                    if (!checked[nextTo]) pq.offer(new int[] {to, nextTo, nextW});  // 방문하지 않은 노드와 연결된 간선 큐에 저장
                }
            }
        }
    }
}
