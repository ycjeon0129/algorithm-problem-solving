package com.ssafy.day23;
// silver 5. 다리 놓기
// DP

import java.io.*;
import java.util.StringTokenizer;

public class Main_1010_전윤철 {
	
	static int N, M;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			dp = new int[N+1][M+1];
			
			for (int i = 1; i <= N; i++) {
				for (int j = i; j <= M; j++) {
					if (i == j) {
						dp[i][j] = 1;
					} else if (i == 1) {
						dp[i][j] = j;
					}
					else {
						// (N, M)은 (N, M-1)의 경우의 수 + (N-1, M-1)의 경우의 수(N번째 출발점에서 M번째 도착점으로 가는 다리를 정한 뒤 나머지 가능성) 
						dp[i][j] = dp[i][j-1] + dp[i-1][j-1]; 
					}
				}
			}
			bw.write(dp[N][M]+"\n");
		}
		bw.flush();
		bw.close();
	}

}
