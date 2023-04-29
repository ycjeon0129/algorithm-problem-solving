package com.ssafy.day22;
// gold 5. 합분해
// DP

import java.util.Arrays;
import java.util.Scanner;

public class Main_2225_전윤철 {

	static int N, K;
	static int[][] dp;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		dp = new int[K+1][N+1];

		if (K >= 2) {	// K가 기저조건값 범위 내의 값인 경우 예외 처리 if문
			for (int i = 1; i <= N; i++) {	// 기저조건 처리
				dp[1][i] = 1;
				dp[2][i] = i+1;
			}
		} else if (K >= 1) {
			for (int i = 1; i <= N; i++) {
				dp[1][i] = 1;
			}
		}

		for (int i = 0; i <= K; i++) {
			dp[i][0] = 1;
		}

		for (int i = 3; i <= K; i++) {	// DP
			for (int j = 1; j <= N; j++) {
				dp[i][j] = round(dp[i-1][j]) + round(dp[i][j-1]);
			}
		}

		System.out.println(round(dp[K][N]));
	}

	static int round(int num) {
		return num % 1_000_000_000;
	}
}
