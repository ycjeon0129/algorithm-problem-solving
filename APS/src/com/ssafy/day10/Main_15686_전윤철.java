package com.ssafy.day10;
// gold 5. 치킨 배달

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15686_전윤철 {

	static int N, M, C, H, cityChickenDistance = Integer.MAX_VALUE;
	static int[][] city;
	static co[] chickensAll, chickensSelected, houses;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		city = new int[N][N];

		int cur;

		// 집과 치킨집의 개수 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				cur = Integer.parseInt(st.nextToken());
				city[i][j] = cur;
				if (cur == 1) {
					H++;
				} else if (cur == 2) {
					C++;
				}
			}
		}
		// 집, 치킨집, 살아남을 치킨집을 저장하는 배열
		houses = new co[H];
		chickensAll = new co[C];
		chickensSelected = new co[M];
		int indexC = 0, indexH = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (city[i][j] == 1) {
					houses[indexH++] = new co(i, j);
				} else if (city[i][j] == 2) {
					chickensAll[indexC++] = new co(i, j);
				}
			}
		}

		// 살아남을 M개의 치킨집을 고르는 조합 계산
		combi(0, 0);
		System.out.println(cityChickenDistance);
	}

	// 전체 치킨집 C개 중 살아남을 M개의 치킨집을 고르는 메서드
	static void combi(int start, int depth) {
		if (depth == M) {
			getChickenDistance();
			return;
		}
		for (int i = start; i < C; i++) {
			chickensSelected[depth] = chickensAll[i];
			combi(i + 1, depth + 1);
		}

	}

	// 선택된 치킨집들에 대하여 도시의 치킨 거리 계산
	private static void getChickenDistance() {
		int sum = 0;
		
		// 각 집들에 대해 치킨 거리 계산
		for (int i=0; i<H; i++) {
			int dist = Integer.MAX_VALUE;
			for (int j=0; j<M; j++) {
				dist = Math.min(dist, getDistance(houses[i], chickensSelected[j]));
			}
			sum += dist;
		}
		// 각 경우의 수에 대해 치킨 거리 계산 및 최솟값 갱신 
		cityChickenDistance = Math.min(cityChickenDistance, sum);
	}

	// 가로세로 거리 계산 메서드
	static int getDistance(co house, co chiken) {
		return Math.abs(house.i-chiken.i) + Math.abs(house.j-chiken.j);
	}

	static class co {
		int i;
		int j;

		public co(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
