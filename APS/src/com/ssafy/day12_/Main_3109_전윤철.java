package com.ssafy.day12_;
// gold 2. 빵집
// back-tracking, dfs

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_3109_전윤철 {

	static int R, C, checker, pipes;
	static int[] delta = { -1, 0, 1 };
	static boolean[][] bakery;
	static Stack<int[]> co;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		bakery = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if (line.charAt(j) == 'x') {
					bakery[i][j] = true;
				}
			}
		}
		// 각 행에 대하여 dfs 탐색 
		for (int i = 0; i < R; i++) {
			steal(i);
		}
		
		System.out.println(pipes);
	}

	static void steal(int startRow) {
		int nr, nc;
		// 시작점을 스택에 저장
		int[] cur = {startRow, 0};
		co = new Stack<>();
		co.push(cur);
		// 더 이상 탐색 가능한 좌표가 없을 때까지
		while (co.size() > 0) {
			cur = co.pop();
			// 현재 좌표 방문처리
			bakery[cur[0]][cur[1]] = true;
			// 현 좌표가 빵집이라면 파이프 수 증가 후 종료
			if (cur[1]==C-1) {
				pipes++;
				break;
			}
			// 시작점의 반대 방향(아래)의 이동 가능 좌표부터 스택에 저장 => LIFO
			for (int i = delta.length-1; i >= 0 ; i--) {
				nr = cur[0] + delta[i];
				nc = cur[1] + 1;
				if (!movable(nr, nc) || bakery[nr][nc]) {
					continue;
				}
				int[] temp = {nr, nc};
				co.push(temp);
			}
		}
	}
	
	// 좌표의 유효 여부 검사
	static boolean movable(int nr, int nc) {
		if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
			return false;
		}
		return true;
	}
}
