package com.ssafy.ssatA;
// 프로세서 연결하기
// 중복순열, 구현

import java.io.*;
import java.util.StringTokenizer;

public class Solution_1767_전윤철 {

	static int N, maxConnectedCore, shortestLine;
	static int[] coreDirList;
	static int[][] semiConductor, delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static Processor processor;	// core를 담은 ArrayList

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			bw.write(String.format("#%d ", t));
			shortestLine = Integer.MAX_VALUE;
			maxConnectedCore = 0;
			N = Integer.parseInt(br.readLine());
			semiConductor = new int[N][N];
			processor = new Processor();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					semiConductor[i][j] = Integer.parseInt(st.nextToken());
					if (semiConductor[i][j] == 1) {
						processor.add(j, i);		// core 저장
					}
				}
			}
			coreDirList = new int[processor.num];	// 각 코어의 전선 방향을 저장하는 배열
			permutR(0);	// 각 코어의 전선 방향을 정하는 중복순열

			bw.write(String.format("%d\n", shortestLine));
		}
		bw.flush();
		bw.close();
	}

	static void permutR(int depth) {
		if (depth == processor.num ) {
			connect();	// 각 방향으로 코어의 전선 연결
			reset();	// 다음 경우의 수를 위해전선 제거
			return;
		}
		for (int i = 0; i < delta.length; i++) {	// 네 방향에 대한 중복순열
			coreDirList[depth] = i;
			permutR(depth+1);
		}
	}

	static void reset() {	// 값이 2인(전선) 위치를 0으로 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (semiConductor[i][j] == 2) {
					semiConductor[i][j] = 0;
				}
			}
		}
	}

	static void connect() {
		int connectionCnt = 0, lineLength = 0;
		int x, y;
		for (int i = 0; i < processor.num; i++) {	// 각각의 코어에 대해 전선 연결
			if (processor.num-i < maxConnectedCore-connectionCnt) return;	// 연결을 시도하지 않은 프로세서의 수가 최대 코어 갱신을 위한 필요 코어 수보다 적다면 
			x = processor.cores[i].x;
			y = processor.cores[i].y;
			int tmpLineLength = 0;
			int nx = x + delta[coreDirList[i]][0];
			int ny = y + delta[coreDirList[i]][1];
			while (isIn(nx, ny) && semiConductor[ny][nx]==0) {	// 전선 설치 가능한 경우
				semiConductor[ny][nx] = 2;	// 전선 설치
				tmpLineLength++;	// 현재 전선 길이 기록
				nx += delta[coreDirList[i]][0];	// 다음 위치로 좌표 이동
				ny += delta[coreDirList[i]][1];
			}
			if (!isIn(nx, ny)) {	// 이미 접지되어 전선을 설치하지 못하는 경우
				connectionCnt++;	// 연결된 코어 개수 기록
				lineLength += tmpLineLength;	// 해당 코어까지의 전선 길이 기록
			} else {	// 전선이 교차되어 전선을 설치하지 못하는 경우 해당 코어에서 나온 전선 제거
				int dir[] = delta[(coreDirList[i]+2)%4];
				nx += dir[0];
				ny += dir[1];
				while (semiConductor[ny][nx] != 1) {	// 시작한 코어까지 되돌아가며 전선 제거 및 길이 감소
					semiConductor[ny][nx] = 0;
					tmpLineLength--;
					nx += dir[0];
					ny += dir[1];
				}
			}
		}
		if (connectionCnt >= maxConnectedCore) {	// 모든 코어를 연결한 후 최대 코어일 경우
			if (connectionCnt > maxConnectedCore) {	// 이전보다 많이 연결했다면
				shortestLine = Integer.MAX_VALUE;	// 무조건 전선 길이 저장
			}
			maxConnectedCore = connectionCnt;
			if (shortestLine > lineLength) {	// 최소 길이라면 저장
				shortestLine = lineLength;
			}
		}
	}

	static boolean isIn(int x, int y) {
		return !(x<0 || x>=N || y<0 || y>=N);
	}	// 범위 유효성 검사

	static class Processor {
		int num;
		Core[] cores;

		public Processor() {
			num = 0;
			cores = new Core[12];
		}

		void add(int x, int y) {
			if (!(x==0 || y==0 || x==N-1 || y==N-1)) {	// core가 이미 접지되었을 경우 저장하지 않음
				cores[num++] = new Core(x, y);
			}
		}
	}

	static class Core {	// core의 좌표를 담는 클래스
		int x;
		int y;
		public Core(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
