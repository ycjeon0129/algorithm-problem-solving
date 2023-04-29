package com.ssafy.day22;
// silver 1. 쉬운 계단 수
// DP

import java.util.Scanner;

public class Main_10844_전윤철 {

	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		long[][] dp = new long[N+1][10];	// 길이별 자릿수를 마지막 값으로 갖는 계단수의 개수를 저장할 2차원 배열

		dp[1] = new long[] {0, 1, 1, 1, 1, 1, 1, 1, 1, 1};

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				if (isIn(j-1)) {
					dp[i][j] += dp[i-1][j-1];
					dp[i][j] %= 1_000_000_000;
				}
				if (isIn(j+1)) {
					dp[i][j] += dp[i-1][j+1];
					dp[i][j] %= 1_000_000_000;
				}
			}

		}

		long result = 0;

		for (int i = 0; i < 10; i++) {
			result += dp[N][i];
			result %= 1_000_000_000;
		}
		System.out.println(result);
	}

	private static boolean isIn(int num) {
		return !(num < 0 || num > 9);
	}

}
//
//// silver 1. 쉬운 계단 수
//// DP
//
//import java.util.Scanner;
//
//public class Main_10844_전윤철 {
//
//	static int N;
//
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		N = sc.nextInt();
//
//		long[][] dp = new long[N+1][10];	// 길이별 자릿수를 마지막 값으로 갖는 계단수의 개수를 저장할 2차원 배열
//
//		dp[1] = new long[] {0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
//
//		for (int i = 2; i <= N; i++) {
//			for (int j = 0; j < 10; j++) {
//				if (isIn(j-1)) {
//					dp[i][j] += dp[i-1][j-1];
//					dp[i][j] %= 1_000_000_000;
//				}
//				if (isIn(j+1)) {
//					dp[i][j] += dp[i-1][j+1];
//					dp[i][j] %= 1_000_000_000;
//				}
//			}
//
//		}
//
//		long result = 0;
//
//		for (int i = 0; i < 10; i++) {
//			result += dp[N][i];
//			result %= 1_000_000_000;
//		}
//		System.out.println(result);
//	}
//
//	private static boolean isIn(int num) {
//		return !(num < 0 || num > 9);
//	}
//
//}
