package boj.gold._3_;
// 위상정렬, 그래프

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2252_줄_세우기 {
	
	static int N, M, _W;
    static int[] _a, heights, indegree;
    static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<ArrayList<Integer>>();
        indegree = new int[N+1];	// 각 노드의 진입 차수 저장 배열
        heights = new int[N];	// 결과 저장 배열
        graph.add(null);	// 0번 노드 사용 X
        for (int i = 1; i <= N; i++) {
        	graph.add(new ArrayList<Integer>());
		}
        int fore, rear;
        for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			fore = Integer.parseInt(st.nextToken());
			rear = Integer.parseInt(st.nextToken());
			graph.get(fore).add(rear);
			indegree[rear]++;
		}
        compare();
        for (int i = 0; i < N; i++) {
			bw.write(heights[i] + " ");
		}
        bw.flush();
        bw.close();
	}	

	static void compare() {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 1; i <= N; i++) {	// 진입 차수가 0인 노드 큐에 저장
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}
		int idx = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			heights[idx++] = cur;	// 결과 배열에 현재 노드 값 저장
			for (Integer next : graph.get(cur)) {	// 현재 노드 이후에 올 수 있는 모든 다음 노드에 대하여
				indegree[next]--;	// 진입 차수 1 감소
				if (indegree[next] == 0) {	// 진입 차수가 0이라면
					queue.offer(next);	// 큐에 저장
				}
			}
		}
	}
}
