package com.ssafy.day18_;
// gold 4. 최단경로
// 다익스트라, 인접 리스트
// 다시 풀기

import java.io.*;
import java.util.*;

public class Main_1753_전윤철_ {

	static int V, E, K;
	static final int INF = Integer.MAX_VALUE;
	static int[] distance;
	static boolean[] visited;
	static Graph edges;
	static PriorityQueue<Integer[]> pq;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static class Graph extends ArrayList<ArrayList<Integer[]>> {
		public Graph(int size) {
			for (int i = 0; i < size; i++) {
				add(new ArrayList<Integer[]>());
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		distance = new int[V + 1];
		Arrays.fill(distance, INF);
		edges = new Graph(V + 1);

		int u, v, w;
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			edges.get(u).add(new Integer[] { u, v, w });
		}

		Dijkstra();
		bw.flush();
		bw.close();
	}

	static void Dijkstra() throws IOException {

		// 간선의 가중치 오름차순으로 우선순위 큐 설정
		pq = new PriorityQueue<Integer[]>(new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				return o1[2] - o2[2];
			}
		});
		// 시작 정점의 경우 거리 0 설정
		distance[K] = 0;
		pq.offer(new Integer[] {K, 0, 0});
		while (!pq.isEmpty()) {
			Integer[] next = pq.poll();
			for (int i = 0; i < edges.get(next[0]).size(); i++) {
				// 현재 정점에서 갈 수 있는 정점 탐색
				Integer[] edge = edges.get(next[0]).get(i);
				// 가중치 최솟값 갱신
				if (distance[edge[1]] > distance[next[0]] + edge[2]) {
					distance[edge[1]] = distance[next[0]] + edge[2];
					// 도착 정점의 최솟값을 갱신했다면 도착 정점에서 출발하는 간선을 우선순위 큐에 삽입
					pq.offer(new Integer[] {edge[1], 0, distance[edge[1]]});
				}
			}
		}
		print();
	}

	static void print() throws IOException {
		for (int i = 1; i <= V; i++) {
			if (distance[i] == INF) {
				bw.write("INF\n");
			} else {
				bw.write(distance[i] + "\n");
			}
		}

	}

}
