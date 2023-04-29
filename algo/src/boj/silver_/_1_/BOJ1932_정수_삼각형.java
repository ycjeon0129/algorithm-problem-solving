package boj.silver_._1_;
// DP

import java.io.*;
import java.util.*;

public class BOJ1932_정수_삼각형 {

	static int N;
	static int[][] intTriangle, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		intTriangle = new int[N][N];
		dp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				intTriangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		getMaxSum();
	}

	static void getMaxSum() {
		dp[0][0] = intTriangle[0][0];
		for (int i = 1; i < N; i++) {
			dp[i][0] = dp[i-1][0] + intTriangle[i][0];	// 사이드의 경우 최적 경로가 하나
			for (int j = 1; j < i; j++) {
				dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + intTriangle[i][j]; // 이전 단까지의 값이 최적해라는 가정하에 현재 단에서 최적해 선택
			}
			dp[i][i] = dp[i-1][i-1] + intTriangle[i][i];	// // 사이드의 경우 최적 경로가 하나
		}
		Arrays.sort(dp[N-1]);
		System.out.println(dp[N-1][N-1]);
	}

}
