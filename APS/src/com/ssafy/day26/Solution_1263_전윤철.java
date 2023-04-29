package com.ssafy.day26;
// D6. 사람 네트워크2
// 최단거리 w/가중치, 플로이드-워샬

import java.io.*;
import java.util.*;

public class Solution_1263_전윤철 {
	
	static int N, answer;
	static final int INF = 1_000;
	static int[][] network;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			network = new int[N][N];
			answer = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					network[i][j] = Integer.parseInt(st.nextToken());
					if (i!=j && network[i][j]==0) {	// 초기에 연결되지 않은 정점간의 간선의 경우 INF 처리
						network[i][j] = INF;
					}
				}
			}
			connect();
			count();
			sb.append(String.format("#%d %d\n", t, answer));
		}
		System.out.println(sb);
	}

	static void count() {	// 간선 별 다른 모든 간선으로의 가중치의 합 계산 및 최솟값 갱신
		int sum;
		for (int i = 0; i < N; i++) {	
			sum = 0;
			for (int j = 0; j < N; j++) {
				sum += network[i][j];
			}
			answer = Math.min(answer, sum);
		}
	}

	static void connect() {
		for (int k = 0; k < N; k++) {	// 경유지
			for (int i = 0; i < N; i++) {	// 출발점
				if (i == k) continue;
				for (int j = 0; j < N; j++) {	// 도착점
					if (j == k || j == i) continue;
					if (network[i][k] + network[k][j] < network[i][j]) {	// 최소 가중치 갱신
						network[i][j] = network[i][k] + network[k][j]; 
					}
				}
			}
		}
	}
}
