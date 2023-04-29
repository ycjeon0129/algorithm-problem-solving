package com.ssafy.day27_;
// D4. 키 순서
// 플로이드-워샬?

import java.io.*;
import java.util.*;

public class Solution_5643_전윤철 {
	
	static int N, M, EA;
	static List<Integer>[] taller, shorter;
	static boolean[] known;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        	
        int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			//
			EA = 0;
			int a, b;
			taller = new ArrayList[N];
			shorter = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				taller[i] = new ArrayList<Integer>();
				shorter[i] = new ArrayList<Integer>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken()) - 1;
				b = Integer.parseInt(st.nextToken()) - 1;
				taller[a].add(b);
				shorter[b].add(a);
			}
			diff();
        	
        	sb.append(String.format("#%d %d\n", t, EA));
		}
        System.out.println(sb);
	}

	static void diff() {
		Queue<Integer> queue;
		known = new boolean[N];
		L1: for (int i = 0; i < N; i++) {
			queue = new ArrayDeque<>();
			queue.offer(i);
			known[i] = true;
			while (!queue.isEmpty()) {	// 큰 노드 확인
				int cur = queue.poll();
				for (int num : taller[cur]) {
					if (!known[num]) {
						known[num] = true;
						queue.offer(num);
					}
				}
			}
			queue.offer(i);
			while (!queue.isEmpty()) {	// 작은 노드 확인
				int cur = queue.poll();
				for (int num : shorter[cur]) {
					if (!known[num]) {
						known[num] = true;
						queue.offer(num);
					}
				}
			}
			for (int j = 0; j < N; j++) {
				if (!known[j]) {	// 하나라도 false라면 continue
					continue L1;
				}
			}
			EA++;	// 정답 수 증가
		}
	}
}
