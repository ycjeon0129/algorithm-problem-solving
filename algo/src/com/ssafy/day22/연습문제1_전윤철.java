package com.ssafy.day22;

import java.util.Scanner;

public class 연습문제1_전윤철 {

	static int[] floor;
	
	static void color(int num) {
		
		for (int i = 3; i <= num; i++) {
			floor[i] = floor[i-1] + floor[i-2];
			// 직전값 중 분기할 수 있는 값은 y로 끝나는 값 
			// y로 끝나는 값은 floor[i-2] => i-1과 관련없이 항상 y가 나올 수 있음
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		floor = new int[num+1];
		
		floor[1] = 2;
		floor[2] = 3;
		
		color(num);
		
		System.out.println(floor[num]);	
	}

}
