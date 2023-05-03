package boj.gold._2;
// 위상정렬, PQ

import java.io.*;
import java.util.*;

public class BOJ1766_문제집 {

    static int N, M;
    static int[] in, result;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        in = new int[N+1];
        result = new int[N];
        graph = new ArrayList<ArrayList<Integer>>();
        graph.add(null);
        for (int i = 1; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());
            graph.get(pre).add(post);
            in[post]++;
        }
        solve();
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(String.format("%d ", i));
        }
        System.out.println(sb);
    }

    static void solve() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() { // 오름차순 우선순위 큐
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 1; i <= N; i++) {  // 초기 설정: 진입차수가 0인 노드를 pq에 저장
            if (in[i] == 0)
                pq.offer(i);
        }
        int idx = 0;
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            result[idx++] = cur;    // 결과 배열 저장
            for (int next : graph.get(cur)) {   // 현재 노드 이후에 선택 해야하는 모든 노드에 대해
                in[next]--; // 다음 노드의 진입차수 1 감소
                if (in[next] == 0)  // 다음 노드의 진입차수가 0이라면
                    pq.offer(next); // pq에 저장
            }
        }
    }
}
