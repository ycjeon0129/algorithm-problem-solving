package com.ssafy.day29;

import java.io.*;
import java.util.*;

public class Main_2098_전윤철 {
	
	static int N, min;
	static final int INF = Integer.MAX_VALUE/100;
	static int[][] price, dp;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		price = new int[N][N];
		dp = new int[1<<N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				price[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < 1<<N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		min = tsp(1, 0);
		
		System.out.println(min);
	}

	private static int tsp(int visited, int city) {
		if ( visited == ( (1 << N) - 1 ) ) {
			if (price[city][0] == 0) {
				return INF;
			}
			return price[city][0];	// 마지막 도시와 집까지의 거리
		}
		
		if (dp[visited][city] != -1) {
			return dp[visited][city];
		}
		
		// 방문 처리
		dp[visited][city] = INF;
		
		for (int i = 0; i < N; i++) {
			
			// 방문한 곳은 재방문하지 않음
			if ( (visited & (1 << i) ) != 0 ) {
				continue;
			}
			
			// 연결되지 않은 도시는 방문할 수 없음
			if (price[city][i] == 0) {
				continue;
			}
			
			int res = tsp(visited | (1 << i), i ) + price[city][i];
			dp[visited][city] = Math.min(res, dp[visited][city]);
			
		}
		return dp[visited][city];
	}
}
