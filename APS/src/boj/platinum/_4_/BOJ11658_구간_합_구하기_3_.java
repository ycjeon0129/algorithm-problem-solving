package boj.platinum._4_;
//세그먼트 트리, 펜윅 트리 (바이너리 인덱스 트리)

//https://www.acmicpc.net/blog/view/21

import java.io.*;
import java.util.*;

public class BOJ11658_구간_합_구하기_3_ {

	static int N, M;
	static long[][] matrix, stMatrix;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new long[N + 1][N + 1];
		stMatrix = new long[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				matrix[i][j] = Long.parseLong(st.nextToken());
			}
		}
		init();
		int op, x, y, x1, y1, x2, y2;
		long c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			op = Integer.parseInt(st.nextToken());
			if (op == 0) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				c = Long.parseLong(st.nextToken());
				update(x, y, c);
			} else {
				x1 = Integer.parseInt(st.nextToken());
				y1 = Integer.parseInt(st.nextToken());
				x2 = Integer.parseInt(st.nextToken());
				y2 = Integer.parseInt(st.nextToken());
				bw.write(getSum(x1, y1, x2, y2) + "\n");
			}
		}
		bw.flush();
		bw.close();
	}

	private static void init() {	// 2차원 stack matrix 초기화 - 팬윅 트리의 각 행에 대해 이동하며 행 내 열에 팬윅 트리 적용
		int x, y;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				x = i;
				while (x <= N) {					
					y = j;
					while (y <= N) {
						stMatrix[x][y] += matrix[i][j];
						y += (y & -y);
					}
					x += (x & -x);
				}
			}
		}

	}

	private static void update(int x, int y, long c) {	// 업데이트
		long diff = c - matrix[x][y];
		matrix[x][y] = c;
		while (x <= N) {
			int ny = y;
			while (ny <= N) {
				stMatrix[x][ny] += diff;
				ny += (ny & -ny);
			}
			x += (x & -x);
		}
	}

	private static long getSum(int x1, int y1, int x2, int y2) {	// 구간 합 계산
		long large = 0l, left = 0l, top = 0l, small = 0l;
		int x, y;
		
		x = x2;
		while (x > 0) {
			y = y2;
			while (y > 0) {
				large += stMatrix[x][y];				
				y -= (y & -y);
			}			
			x -= (x & -x);
		}
		
		x = x2;
		while (x > 0) {
			y = y1-1;
			while (y > 0) {
				left += stMatrix[x][y];				
				y -= (y & -y);
			}			
			x -= (x & -x);
		}
		
		x = x1-1;
		while (x > 0) {
			y = y2;
			while (y > 0) {
				top += stMatrix[x][y];				
				y -= (y & -y);
			}			
			x -= (x & -x);
		}
		
		x = x1 - 1;
		while (x > 0) {
			y = y1 - 1;
			while (y > 0) {
				small += stMatrix[x][y];				
				y -= (y & -y);
			}			
			x -= (x & -x);
		}
		return large - left - top + small;
	}

}
