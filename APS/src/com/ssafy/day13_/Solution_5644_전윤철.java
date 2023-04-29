package com.ssafy.day13_;
// 모의 SW 역량테스트. 무선 충전
// 그래프 탐색, 구현

import java.io.*;
import java.util.*;

public class Solution_5644_전윤철 {

	static int M, A, sum;
	static final int size = 10;
	static int[] playerA, playerB, delta[] = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[][] belkins;
	static List<Integer[]>[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			map = new ArrayList[size][size];
			sum = 0;
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			playerA = new int[M];
			playerB = new int[M];
			belkins = new int[A][4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				playerA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				playerB[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				belkins[i][1] = Integer.parseInt(st.nextToken())-1;
				belkins[i][0] = Integer.parseInt(st.nextToken())-1;
				belkins[i][2] = Integer.parseInt(st.nextToken());
				belkins[i][3] = Integer.parseInt(st.nextToken());
			}
			// 최대 충전값 계산 
			charge();

			bw.write(String.format("#%d %d\n", t, sum));
		}
		bw.flush();
		bw.close();
	}

	// 각 플레이어가 움직이며 충전 시작
	static void charge() {
		setChargingFeild();
		int ia = 0, ja = 0, ib = 9, jb = 9;
		boolean connectA, connectB;
		for (int s = 0; s <= M; s++) {
			connectA = map[ia][ja] != null;
			connectB = map[ib][jb] != null;
			// 한 사람만 충전기와 연결되어 있다면 해당 충전기의 성능 합산
			if (connectA && !connectB) {
				sum += map[ia][ja].get(0)[1];
			} else if (!connectA && connectB) {
				sum += map[ib][jb].get(0)[1];
			} 
			// 두 사람이 모두 충전기와 연결되어 있다면
			else if (connectA && connectB) {
				// 플레이어 A의 최대 충전기 성능 합산
				sum += map[ia][ja].get(0)[1];
				// 두 사람이 같은 충전기에 연결 중이라면
				if (map[ia][ja].get(0)[0] == map[ib][jb].get(0)[0]) {
					// 두 사람 모두 복수의 충전기에 연결중이라면
					if (map[ib][jb].size() > 1 && map[ia][ja].size() > 1) {
						// 각 플레이어의 2순위 성능 충전기 중 큰 값을 합산
						sum += Math.max(map[ia][ja].get(1)[1], map[ib][jb].get(1)[1]);
					} 
					// 한 사람만 복수의 충전기에 연결중이라면 해당 플레이어의 2순위 성능 충전기 합산
					else if (map[ia][ja].size() > 1) {
						sum += map[ia][ja].get(1)[1];
					} else if (map[ib][jb].size() > 1) {
						sum += map[ib][jb].get(1)[1];
					}
				} 
				// 플레이어 B의 최대 성능 충전기 합산
				else {
					sum += map[ib][jb].get(0)[1];
				}
			}
			// 최대 시간에 도달했다면 중지
			if (s==M) {
				break;
			}
			// 각 플레이어를 다음 위치로 이동
			ia += delta[playerA[s]][0];
			ja += delta[playerA[s]][1];
			ib += delta[playerB[s]][0];
			jb += delta[playerB[s]][1];
		}
	}

	// 충전 가능 구역 설정
	static void setChargingFeild() {
		// 무선 충전기를 성능이 좋은 순으로 내림차순 정렬
		Arrays.sort(belkins, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[3] - o1[3];
			}
		});
		for (int i = 0; i < belkins.length; i++) {
			int ai = belkins[i][0], aj = belkins[i][1], ar = belkins[i][2];
			for (int j = ai - ar; j <= ai + ar; j++) {
				for (int k = aj - ar; k <= aj + ar; k++) {
					// 검사 좌표가 배열의 범위 내에 있고 충전기의 범위  내라면
					if (isIn(j, k, ai, aj, ar)) {
						// 현 좌표에 충전기 정보가 없을 경우 생성
						if (map[j][k] == null) {
							map[j][k] = new ArrayList<>();
						}
						// 현재 충전기의 번호와 성능을 좌표에 저장
						Integer[] temp = { i, belkins[i][3] };
						map[j][k].add(temp);
					}
				}
			}
		}
	}

	// 충전기의 범위 내인지 확인
	static boolean isIn(int ni, int nj, int i, int j, int range) {
		if (ni < 0 || ni >= size || nj < 0 || nj >= size || (Math.abs(i - ni) + Math.abs(j - nj)) > range) {
			return false;
		}
		return true;
	}
}
