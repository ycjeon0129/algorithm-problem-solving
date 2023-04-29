package com.ssafy.day12_;
// silver 1. 쿼드트리
// DnC

import java.io.*;
import java.util.StringTokenizer;

public class Main_1992_전윤철 {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static boolean[][] BlackAndWhite;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		BlackAndWhite = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				if (line.charAt(j)=='1') {
					BlackAndWhite[i][j] = true;
				}
			}
		}
		
		// 시작 상태에서 탐색 시작
		quadTree(N, 0, 0);
		bw.flush();
		bw.close();
	}

	static void quadTree(int n, int i, int j) throws IOException {
		// 더 이상 분할할 수 없다면 현재 값 출력
		if (n==1) {
			print(i, j);
			return;
		}
		// 시작점 (i, j)에서부터 n x n 구역의 값이 모두 동일한지 검사
		boolean same = true;
		LCheck: for (int k = i; k < i+n; k++) {
			for (int l = j; l < j+n; l++) {
				if (BlackAndWhite[i][j]!=BlackAndWhite[k][l]) {
					same = false;
					break LCheck;
				}
			}
		}
		// 동일하다면 시작점 값 출력
		if (same) {
			print(i, j);
		} 
		// 동일하지 않다면 괄호를 사이로 현 상태를 사분할 탐색
		else {
			bw.write('(');
			quadTree(n/2, i, j);
			quadTree(n/2, i, j+n/2);
			quadTree(n/2, i+n/2, j);
			quadTree(n/2, i+n/2, j+n/2);
			bw.write(')');
		}
	}

	// 현재 값을 파싱하여 출력
	static void print(int i, int j) throws IOException {
		int temp = BlackAndWhite[i][j] ? 1 : 0;
		bw.write(temp+"");		
	}

}
