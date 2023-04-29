package com.ssafy.day14_;
// gold 4. 알파벳
// dfs, back-tracking

import java.io.*;
import java.util.*;

public class Main_1987_전윤철 {

	static int N, M, maxLength = 0;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static char[][] board;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		// 알파벳 방문 여부 체크 배열
		visited = new boolean[26];
		// 시작 위치부터 길이 1 dfs 시작
		search(0, 0, 1);
		System.out.println(maxLength);
	}

	// dfs
	static void search(int i, int j, int depth) {
		int ni, nj;
		// 현재 알파벳 방문처리
		visited[board[i][j] - 65] = true;
		// 최대 이동 거리 갱신
		if (depth > maxLength) {
			maxLength = depth;
		}
		// 사방탐색
		for (int k = 0; k < delta.length; k++) {
			ni = i + delta[k][0];
			nj = j + delta[k][1];
			// 배열의 범위 내에 있고 해당 알파벳이 방문하지 않은 알파벳이라면
			if (isIn(ni, nj) && !visited[board[ni][nj] - 65]) {
				// 현재 위치에서 재귀 실행
				search(ni, nj, depth + 1);
			}
		}
		// 현재 위치에서 더 이상 진행할 수 없을 경우 현재 알파벳을 미방문 처리 후 이전 칸으로 이동
		visited[board[i][j] - 65] = false;
	}

	// 좌표가 배열의 범위 내에 있는지 확인
	static boolean isIn(int i, int j) {
		if (i < 0 || i >= N || j < 0 || j >= M) {
			return false;
		}
		return true;
	}
}