package com.ssafy.day02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1210_전윤철 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] result = new int[10];
	
	public static void main(String[] args) throws IOException {
//		st = new StringTokenizer(br.readLine());
		int T = 10;
		
		for (int t=0; t<T; t++) {
			br.readLine();
			int[][] map = new int[100][100];
			// 맵 입력
			for (int i=0; i<100; i++) {
				
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<100; j++) {				
					map[i][j] = Integer.parseInt(st.nextToken());
				}		
			}
			// 당첨 시작점 확인
			int startPoint = 0;
			for (int i=0; i<100; i++) {
				if (map[99][i]==2) {
					startPoint = i;
				}
			}
			// 정답에서 부터 역으로 탐색
			result[t] = findAnswer(map, startPoint, 99);
			
		}
		
		// 결과 출력
		for (int t=0; t<T; t++) {
			StringBuilder sb = new StringBuilder("#");
			sb.append( (t+1)+" " );
			sb.append(result[t]+"\n");
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();		
	}

	static int findAnswer(int[][] map, int x, int y) {
		// 현재 좌우 진행 방향 1: 우, 0: 없음, -1: 좌
		int curD = 0;
		// 사다리의 시작점에 도달할 때까지 반복
		while (y > 0) {
			// 다음 이동 칸 확인 및 현재 이동 방향 저장
			int moveD = isMove(map, x, y, curD);
			if (moveD > 0) {
				x++;
				curD = 1;
			} else if (moveD < 0) {
				x--;
				curD = -1;
			} else {
				y--;
				curD = 0;
			}
		}
		
		return x;
	}
	// 다음 이동 칸 확인
	static int isMove(int[][] map, int x, int y, int curD) {
		
		// 현 진행방향이 없는 경우 좌우 모두 탐색
		if (curD == 0) {
			if (x<99 && map[y][x+1]==1) {
				return 1;
			} else if (x>0 && map[y][x-1]==1) {
				return -1;
			}
		} 
		// 현 진행방향이 1(오른쪽)인 경우 오른쪽만 탐색
		else if (curD == 1) {
			if (x<99 && map[y][x+1]==1) {
				return 1;
			}
		} 
		// 현 진행방향이 -1(왼쪽)인 경우 왼쪽만 탐색
		else {
			if (x>0 && map[y][x-1]==1) {
				return -1;
			}
		}
		// 이동할 수 있는 좌우 칸이 없는 경우 0 리턴
		return 0;
	}

}