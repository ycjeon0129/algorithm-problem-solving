package com.ssafy.day22;
// silver 1. RGB거리
// DP

import java.io.*;
import java.util.*;

public class Main_1149_전윤철 {

	static int N;
	static int[][] RGB, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][3];
		RGB = new int[N + 1][3];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				RGB[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < 3; i++) {
			dp[1][i] = RGB[1][i];	// 시작점의 최솟값은 각 값
		}
		for (int i = 2; i <= N; i++) {	// N번째 집까지 DP 시작
			for (int j = 0; j < 3; j++) {	// 각 순서의 RGB집에 대하여
				dp[i][j] = RGB[i][j] + Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]);	// 현재 집과 색이 다른 직전 최적해 중 최솟값 활용
			}
		}
		int least = Math.min(dp[N][0], dp[N][1]);
		least = Math.min(least, dp[N][2]);
		System.out.println(least);
	}
}
