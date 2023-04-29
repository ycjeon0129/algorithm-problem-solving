package com.ssafy.day18_;
// gold 1. 낚시왕
// 구현, 시뮬레이션

import java.io.*;
import java.util.*;

public class Main_17143_전윤철 {

	static int R, C, M, sharkScore, numOfShark;
	static Shark[][] oceans;
	static Shark[] jaws;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		oceans = new Shark[R][C];	// 상어의 현 위치
		numOfShark = M;	// 남은 상어 수

		int r, c, s, d, z;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			oceans[r][c] = new Shark(r, c, s, d, z);	
		}
		fishing();	// 낚시 시작~
		System.out.println(sharkScore);
	}

	static void fishing() {	// 낚시왕이 이동하며 가장 가까운 상어를 잡으면 무게를 기록한 후 상어 이동 시작
		for (int c = 0; c < C; c++) {
			for (int r = 0; r < R; r++) {
				if (oceans[r][c] != null) {
					sharkScore += oceans[r][c].size;
					numOfShark--;
					oceans[r][c] = null;
					break;
				}
			}
			swim(c);
		}
	}

	static void swim(int fisherIdx) {	// 각 상어의 방향과 속도에 따라 다음 위치를 기억한 후 같은 위치에 있는 상어에 대해 가장 큰 상어만을 남김
		jaws = new Shark[numOfShark];	// 다음으로 이동할 상어 상태를 저장할 배열
		int idx = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				Shark shark = oceans[r][c];
				if (shark != null) {
					int[] nextCo = getNextCo(r, c, shark.dir, shark.speed);	// 상어의 다음 위치 및 방향 계산
					if (nextCo[2] == 1) {	// 방향이 바뀔 경우 처리
						switch (shark.dir) {
						case 1:
							shark.dir = 2;
							break;
						case 2:
							shark.dir = 1;
							break;
						case 3:
							shark.dir = 4;
							break;
						case 4:
							shark.dir = 3;
							break;
						}
					}
					shark.r = nextCo[0];
					shark.c = nextCo[1];
					jaws[idx++] = new Shark(nextCo[0], nextCo[1], shark.speed, shark.dir, shark.size);	// 상어의 상태를 다음 위치에 기록 
					oceans[r][c] = null;	// 다음 위치를 계산한 상어의 경우 배열에서 삭제
				}
			}
		}
		carnival();	// 한 칸에 하나의 가장 무거운 상어만을 남김
	}

	static void carnival() {	// 다음 위치를 기록해둔 상어를 낚시터의 알맞는 자리에 배치
		for (int i = 0; i < jaws.length; i++) {
			Shark shark = jaws[i];
			if (oceans[shark.r][shark.c]==null) {	// 해당 칸의 유일한 상어
				oceans[shark.r][shark.c] = shark;
			} else {	// 이미 해당 칸에 상어가 있을 경우 무거운 상어만을 남김
				if (shark.size > oceans[shark.r][shark.c].size) {
					oceans[shark.r][shark.c] = shark;
				}
				numOfShark--;
			}
		}		
	}

	static int[] getNextCo(int row, int col, int dir, int speed) {	// 다음 이동 좌료를 구하는 매직
		int r = row, c = col, turnCtn = 0, leftMoves = 0, s;
		if (dir == 1 || dir == 2) {
			s = speed % ((R - 1) * 2);
		} else {
			s = speed % ((C - 1) * 2);
		}
		if (dir == 1) {	// 이동방향이 상, 좌 일 경우 하, 우로 방향 및 속도 변경
			s = (2 * (R - 1) - s);
		} else if (dir == 4) {
			s = (2 * (C - 1) - s);
		}

		switch (dir) {
		case 1:
		case 2:
			turnCtn = (r + s) / (R - 1);	// 방향 전환 횟수
			leftMoves = (r + s) % (R - 1);	// 방향 전환 후 이동 칸 수
			if (turnCtn == 0) {
				r += s;
			} else if (turnCtn == 1) {
				r = R - leftMoves - 1;
			} else if (turnCtn == 2){
				r = leftMoves;
			}
			break;
		case 3:
		case 4:
			turnCtn = (c + s) / (C - 1);
			leftMoves = (c + s) % (C - 1);
			if (turnCtn == 0) {
				c += s;
			} else if (turnCtn == 1) {
				c = C - leftMoves - 1;
			} else if (turnCtn == 2) {
				c = leftMoves;
			}
			break;
		}
		return new int[] { r, c, turnCtn % 2 };
	}

	static class Shark {	// 상어 클래스
		int r;
		int c;
		int speed;
		int dir;
		int size;
		int moveCnt;

		Shark(int r, int c, int speed, int dir, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
			moveCnt = 0;
		}
	}
}
