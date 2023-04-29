package com.ssafy.day07;
// D3. 한빈이와 Spot Mart
// ~

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_9229_전윤철 {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, weight, max;
	static int[] snacks, peeked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			snacks = new int[N];
			peeked = new int[2];
			// 양 손에 과자 두개를 들 수 없다면 출력값 -1
			max = -1;
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			
			// 두개의 과자를 고르는 조합
			combi(0, 0);
			bw.write(String.format("#%d %d\n", t, max));
		}
		bw.flush();
		bw.close();
	}

	static void combi(int start, int depth) throws IOException {
		if (depth == peeked.length) {
			// 두개를 골랐을때 무게의 합이 무게 제한보다 작다면 최댓값 비교
			int temp = peeked[0] + peeked[1];
			if (temp <= M) {
				max = Math.max(max, temp);
			}
			return;
		}
		for (int i=start; i<N; i++) {
			peeked[depth] = snacks[i];
			combi(i+1, depth+1);			
		}
		
	}

}
