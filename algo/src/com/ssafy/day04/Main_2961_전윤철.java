package com.ssafy.day04;
// silver 2. 도영이가 만든 맛있는 음식

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2961_전윤철 {
	static Ingredient[] ingredients;
	static boolean[] isSelected;
	static int N;
	static int delicious = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		// 재료 정보 저장 배열
		ingredients = new Ingredient[N];
		// 해당 재료 선택 여부 저장 배열
		isSelected = new boolean[N];
		int s, b;
		// 각 재료 별 정보 입력
		for (int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			ingredients[n] = new Ingredient(s, b);
		}
		// 가장 맛있는 음식을 찾아서
		newMenu(0);
		
		System.out.println(delicious);
	}
	
	static void newMenu(int count) {
		// 부분 집합 탐색 완료 시 맛 비교 실행
		if (count==N) {
			isTasty();
			return;
		}
		// 해당 인덱스에서 O/X 경우의 수 고려
		isSelected[count] = true;
		newMenu(count+1);
		isSelected[count] = false;
		newMenu(count+1);
	}
	
	static void isTasty() {
		// 최종 신맛, 쓴맛을 위한 기본값
		int S = 1;
		int B = 0;
		for (int i=0; i<N; i++) {
			// 선택한 재료의 맛을 요리에 계산
			if (isSelected[i]) {
				S *= ingredients[i].s;
				B += ingredients[i].b;
			}
		}
		// 재료를 하나도 선택하지 않았을 경우
		if (B==0) {
			return;
		}
		// 가장 맛있는 재료 조합 저장
		delicious = Math.min( Math.abs(S-B), delicious);
		
		
	}
	
	static class Ingredient {
		int s;
		int b;
		Ingredient(int s, int b) {
			this.s = s;
			this.b = b;
		}
	}

}
