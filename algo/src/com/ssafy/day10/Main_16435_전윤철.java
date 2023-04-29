package com.ssafy.day10;
// silver 5. 스네이크버드
// 그리디

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16435_전윤철 {
	
	static int N, L;
	static int[] fruits;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		fruits = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}
		
		// 과일의 높이를 오름차순 정렬
		Arrays.sort(fruits);
		
		int cur = 0;
		// 현재 과일의 높이가 스네이크버드의 길이보다 낮거나 같다면 길이를 1 늘이고 다음 과일을 탐색 => 과일의 높이가 스네이크버드의 길이보다 높을때까지
		while (fruits[cur++] <= L) {
			L++;
			if (cur == N) {
				break;
			}
		}
 		
		System.out.println(L);
		
	}
}
