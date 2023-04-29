package com.ssafy.day04;
// D2. 파리 퇴치

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 01. 차원 슬라이딩 윈도우를 통한 구
public class Solution_2001_전윤철 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, kda;
	static int[][] flies;
	static int[] rows;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			kda = -1;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			// 파리 위치 정보를 담는 2차원 배열
			flies = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					flies[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 구역 최댓값 계산 함수
			countKDA();
			bw.write("#" + (t + 1) + " " + kda + "\n");
		}
		bw.flush();
		bw.close();
	}

	static void countKDA() {
		// 구역이 이동 가능한 행 별로 최댓값 계산 함수 실행
		for (int i = 0; i < N - M + 1; i++) {
			countColumn(i);
		}
	}

	// 슬라이딩 윈도우
	static void countColumn(int row) {

		int sum = 0;
		int temp = 0;
		for (int i = 1+row; i <= M+row; i++) {
			for (int j = 1; j <= M; j++) {
				sum += flies[i][j];
			}
		}
		temp = sum;
		for (int j = M + 1; j <= N; j++) {
			for (int i = 1+row; i <= M+row; i++) {
				 temp = temp + flies[i][j] - flies[i][j-M];
			}
			sum = temp > sum ? temp : sum;
		}
		kda = Math.max(sum, kda);
	}

}

//// 02. 4중 for문을 통한 구현
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.StringTokenizer;
//
//
//public class Solution {
//	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//	static int N, M, kda;
//	static int[][] flies;
//
//	public static void main(String[] args) throws NumberFormatException, IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//
//		int T = Integer.parseInt(br.readLine());
//
//		for (int t=0; t<T; t++) {
//			kda = -1;
//			st = new StringTokenizer(br.readLine());
//			N = Integer.parseInt(st.nextToken());
//			M = Integer.parseInt(st.nextToken());
//			flies = new int[N+1][N+1];
//			for (int i=1; i<=N; i++) {
//				st = new StringTokenizer(br.readLine());
//				for (int j=1; j<=N; j++) {
//					flies[i][j] = Integer.parseInt(st.nextToken());
//				}
//			}
//			countKDA();
//			bw.write("#"+(t+1)+" "+kda+"\n");
//		}
//		bw.flush();
//		bw.close();
//	}
//	// M x M 구역이 위치할 수 있는 모든 구역에서 구역의 합 계산
//	static void countKDA() {
//		for (int i=1; i<=N-M+1; i++) {
//			for (int j=1; j<=N-M+1; j++) {
//				int count = 0;
//				for (int k=i; k<i+M; k++) {
//					for (int l=j; l<j+M; l++) {
//						count += flies[k][l];
//					}
//				}
//				kda = Math.max(count, kda);
//			}
//		}
//	}
//
//}
