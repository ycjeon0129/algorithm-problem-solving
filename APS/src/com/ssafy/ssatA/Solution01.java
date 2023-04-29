package com.ssafy.ssatA;
// 사과 먹기

import java.io.*;
import java.util.*;

public class Solution01 {
	
	static int N, turnCnt;
	static int playerDirection;
	static int[] player;
	static int[][] matrix;
	static List<int[]> apples;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			bw.write(String.format("#%d ", t));
			turnCnt = 1;
			playerDirection = 0b0010;
			N = Integer.parseInt(br.readLine());
			matrix = new int[N][N];
			apples = new ArrayList<int[]>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
					if (matrix[i][j] > 1) {
						apples.add(new int[] {matrix[i][j], i, j});
					} else if (matrix[i][j] == 1) {
						player = new int[] {i, j};
					}
				}
			}
			Collections.sort(apples, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});
			play();
			bw.write(String.format("%d\n", turnCnt));
		}
		bw.flush();
		bw.close();
	}

	static void play() {
		for (int i = 0; i < apples.size(); i++) {
			int[] apple = apples.get(i);
			int getNextDir = getNextDir(apple);
			int nextPlayerDir = 0;
			switch (getNextDir) {
			case 0:
				turnCnt += 1;
				nextPlayerDir = 1;
				break;
			case 1:
				turnCnt += 2;
				nextPlayerDir = 2;
				break;
			case 2:
			case 3:
				turnCnt += 3;
				nextPlayerDir = 3;
				break;
			}
			playerDirection = turnRight(playerDirection, nextPlayerDir);
			player[0] = apple[1];
			player[1] = apple[2];
		}
		
	}

	static int getNextDir(int[] next) {
		int dirToApple = 0;
		if (next[1]>player[0] && next[2]>player[1]) {
			dirToApple = 1;
		} else if (next[1]>player[0] && next[2]<player[1]) {
			dirToApple = 2;
		} else if (next[1]<player[0] && next[2]<player[1]) {
			dirToApple = 3;
		}
		for (int i = 0; i < 4; i++) {
			if ( (playerDirection & (1 << (3-i)) ) != 0) {
				dirToApple = (4-i+dirToApple) % 4;
				break;
			}
		}
		return dirToApple;
	}
	static int turnRight(int dir, int times) {
		return ( (dir >> times) | ( ( ( (dir << 4) & 0b11110000) >> times ) & 0b1111 ) );
	}
}




