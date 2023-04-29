package com.ssafy.day10;
// silver 4. 설탕 배달
// 그리디

import java.util.Scanner;

public class Main_2839_전윤철 {
	
	static int N, coins = -1;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		
		// 최대한 5kg 봉지를 챙겼을 경우 5kg 봉지의 개수
		int timesOfFive = N / 5;
		int mods;
		
		// 5kg 봉지를 최대한 챙겼을 경우부터 하나도 챙기지 않은 경우까지
		for (int i = timesOfFive; i >= 0; i--) {
			// 3kg 봉지로 챙겨야 하는 설탕의 무게
			mods = N - (5 * i);
			// 무게가 3의 배수일 경우 봉지의 합을 구하고 반복 중단
			if ( mods % 3 == 0 ) {
				coins = i + mods / 3;
				break;
			}
		}
		// 3과 5로 정확한 무게를 맞출 수 없을 경우 디폴트 값 -1 출력
		System.out.println(coins);
	}

}
