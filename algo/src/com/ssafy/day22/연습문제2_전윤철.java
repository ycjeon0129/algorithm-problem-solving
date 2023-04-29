package com.ssafy.day22;

import java.util.Scanner;

public class 연습문제2_전윤철 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		int[] stick = new int[num+1];
		
		stick[1] = 2;
		stick[2] = 5;
		
		for (int i = 3; i <= num; i++) {
			stick[i] = stick[i-1] * 2 + stick[i-2];
			// 길이가 i인 막대를 만드는 경우의 수
			// 1. 길이가 i-2 인 막대에서 빨간 막대 추가
			// 2. 길이가 i-1 인 막대에서 노란 막대 추가
			// 3. 길이가 i-1 인 막대에서 파란 막대 추가
		}
		System.out.println(stick[num]);		
	}

}
