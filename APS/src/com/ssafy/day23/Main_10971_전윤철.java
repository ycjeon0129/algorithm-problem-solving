package com.ssafy.day23;
// silver 2. 외판원 순회 2
// 순열

import java.io.*;
import java.util.*;

public class Main_10971_전윤철 {

	static int N, budget;
	static int[] path;
	static int[][] city;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		city = new int[N][N];
		budget = Integer.MAX_VALUE;
		path = new int[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		path[0] = 0;	// 시작 도시 설정
		permut(1);		// 나머지 도시에 대해 순열로 방문 순서 결정

		System.out.println(budget);

	}

	static void permut(int depth) {	// N개의 도시 방문 순서 => 순열
		if (depth == N) {
			travel();
			return;
		}
		for (int i = 1; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				path[depth] = i;
				permut(depth + 1);
				visited[i] = false;
			}
		}

	}

	static void travel() {
		int tmpBudget = 0;
		for (int i = 0; i < N; i++) {	// 방문 순서에 맞게 각 도시 방문 비용 합
			int price = city[path[i]][path[(i + 1) % N]];
			if (price == 0) {	// 다음 순서의 도시를 갈 수 없는 경우 해당 순열 폐기
				return;
			}
			tmpBudget += price; 
		}
		budget = Math.min(budget, tmpBudget);	// 최소 비용 갱신
	}
}
