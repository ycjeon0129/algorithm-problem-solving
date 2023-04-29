package com.ssafy.day11;
// 탈주범 검거
// bfs, 그래프

import java.io.*;
import java.util.*;

public class Solution_1953_전윤철 {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, R, C, L, cases;
	static int[][] map, isVisited;
	static Queue<int[]> queue;
	// 각각 파이프의 방향에 따른 연결 방향
	static int[] di = {-1, 0, 1, 0, -1, 1, 0, 0, -1, 0, 0, 1, 0, 1, -1, 0};
	static int[] dj = {0, -1, 0, 1, 0, 0, -1, 1, 0, 1, 1, 0, -1, 0, 0, -1};
	// 해당 파이프의 방향에서 이동할 수 있는 다음 파이프 번호
	static int[] NORTH = {1, 2, 5, 6}, WEST = {1, 3, 4, 5},	SOUTH = {1, 2, 4, 7}, EAST = {1, 3, 6, 7};
	static int[][] connection = { NORTH, WEST, SOUTH, EAST, NORTH, SOUTH, WEST, EAST, NORTH, EAST, EAST, SOUTH, WEST, SOUTH, NORTH, WEST };


	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			cases = 0;
			queue = new ArrayDeque<int[]>();
			map = new int[N][M];
			isVisited = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j]==0) {
						// 이동할 수 없는 좌표의 경우 방문값을 -1로 설정
						isVisited[i][j] = -1;
					}
				}
			}
			find();
			bw.write(String.format("#%d %d\n", t, cases));
		}
		bw.flush();
		bw.close();
	}

	static void find() {
		// 시작 맨홀의 좌표를 큐에 넣고 bfs
		int[] start = { R, C }, temp;
		queue.offer(start);
		isVisited[R][C] = -1;
		cases++;
		while (queue.size() > 0) {
			temp = queue.poll();
			// 현재 좌표에서 이동할 수 있는 경우 탐색
			move(temp[0], temp[1]);
		}
	}

	static void move(int i, int j) {
		int start=0, end=0, ni, nj;
		// 현재 좌표의 파이프의 종류에 따른 이동 가능 방향 설정
		switch (map[i][j]) {
		case 1:
			start = 0;
			end = 4;
			break;
		case 2:
			start = 4;
			end = 6;
			break;
		case 3:
			start = 6;
			end = 8;
			break;
		case 4:
			start = 8;
			end = 10;
			break;
		case 5:
			start = 10;
			end = 12;
			break;
		case 6:
			start = 12;
			end = 14;
			break;
		case 7:
			start = 14;
			end = 16;
		}
		// 현재 파이프에서 이동 가능한 방향 탐색
		for (int k = start; k < end; k++) {
			ni = i + di[k];
			nj = j + dj[k];
			if (ni<0 || ni>=N || nj<0 || nj>=M) {
				continue;				
			}
			// 방문하지 않은 파이프라면
			if (isVisited[ni][nj]==0) {
				// 현재 파이프의 각 이동방향과 해당 방향의 파이프가 연결되었는지 확인
				boolean movable = false;
				for (int l = 0; l < connection[k].length; l++) {
					if (connection[k][l] == map[ni][nj]) {
						movable = true;
					}
				}
				// isVisited 배열의 음수값의 형태로 흉악범이 이동한 시간 표시
				// 다음 좌표의 이동 시간은 현재 좌표의 이동 시간보다 1 큼
				if (movable) {
					isVisited[ni][nj] = isVisited[i][j] - 1;
					// 다음 좌표의 이동 시간이 제한 시간보다 크다면 종료
					if (Math.abs(isVisited[ni][nj]) > L) {
						return;
					}
					// 다음 좌표를 큐에 넣고 흉악범이 있을 수 있는 좌표의 수 1 증가
					int[] next = {ni, nj};
					queue.offer(next);
					cases++;
				}
			}
		}
	}
}
