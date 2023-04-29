package com.ssafy.day24;
// gold 5. 평범한 배낭
// DP

import java.io.*;
import java.util.*;

public class Main_12865_전윤철 {
	
	static int N, K;
	static int[][] items, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		items = new int[N+1][2];	// 각 물품의 무게와 가치
		dp = new int[N+1][K+1];	// 각 물품에 대한 최적해를 저장할 2차원 배열
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0] = Integer.parseInt(st.nextToken());	// w
			items[i][1] = Integer.parseInt(st.nextToken());	// v
		}
		
		pack();
		
		System.out.println(dp[N][K]);
		

	}

	static void pack() {
		for (int i = 1; i <= N; i++) {
			for (int w = 1; w <= K; w++) {
				if (w < items[i][0]) {	// 현재 무게에서 해당 물건을 담을 수 없다면
					dp[i][w] = Math.max(dp[i-1][w], dp[i][w-1]);	// 현재 무게의 기존 값과 이전 무게의 값 중 최댓값 저장
				}
				else {	// 현재 무게에서 해당 물건을 담을 수 있다면
					dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-items[i][0]]+items[i][1]);	// 전체 제한 무게에서 현 물건을 제거한 최댓값과 현 무게의 기존 값 중 최댓값 저장
				}
			}
		}
		
	}

}
