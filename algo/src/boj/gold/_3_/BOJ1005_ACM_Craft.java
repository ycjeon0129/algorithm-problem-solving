package boj.gold._3_;
// 위상정렬, DP, 그래프

import java.io.*;
import java.util.*;

public class BOJ1005_ACM_Craft {

    static int N, K, W;
    static int[] buildTime, timestamp, indegree;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            buildTime = new int[N + 1];     // 각 건물의 건설 시간
            timestamp = new int[N + 1];     // 각 건물이 건설된 후 시간
            indegree = new int[N + 1];      // 각 건물을 짓기 위한 선행 건물의 수
            graph = new ArrayList<ArrayList<Integer>>();
            graph.add(new ArrayList<Integer>());    // 사용하지 않을 0번 노드
            st = new StringTokenizer(br.readLine());
            for (int n = 1; n <= N; n++) {
                graph.add(new ArrayList<Integer>());
                buildTime[n] = timestamp[n] = Integer.parseInt(st.nextToken());
            }
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int before = Integer.parseInt(st.nextToken());
                int after = Integer.parseInt(st.nextToken());
                graph.get(before).add(after);   // 선후관계 입력
                indegree[after]++;  // 진입 차수 증가
            }
            W = Integer.parseInt(br.readLine());
            build();
            bw.write(timestamp[W]+"\n");
        }
        bw.flush();
        bw.close();
    }

    static void build() {
        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int n = 1; n <= N; n++) {
            if (indegree[n] == 0) queue.offer(n);
        }
        while (!queue.isEmpty()) {  // 순환 관계 존재할 경우 위상 정렬 불가 -> Queue가 빈 상태에서 진입 차수가 0이 아닌 노드 존재
            int cur = queue.poll();
            for (int next : graph.get(cur)) {
                // 건물의 총 소요시간 = 이전까지의 소요시간 + 현재 건물 소요시간
                // 선행 관계가 모두 완료되어야 현 건물 건설 가능 ==> Max 비교
                timestamp[next] = Math.max(timestamp[next], timestamp[cur] + buildTime[next]);
                indegree[next]--;   // cur 완료 -> next의 선행 노드 수 1 감소
                if (indegree[next] == 0) queue.offer(next); // 선행 노드가 더 이상 존재하지 않다면 건설 대기 열에 추가
            }
        }
    }
}
