package com.ssafy.day08;
// silver 1. 배열 돌리기 3
// 구현

import java.io.*;
import java.util.*;

public class Main_16935_전윤철 {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, r, newN, newM, op;
	static int[][] matrix, output;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];
		// 90도 회전을 위한 새로운 가로, 세로 값
		newN = N;
		newM = M;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		// 연산값을 입력받고 실행
		while (st.hasMoreTokens()) {
			op = Integer.parseInt(st.nextToken());

			switch (op) {
				case 1:
					vertical();
					break;
				case 2:
					horizon();
					break;
				case 3:
					turnRight();
					break;
				case 4:
					turnLeft();
					break;
				case 5:
					makeOutput();
					turnGroupRight();
					break;
				case 6:
					makeOutput();
					turnGroupRight();
					turnGroupRight();
					turnGroupRight();
			}
		}
		// 최종 결과물 출력
		print();
		bw.flush();
		bw.close();
	}

	// 그룹을 시계방향 회전
	static void turnGroupRight() throws IOException {
		for (int i = 0; i < newN/2; i++) {
			for (int j = 0; j < newM/2; j++) {
				output[i][j] = matrix[i+newN/2][j];
			}
		}
		for (int i = 0; i < newN/2; i++) {
			for (int j = newM/2; j < newM; j++) {
				output[i][j] = matrix[i][j-newM/2];
			}
		}
		for (int i = newN/2; i < newN; i++) {
			for (int j = newM/2; j < newM; j++) {
				output[i][j] = matrix[i-newN/2][j];
			}
		}
		for (int i = newN/2; i < newN; i++) {
			for (int j = 0; j < newM/2; j++) {
				output[i][j] = matrix[i][j+newM/2];
			}
		}
		save();
	}

	// N, M값을 스왑 후 회전
	static void turnLeft() throws IOException {
		swap();
		makeOutput();
		for (int i = 0; i < newN; i++) {
			for (int j = 0; j < newM; j++) {
				output[newN - i - 1][j] = matrix[j][i];
			}
		}
		save();
	}

	static void turnRight() throws IOException {
		swap();
		makeOutput();
		for (int i = 0; i < newN; i++) {
			for (int j = 0; j < newM; j++) {
				output[i][newM - j - 1] = matrix[j][i];
			}
		}
		save();
	}

	// 가로 반전
	static void horizon() throws IOException {
		makeOutput();
		for (int i = 0; i < newN; i++) {
			for (int j = 0; j < newM; j++) {
				output[i][newM - j - 1] = matrix[i][j];
			}
		}
		save();
	}

	static void vertical() throws IOException {
		makeOutput();
		for (int i = 0; i < newN; i++) {
			for (int j = 0; j < newM; j++) {
				output[newN - i - 1][j] = matrix[i][j];
			}
		}
		save();
	}

	// 다음 연산을 위해 연산 결과를 저장
	static void save() {
		matrix = new int[newN][newM];
		for (int i = 0; i < newN; i++) {
			for (int j = 0; j < newM; j++) {
				matrix[i][j] = output[i][j];
			}
		}
	}

	static void print() throws IOException {
		for (int i = 0; i < newN; i++) {
			for (int j = 0; j < newM; j++) {
				bw.write(matrix[i][j] + " ");
			}
			bw.newLine();
		}
	}

	static void swap() {
		int temp = newN;
		newN = newM;
		newM = temp;
	}

	static void makeOutput() {
		output = new int[newN][newM];
	}

}
