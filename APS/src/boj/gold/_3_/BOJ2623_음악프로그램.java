package boj.gold._3_;
// 위상정렬, 그래프

import java.io.*;
import java.util.*;

public class BOJ2623_음악프로그램 {
	
	static int N, M, idx;
	static int[] indegree, resultSet;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N+1];	// 노드의 진입 차수 저장 배열
		resultSet = new int[N+1];	// 정렬된 노드의 순서 저장 배열
		
		graph = new ArrayList<ArrayList<Integer>>();	// 노드의 간선 정보를 저장할 2차원 리스트
		graph.add(null);	// 0번 노드는 사용하지 않음
		
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<Integer>());	// 각 노드에서 연결된 노드를 저장할 리스트
		}
		
		int num, from, to;
		for (int i = 0; i < M; i++) {	// 각 보조PD가 담당하는 가수에 대해 가수 순서를 각 가수 노드의 입력 차수, 순서 간선 리스트로 변환하여 입력받음
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			from = Integer.parseInt(st.nextToken());	// 첫 가수 입력
			for (int j = 1; j < num; j++) {
				to = Integer.parseInt(st.nextToken());
				indegree[to]++;	// 어떤 가수 뒤에 오는 모든 가수의 진입 차수 1 증가
				graph.get(from).add(to);	// 이전 가수의 리스트에 다음 가수 추가
				from = to;	// 다음 반복문을 대비히여 다음 가수를 이전 가수로 변경
			}
		}
		sort();	// 순서 정렬
	}

	static void sort() throws IOException {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		idx = 0;	// 정렬된 가수의 수
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {	// 이전 순서가 없는 모든 가수를 큐에 저장
				queue.offer(i);
			}
		}
		while (!queue.isEmpty()) {
			int cur = queue.poll();	// 현재 가수를 결과 순서에 저장
			resultSet[++idx] = cur;
			for (Integer next : graph.get(cur)) {	// 현재 가수 다음으로 오는 모든 가수에 대해 
				indegree[next]--;	// 진입 차수 1 감소
				if (indegree[next] == 0) {	// 다음 가수의 진입 차수가 0이라면
					queue.offer(next);	// 큐에 저장
				}
			}
		}
		print();	// 큐가 종료된 이후 결과 출력
	}

	static void print() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		if (idx != N) {	// 모든 가수가 정렬되기 전 큐가 종료되었다면
			bw.write("0");
		} else {	// 모든 가수가 정렬되었다면
			for (int i = 1; i <= N; i++) {
				bw.write(resultSet[i]+"\n");
			}
		}
		bw.flush();
		bw.close();
	}
}