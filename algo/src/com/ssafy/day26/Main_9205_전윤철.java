package com.ssafy.day26;
// gold 5. 맥주 마시면서 걸어가기
// BFS, 그래프

import java.io.*;
import java.util.*;

public class Main_9205_전윤철 {
	
	static int N;
	static final int BEER = 20, STEP = 50;
	static boolean able, visited[];
	static Node cvs[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		int tempY, tempX;
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			able = false;
			visited = new boolean[N+2];
			cvs = new Node[N+2];
			for (int i = 0; i < N+2; i++) {	// cvs[0]: home, cvs[n+1]: festival
				st = new StringTokenizer(br.readLine());
				tempY = Integer.parseInt(st.nextToken());
				tempX = Integer.parseInt(st.nextToken());
				cvs[i] = new Node(tempY, tempX);
			}
			walk();
			if (able) {
				sb.append("happy\n");
			} else {
				sb.append("sad\n");
			}
			
		}
		System.out.println(sb);
		

	}

	static void walk() {	
		int idx;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(0);	// 집 큐 삽입
		visited[0] = true;
		while (!queue.isEmpty()) {
			idx = queue.poll();
			for (int i = 1; i <= N+1; i++) {	// 모든 편의점 및 페스티벌에 대해 거리 탐색
				if (i == idx) {
					continue;
				}
				if (!visited[i] && (calDist(idx, i) <= BEER * STEP) ) {	// 미방문한 이동 가능한 좌표에 대해 (방문한 좌표의 경우 이미 큐에 들어가 있기 때문에)
					if (i == N+1) {	// 다음 이동 가능 좌표가 페스티벌일 경우
						able = true;	// 플래그 토글 및 종료
						return;
					}
					queue.offer(i);	// 해당 편의점 큐 삽입 및 방문처리
					visited[i] = true;
				}
			}
		}
	}

	static int calDist(int i, int j) {	// 맨해튼 거리 계산 메서드
		return Math.abs(cvs[i].x - cvs[j].x) + Math.abs(cvs[i].y - cvs[j].y);
	}

	static class Node {
		int y, x;
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}		
	}

}
