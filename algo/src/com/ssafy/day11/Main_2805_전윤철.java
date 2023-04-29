package com.ssafy.day11;
// silver 2. 나무 자르기
// 수학, 이진트리

import java.io.*;
import java.util.*;

public class Main_2805_전윤철 {

	static int N, M;
	static long H;
	static long[] trees;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trees = new long[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(trees);
		lumberjack();
		System.out.println(H);
	}

	// 가장 큰 나무부터 벌목
	static void lumberjack() {
		int idx = N;
		long tree = 0, sum = 0;
		// 현재 높이까지 나무 길이의 합이 M보다 작다면 다음으로 큰 나무까지  반복
		while (sum < M) {
			sum += ((trees[idx--] - trees[idx]) * ++tree);
		}
		// 목표 길이와 현재 벌목한 길이 사이의 차를 자른 나무의 수로 나눔
		long gap = (sum - M);
		H = (trees[idx] + (gap / tree));
	}

}
