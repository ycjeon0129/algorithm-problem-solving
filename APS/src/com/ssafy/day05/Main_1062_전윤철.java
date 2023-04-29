package com.ssafy.day05;
// gold 4. 가르침
// 조합 + 비트마스킹

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1062_전윤철 {
	
	static int N, K, result = 0;
	static String[] words;
	static int bina = 532741;
	static final int iter = 26;		// 알파벳 갯수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken())-5;

		// 단어를 입력받고 앞 뒤 기본 단어를 제거함
		words = new String[N];
		for (int i=0; i<N; i++) {
			String temp = br.readLine();
			words[i] = temp.substring(4, temp.length()-4);
		}

		// 배운 글자 수가 5개 이하라면 0 출력 후 종료
		if (K < 0) {
			System.out.println(0);
		} else {
			combi(0, 0);
			System.out.println(result);
		}


	}

	// 조합 계산
	static void combi(int depth, int start) {
		if (depth==K) {
			canRead();
			return;
		}
		for (int i=start; i<iter; i++) {
			// 첫 재귀함수 내의 반복문일 경우 정의 바이너리 값을 1로 설
			if (start==0) {
//				bina |= 1 << 0;
//				bina |= 1 << 2;
//				bina |= 1 << 8;
//				bina |= 1 << 13;
//				bina |= 1 << 19;
				bina = 532741;
			}
			// a, c, i, n, t의 경우 조합에서 제외
			if (i == 0 || i == 2 || i == 8 || i == 13 || i == 19) {
				continue;
			}
			// 인덱스 값에 해당하는 비트를 1로 설정
			bina |= 1 << i;
			combi(depth+1, i+1);
			bina &= ~(1 << i);
		}
	}


	// 해당 글자 조합의 경우의 수에 대해 최대 단어 개수 계산
	static void canRead() {
		int temp = 0;
		for (int i=0; i<N; i++) {
			// 각각의 단어에서 사용된 글자가 배운 글자 조합에 모두 포함되는지 확인
			int value = parse(words[i]);
			if ( (value | bina) == bina ) {
				temp++;
			}
		}
		result = Math.max(result, temp);
	}

	static int parse(String word) {
		int value = 0;
		// 단어에서 사용된 각 글자에 대해 해당 비트를 1로 설
		for (int i=0; i<word.length(); i++) {
			int temp = word.charAt(i) - 'a';
			value |= 1 << temp;
		}
		return value;
	}
}
