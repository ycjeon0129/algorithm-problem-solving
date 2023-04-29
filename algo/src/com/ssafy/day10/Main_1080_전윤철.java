package com.ssafy.day10;
// silver 1. 행렬
// 수학

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main_1080_전윤철 {
	
	static int N, M, count = 0;
	static boolean[][] first, second, diff; 

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		first = new boolean[N][M];
		second = new boolean[N][M];
		// 행렬 A와 행렬 B가 같다면 true, 다르다면 false 값을 저장하는 배열 diff
		diff = new boolean[N][M];
		
		String line;
		
		for (int i = 0; i < N; i++) {
			line = br.readLine();
			for (int j = 0; j < M; j++) {
				first[i][j] = line.charAt(j)=='1' ? true : false;
			}
		}
		
		for (int i = 0; i < N; i++) {
			line = br.readLine();
			for (int j = 0; j < M; j++) {
				second[i][j] = line.charAt(j)=='1' ? true : false;
				diff[i][j] = first[i][j]==second[i][j] ? true : false;
			}
		}
		
		// diff 배열의 값이 false라면 3x3 범위 반전
		for (int i = 0; i <= N-3; i++) {
			for (int j = 0; j <= M-3; j++) {
				if (!diff[i][j]) {
					reverse(i, j);
				}
			}
		}
		// 모든 diff 값이 같은지 확인
		isSame();
		System.out.println(count);
	}
	
	// 모든 diff 값이 같은지 확인
	static void isSame() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!diff[i][j]) {
					count = -1;
					return;
				}
			}
		}	
	}

	// 주어진 좌표부터 가로세로 3칸씩 값을 반전
	static void reverse(int i, int j) {
		for (int i2 = i; i2 < i+3; i2++) {
			for (int j2 = j; j2 < j+3; j2++) {
				if (first[i2][j2]) {
					first[i2][j2] = false;
				} else {
					first[i2][j2] = true;
				}
			}
		}
		// diff 값을 갱신
		check(i, j);
		// 반전 횟수 증가
		count++;
	}

	// 주어진 좌표부터 가로세로 3칸씩 diff 값을 갱신
	static void check(int i, int j) {
		for (int i2 = i; i2 < i+3; i2++) {
			for (int j2 = j; j2 < j+3; j2++) {
				diff[i2][j2] = first[i2][j2]==second[i2][j2] ? true : false; 
			}
		}		
	}
}
