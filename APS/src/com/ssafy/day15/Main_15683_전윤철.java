package com.ssafy.day15;
// gold 4. 감시
// 비트마스킹, 중복순열, 구현

import java.io.*;
import java.util.*;

public class Main_15683_전윤철 {

	static int N, M, minBlindSpot = Integer.MAX_VALUE;
	static int[] direction, office[], save[];
	static List<int[]> cctvs;
	static int[] panopticon = {0b1000, 0b1010, 0b1100, 0b1110, 0b1111};
	static int[][] delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		office = new int[N][M];
		save = new int[N][M];
		cctvs = new ArrayList<int[]>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				save[i][j] = office[i][j];
				if (office[i][j]>0 && office[i][j]<6) {
					cctvs.add(new int[] {i, j, office[i][j], -1});
				}
			}
		}
		direction = new int[cctvs.size()];
		permute(0);
		System.out.println(minBlindSpot);
	}

	static void permute(int depth) {
		if (depth== cctvs.size()) {
			monitor();
			return;
		}
		for (int i = 0; i < 4; i++) {
			direction[depth] = i;
			permute(depth + 1);
		}
	}

	static void monitor() {
		for (int i = 0; i < cctvs.size(); i++) {
			for (int j = 0; j < 4; j++) {
				// 중복순열에 의한 해당 카메라의 회전 방향과 카메라의 감시 방향이 일치하지 않다면 스킵
				int temp = rotate(panopticon[cctvs.get(i)[2]-1], direction[i]);
				if ( (temp & (1 << j)) == 0 ) {
					continue;
				}
				watching(cctvs.get(i), j);
			}
		}
		// 최솟값 갱신
		minBlindSpot = Math.min(getCount(), minBlindSpot);
		// 다음 경우의 수를 위한 복원
		reset();
	}

	static void reset() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				office[i][j] = save[i][j];
			}
		}
	}

	static int getCount() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (office[i][j]==0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	// 현 좌표에서 현 방향으로 범위를 벗어나거나 벽을 만날때까지 감시 구역 체크
	static void watching(int[] co, int dir) {
		int ni = co[0]+delta[dir][0], nj = co[1]+delta[dir][1];
		while (isIn(ni, nj) && office[ni][nj]!=6) {
			if (office[ni][nj]==0) {
				office[ni][nj] = -1;
			}
			ni += delta[dir][0];
			nj += delta[dir][1];
		}
	}

	static boolean isIn(int i, int j) {
		return i>=0 && i<N && j>=0 && j<M;
	}

	// 방향 횟수만큼 감시방향 회전
	static int rotate(int cam, int i) {
		return ( (cam << i) & 0b00001111 ) | ( ( (cam << i) & 0b11110000 ) >> 4 );
	}

}
