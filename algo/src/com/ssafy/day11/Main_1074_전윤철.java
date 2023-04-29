package com.ssafy.day11;
// Z.
// DnC

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_전윤철 {

	static int N, R, C, visitTime;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		visitTime = divideAndConquer(N, R, C);
		
		System.out.println(visitTime);
	}

	static int divideAndConquer(int n, int r, int c) {
		// 더 이상 분할할 수 없다면 현재 구역 내에서 방문 순서를 반환
		if (n==1) {
			return r*2 + c;
		}
		// 사분할 구역 중 위치 계산 용 플래그
		boolean flagR = false, flagC = false;
		// 분할한 구역의 최대 행, 열
		int divider = (int) Math.pow(2, n-1);
		// 해당 구역을 넘는다면 플래그를 켠 후 값을 구역 내로 조정
		if (r >= divider) {
			r = r-divider;
			flagR = true;
		}
		if (c >= divider) {
			c = c-divider;
			flagC = true;
		}
		// 한 구역에서 방문하는 마지막 번호 계산
		int block = (int) Math.pow(2, n*2 - 2);
		int now = flagR ? block * 2 : 0;
		now = flagC ? now + block : now;
		// 추가로 탐색할 분할 구역 전까지의 방문 횟수와 분할 구역 내에서의 방문 횟수를 더함.
		return now + divideAndConquer(n-1, r, c);
	}

}
