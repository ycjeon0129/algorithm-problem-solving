package com.ssafy.day12_;
// gold 5. 적록색약
// bfs
 
import java.io.*;
import java.util.*;

public class Main_10026_전윤철 {

	static int N, group;
	static int[][] RGB, temp;
	static Queue<int[]> queue;
	static int[] di = { -1, 0, 1, 0 }, dj = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		RGB = new int[N][N];
		temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				parse(i, j, line.charAt(j));
			}
		}
		// 인지할 수 있는 수의 색이 3개인 경우 (R, G, B)
		grouping(3);
		// 인지할 수 있는 수의 색이 2개인 경우 (RG, B)
		grouping(2);

	}

	static void grouping(int num) {
		group = 0;
		for (int c = num; c > 0; c--) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (RGB[i][j] >= c) {
						// 해당 좌표의 색이 현재 인식할 색 순번보다 크거나 같다면 해당 영역을 체크하고 그룹 개수 증가
						turnOff(i, j, c);
						group++;
					}
				}
			}
		}
		System.out.print(group + " ");
		// 다음 탐색을 위해 배열 복원
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				RGB[i][j] = temp[i][j];
			}
		}
	}

	// bfs
	static void turnOff(int i, int j, int c) {
		int ni, nj;
		int[] cur = { i, j };
		queue = new ArrayDeque<int[]>();
		queue.offer(cur);
		RGB[i][j] = 0;
		while (queue.size() > 0) {
			cur = queue.poll();
			
			for (int k = 0; k < di.length; k++) {
				ni = cur[0] + di[k];
				nj = cur[1] + dj[k];
				if (movable(ni, nj) && RGB[ni][nj]>=c) {
					int[] tempCo = {ni, nj};
					RGB[ni][nj] = 0;
					queue.offer(tempCo);
				}
			}
		}
	}

	// 배열 인덱스 범위 유효성 검사
	static boolean movable(int i, int j) {
		if (i<0 || i>=N || j<0 || j>=N) {
			return false;
		}
		return true;
	}

	static void parse(int i, int j, char charAt) {
		int value = 0;
		switch (charAt) {
		case 'R':
			value = 3;
			break;
		case 'G':
			value = 2;
			break;
		case 'B':
			value = 1;
			break;
		}
		RGB[i][j] = value;
		temp[i][j] = value;
	}

}
