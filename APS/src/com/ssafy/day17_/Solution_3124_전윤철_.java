package com.ssafy.day17_;
// D4. 최소 스패닝 트리
// MST, kruskal, prim
// 크루스칼로 다시 풀어보기

import java.io.*;
import java.util.*;

public class Solution_3124_전윤철_ {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int V, E;
	static long result;
	static int[] minEdge;
	static List<Integer[]>[] MST;
	static boolean[] visited;
	static PriorityQueue<int[]> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			bw.write("#" + t + " ");
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			MST = new ArrayList[V+1];
			for (int i = 1; i <= V; i++) {
				MST[i] = new ArrayList<Integer[]>();
			}
			minEdge = new int[V+1];
			for (int i = 1; i <= V; i++) {
				minEdge[i] = Integer.MAX_VALUE;
			}
			visited = new boolean[V+1];
			pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}
			});

			int from, to, weight;
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				weight = Integer.parseInt(st.nextToken());
				MST[from].add(new Integer[] {to, weight});
				MST[to].add(new Integer[] {from, weight});
			}
			link();
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
	}

	static void link() {
		result = 0l;
		int nodeCount = 0;
		pq.offer(new int[] { 1, 0 });
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (visited[cur[0]]) {
				continue;
			}
			result += (long) (cur[1]);
			visited[cur[0]] = true;
			if (++nodeCount == V) {
				break;
			}
			for (int i = 0; i < MST[cur[0]].size(); i++) {
				Integer[] edge = MST[cur[0]].get(i);
				if (!visited[edge[0]] && minEdge[edge[0]] > edge[1] ) {
					minEdge[edge[0]] = edge[1];
					pq.offer(new int[] {edge[0], edge[1]});
				}
			}
		}
	}

}
