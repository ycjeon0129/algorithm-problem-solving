package boj.gold._3_;
// 위상정렬, DP. 그래프

import java.io.*;
import java.util.*;

public class BOJ1516_게임_개발 {

    static int N;
    static int[] task, in, result;  // 초기 시간, 진입 차수, 출력값
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        task = new int[N + 1];
        in = new int[N + 1];
        result = new int[N + 1];
        graph = new ArrayList<ArrayList<Integer>>();
        graph.add(null);
        for (int i = 1; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            task[i] = Integer.parseInt(st.nextToken());
            int pre = Integer.parseInt(st.nextToken());
            while (pre != -1) {
                in[i]++;
                graph.get(pre).add(i);
                pre = Integer.parseInt(st.nextToken());
            }
        }
        build();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i] + "\n");
        }
        System.out.println(sb);
    }

    static void build() {
        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {   // 진입 차수가 0이라면
                queue.offer(i); // Queue에 저장
                result[i] = task[i];    // 최종 작업 시간 = 각 작업 고유 시간
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph.get(cur)) {   // 선후 관계가 있는 노드에 대해
                in[next]--; // 진입 차수 감소
                result[next] = Math.max(result[next], result[cur] + task[next]);    // 각 작업 시간 = 선수 작업 시간 + 작업 고유 시간
                if (in[next] == 0) {    // 진입 차수가 0이라면
                    queue.offer(next);  // Queue에 저장
                }
            }
        }
    }
}
