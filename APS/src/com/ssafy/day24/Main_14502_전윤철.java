package com.ssafy.day24;
// gold 4. 연구소
// 조합, 그래프, BFS

import java.io.*;
import java.util.*;

public class Main_14502_전윤철 {
	
	static int N, M, safeArea;
	static int[][] lab, saved, wall, delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static List<int[]> candies, virus;
	static Queue<int[]> queue;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][M];	// 연구실 2차원 배열
		saved = new int[N][M];	// 복원을 위한 2차원 배열
		wall = new int[3][2];	// 벽
		candies = new ArrayList<int[]>();	// 벽이 될 수 있는 후보
		virus = new ArrayList<int[]>();	// 초기 바이러스 위치
		safeArea = -1;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if (lab[i][j] == 0) {
					candies.add(new int[] {i, j});
				} else if (lab[i][j] == 2) {
					virus.add(new int[] {i, j});
				}
				saved[i][j] = lab[i][j];
			}
		}
		comb(0, 0);
		System.out.println(safeArea);
	}

	static void comb(int depth, int start) {	// 후보 중 벽을 3개 뽑은 경우 바이러스 확산 시작
		if (depth == wall.length) {	 
			spread();
			return;
		}
		for (int i = start; i < candies.size(); i++) {
			wall[depth][0] = candies.get(i)[0];
			wall[depth][1] = candies.get(i)[1];
			comb(depth+1, i+1);
		}
	}

	static void spread() {
		queue = new ArrayDeque<int[]>();
		for (int i = 0; i < wall.length; i++) {	// 벽 시공
			lab[wall[i][0]][wall[i][1]] = 1;
		}
		for (int i = 0; i < virus.size(); i++) {	// 초기 바이러스 큐에 삽입
			queue.offer(virus.get(i));
		}
		int ny, nx;
		int[] co = null;
		while (!queue.isEmpty()) {	// 더 이상 확산할 수 없을 때까지
			co = queue.poll();
			for (int d = 0; d < delta.length; d++) {	// 4방탐색
				ny = co[0] + delta[d][0];
				nx = co[1] + delta[d][1];
				if (isIn(ny, nx) && lab[ny][nx]==0) {	// 다음 위치가 배열 내 빈 공간일 경우
					lab[ny][nx] = 2;
					queue.offer(new int[] {ny, nx});
				}
			}
		}
		count();
		restore();
	}

	static void restore() {	// 다음 경우의 수를 위해 배열 복원
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				lab[i][j] = saved[i][j];
			}
		}
	}

	static void count() {	// 안전 공간 넓이 계산
		int area = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (lab[i][j] == 0) {
					area++;
				}
			}
		}
		safeArea = Math.max(area, safeArea);
	}

	static boolean isIn(int i, int j) {
		return !(i<0 || i>=N || j<0 || j>=M);
	}
}
