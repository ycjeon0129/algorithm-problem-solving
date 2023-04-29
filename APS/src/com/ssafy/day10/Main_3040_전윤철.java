package com.ssafy.day10;
// bronze 2. 백설 공주와 일곱 난쟁이
// 조합, 브루트 포스

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_3040_전윤철 {
	
	static int sum;
	static int[] dwarves, lier;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dwarves = new int[9];
		lier = new int[2];
		
		// 7 난쟁이 모자 숫자의 합이 100인 것을 이용하여 가짜 난쟁이 둘의 합을 구함
		for (int i = 0; i < 9; i++) {
			dwarves[i] = Integer.parseInt(br.readLine());
			sum += dwarves[i]; 
		}
		sum -= 100;
		combi(0, 0);
	}

	// 9 난쟁이 중 두 난쟁이가 가짜인 경우 계산
	static void combi(int start, int depth) {
		if (depth == 2) {
			check();
			return;
		}
		for (int i = start; i < dwarves.length; i++) {
			lier[depth] = dwarves[i];
			combi(i+1, depth+1);
		}
	}

	// 임의로 고른 두 난쟁이의 모자의 합 계산
	static void check() {
		int sumLier = 0;
		for (int i = 0; i < lier.length; i++) {
			sumLier += lier[i];
		}
		if (sumLier == sum) {
			print();
		}
	}

	// 두 난쟁이가 가짜라면 이를 제외한 난쟁이의 숫자 출력
	static void print() {
		for (int i = 0; i < dwarves.length; i++) {
			if (dwarves[i] != lier[0] && dwarves[i] != lier[1]) {
				System.out.println(dwarves[i]);
			}
		}
		
	}

}
