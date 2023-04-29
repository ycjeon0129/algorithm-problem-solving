package boj.silver_._1_;
// DP

import java.io.*;
import java.util.*;

public class BOJ1149_RGB거리 {

	static int N, answer;
	static int[][] houses, dp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		houses = new int[N][3];	// 입력값
		dp = new int[N][3];	// DP
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			houses[i][0] = Integer.parseInt(st.nextToken());
			houses[i][1] = Integer.parseInt(st.nextToken());
			houses[i][2] = Integer.parseInt(st.nextToken());
		}
		colorize();
		System.out.println(answer);
	}

	static void colorize() {
		for (int i = 0; i < 3; i++) {
			dp[0][i] = houses[0][i];	// 첫 선택의 경우 항상 최선
		}
		for (int i = 1; i < N; i++) {	// N-1까지 최선의 선택이 보장되었다는 가정 하에
			for (int j = 0; j < 3; j++) {
				// 해당 순서의 집에 도달할 수 있는 최적의 경우의 수를 거쳐 해당 순서 집 채색
				dp[i][j] = houses[i][j] + Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]);
			}
		}
		// N번째 집 중 최소 비용 출력
		answer = Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2]);
	}
}
