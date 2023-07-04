package com.ssafy.day22;
// silver 3. 1로 만들기
// DP

import java.util.Scanner;

public class Main_1463_전윤철 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N+1];
		arr[1] = 0;
		
		for (int i = 2; i <= N; i++) {
			int least = arr[i-1];	// i-1에서 (+1)하여 도달하는 값을 최솟값으로 가정
			if ( (i % 2) == 0) {	// i가 2의 배수라면 
				least = Math.min(least, arr[i/2]);	// 기존 최솟값과 i/2의 최적해 중 최솟값 갱신 
			}
			if ( (i % 3) == 0) {	// i가 3의 배수라면 
				least = Math.min(least, arr[i/3]);	// 기존 최솟값과 i/3의 최적해 중 최솟값 갱신
			}
			arr[i] = least + 1;	// i에 직접 도달할 수 있는 위치의 값을 최솟값으로 계산 -> 최솟값에 1을 더함
		}
		System.out.println(arr[N]);		
	}
}
