package com.ssafy.day23;
// gold 5. 파이프 옮기기 1
// DP, 구현

import java.io.*;
import java.util.StringTokenizer;

public class Main_17070_전윤철 {
	
	static int N;
	static int[][] room;
	static int[][][] dp;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		room = new int[N][N];
		dp = new int[N][N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// k(0, 1, 2) 는 각각 해당 좌표로 들어오는 파이프(가로, 대각선, 세로) 의미
		dp[0][1][0] = 1;
		dp[0][1][1] = 0;
		dp[0][1][2] = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (room[i][j] == 0) {	// 해당 위치가 벽이 아닌 경우
					for (int k = 0; k < 3; k++) {	// 각 파이프 경우에 대해
						if (isValid(i, j, k)) {	// 해당 파이프 방향에 대한 연결 가능 여부 유효성 검증
							if (k == 0) {	// 각 파이프 모양에 대해 연결 가능한 경우의 수 합산
								dp[i][j][k] += dp[i][j-1][0];
								dp[i][j][k] += dp[i][j-1][1];
							} else if (k == 1) {
								dp[i][j][k] += dp[i-1][j-1][0];
								dp[i][j][k] += dp[i-1][j-1][1];
								dp[i][j][k] += dp[i-1][j-1][2];
							} else {
								dp[i][j][k] += dp[i-1][j][1];
								dp[i][j][k] += dp[i-1][j][2];
							}
						}
					}					
				}
			}
		}
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);	// 최종 목적지에 도달 가능한 모든 파이프의 합 출력

	}
	
	static boolean isValid(int i, int j, int k) {
		if (k == 0) {	// 가로 파이프의 경우
			if (isIn(i, j-1) && room[i][j-1] == 0) {	// 좌측 칸이 범위 내이고 벽이 아닌 경우
				return true;
			}
		} else if (k == 1) {
			if (isIn(i-1, j-1) && room[i][j-1] == 0 && room[i-1][j-1] == 0 && room[i-1][j] == 0) {	// 좌상단 칸이 범위 내이고 주변 칸이 벽이 아닌 경우
				return true;
			}
		} else if (k == 2) {
			if (isIn(i-1, j) && room[i-1][j] == 0) {	// 상단 칸이 범위 내이고 벽이 아닌 경우
				return true;
			}
		}
		return false;
	}
	
	static boolean isIn(int i, int j) {	// 배열 범위 확인 메서드
		return !(i<0 || i>= N || j<0 || j>=N);
	}

}
