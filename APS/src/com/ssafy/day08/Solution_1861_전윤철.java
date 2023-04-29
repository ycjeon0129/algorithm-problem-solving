package com.ssafy.day08;
// D4. 정사각형 방
// dfs, 사방탐색

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_1861_전윤철 {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, start, len, curLen;
	static int[][] room;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			// 각 테케에 대해 시작 위치와 최대 길이는 0으로 초기화
			start = len = 0;
			room = new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 가장 긴 길이와 그때의 시작 위치 찾기
			findStartPoint();
			bw.write(String.format("#%d %d %d\n", t+1, start, len));
		}
		bw.flush();
		bw.close();
	}

	static void findStartPoint() {
		// 모든 항목에 대해 비교
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 최대값과 현재 위치값의 차이가 현재 최대 길이보다 작다면 탐색하지 않음
				if (room[i][j] > (N*N)-len) {
					continue;
				}
				// 현재 길이를 1로 설정하고 탐색
				curLen = 1;
				getLongest(i, j);
				// 탐색 결과 길이가 현재 최대 길이보다 길다면 업데이트
				if (curLen >= len) {
					// 길이가 최대 길이와 같더라도 시작 위치의 값이 작다면 업데이트
					if (start > room[i][j] || curLen > len) {
						start = room[i][j];						
					}
					len = curLen;
				}
			}
		}
		
	}
	
	// 사방탐색
	static void getLongest(int i, int j) {
		int[] di = {1, 0, -1, 0};
		int[] dj = {0, 1, 0, -1};
				
		for (int k=0; k<dj.length; k++) {
			int ni = i + di[k];
			int nj = j + dj[k];
			
			if (ni>=N || ni<0 || nj>=N || nj<0) {
				continue;
			}
			// 사방탐색 중 다음 좌표의 값이 현재 좌표의 값보다 1 크다면 길이를 1 증가시킨 후 다음 좌표로 이동하여 계속 탐색
			if (room[i][j]+1 == room[ni][nj]) {
				curLen++;
				getLongest(ni, nj);
			}
		}
		
	}

}
