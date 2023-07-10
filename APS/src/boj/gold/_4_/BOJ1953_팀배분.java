package boj.gold._4_;
// 이분 그래프, DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1953_팀배분 {

    static int N, reds, blues;
    static Graph graph;
    static boolean[] selected, red, blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        graph = new Graph(N);
        selected = new boolean[N + 1];
        red = new boolean[N + 1];
        blue = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {  // 입력
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= num; j++) {
                int temp = Integer.parseInt(st.nextToken());
                graph.add(i, temp);
            }
        }

        // 선택되지 않은 노드에 대해 홍팀 배치
        for (int i = 1; i <= N; i++) {
            if (!selected[i]) {
                selected[i] = true;
                red[i] = true;
                reds++;
                dfs(i, true);
            }
        }

        sb.append(reds + "\n"); // 출력
        for (int i = 1; i <= N; i++) {
            if (red[i]) {
                sb.append(i + " ");
            }
        }
        sb.append("\n");
        sb.append(blues + "\n");
        for (int i = 1; i <= N; i++) {
            if (blue[i]) {
                sb.append(i + " ");
            }
        }
        System.out.println(sb);
    }

    static void dfs(int node, boolean state) {  // 현재 노드의 이름과 상태를 통한 dfs

        for (int nextNode : graph.get(node)) {
            if (!selected[nextNode]) {
                selected[nextNode] = true;
                if (state) {
                    blue[nextNode] = true;
                    blues++;
                } else {
                    red[nextNode] = true;
                    reds++;
                }
                dfs(nextNode, !state);
            }
        }
    }

    static class Graph extends ArrayList<ArrayList<Integer>> {
        Graph(int num) {
            for (int i = 0; i <= num; i++) {
                add(new ArrayList<Integer>());
            }
        }

        void add(int from, int to) {
            get(from).add(to);
        }
    }

}
