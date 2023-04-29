package com.ssafy.ssatA;
// 애드가 앨런 포

import java.io.*;
import java.util.*;

public class Solution02_ {
	
	static int N, catchedPawns;
	static int[][] board, delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static boolean[][] visited;
	static Queue<int[]>[] possiblePositions, catchablePawns;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			bw.write(String.format("#%d ", t));
			catchedPawns = 0;
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			possiblePositions = new Queue[3];
			catchablePawns = new Queue[3];
			for (int i = 0; i < 3; i++) {
				possiblePositions[i] = new ArrayDeque<int[]>();
				catchablePawns[i] = new ArrayDeque<int[]>();
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] == 2) {
						board[i][j] = 0;
						possiblePositions[0].offer(new int[] {i, j});
					}
				}
			}
			
			play();
					
			bw.write(String.format("%d\n", catchedPawns));
		}
		bw.flush();
		bw.close();
	}

	static void play() {
		for (int i = 0; i < possiblePositions.length; i++) {
			visited = new boolean[N][N];
			while (!possiblePositions[i].isEmpty()) {
				int[] curPow = possiblePositions[i].poll();
				for (int j = 0; j < delta.length; j++) {
					addPossibility(i, curPow, j);
				}
			}
			if (i > 0) {
				catchThemAll(i-1);									
			}
		}
		catchThemAll(2);
	}

	static void catchThemAll(int i) {
		while (!catchablePawns[i].isEmpty()) {
			int[] curPawn = catchablePawns[i].poll();
			if (board[curPawn[0]][curPawn[1]] == 1) {
//				if (curPawn[0]==0 && curPawn[0]==1) {
//					System.out.println();
//				}
				board[curPawn[0]][curPawn[1]] = 0;
				catchedPawns++;
//				System.out.printf("%d: (%d, %d) %d\n", i, curPawn[0], curPawn[1], catchedPawns);
			}
		}
	}

	static void addPossibility(int turn, int[] curPow, int j) {
		int ny = curPow[0];
		int nx = curPow[1];
//		if (ny==0 && nx==0 && j==2) {
//			System.out.println();
//		}
		while (isIn(ny, nx) && board[ny][nx]==0) {	// 뛰어넘을 말을 찾아 이동
			ny += delta[j][0];
			nx += delta[j][1];
		}
		if (isIn(ny, nx)) {	// 뛰어넘을 말을 만났다면 그 이후를 탐색
			ny += delta[j][0];
			nx += delta[j][1];
			while (isIn(ny, nx) && board[ny][nx]==0) {	// 잡을 말을 찾아 이동 및 중간 경로를 다음 경우의 수에 포함
				if (turn < 2 && !visited[ny][nx]) {
					possiblePositions[turn+1].offer(new int[] {ny, nx});
					visited[ny][nx] = true;
				}
				ny += delta[j][0];
				nx += delta[j][1];
			}
			if (isIn(ny, nx) && board[ny][nx]==1) {
				if (!visited[ny][nx]) {
					//
					if (turn < 2) {
						possiblePositions[turn+1].offer(new int[] {ny, nx});					
					}
					catchablePawns[turn].offer(new int[] {ny, nx});
					visited[ny][nx] = true;
				}
				
			}
		}
	}

	static boolean isIn(int i, int j) {
		return !(i<0 || i>=N || j<0 || j>=N);
	}

}
