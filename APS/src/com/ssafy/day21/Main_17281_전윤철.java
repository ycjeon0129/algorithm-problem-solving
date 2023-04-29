package com.ssafy.day21;
// gold 4. ⚾
// 순열, 시뮬레이션

import java.io.*;
import java.util.*;

public class Main_17281_전윤철 {

	static int N, bestGameScore;
	static final int BASEBALL = 9;
	static int[] orderOfHitter;
	static int[][] scoreOfHitter;
	static boolean[] selected;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		scoreOfHitter = new int[N][BASEBALL];
		orderOfHitter = new int[BASEBALL];
		selected = new boolean[BASEBALL];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < BASEBALL; j++) {
				scoreOfHitter[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		orderOfHitter[3] = (1 - 1);	// 4번 타자에 1번 선수 고정
		selected[0] = true;	// 1번 선수는 이미 선택됨
		setOrderOfHitter(0);
		System.out.println(bestGameScore);
	}

	static void setOrderOfHitter(int depth) {
		if (depth == BASEBALL) {
			play();
			return;
		}
		if (depth == 3) {	// 4번 타자 자리는 스킵
			setOrderOfHitter(depth + 1);
		} else {
			for (int i = 0; i < BASEBALL; i++) {
				if (!selected[i]) {
					orderOfHitter[depth] = i;
					selected[i] = true;
					setOrderOfHitter(depth + 1);
					selected[i] = false;
				}
			}			
		}
	}

	static void play() {	// WBC 14년만에 우승 도전!!
		int outCount = 0, gameScore = 0, hitterIdx = 0;
		boolean[] runner;
		for (int i = 0; i < N; i++) {
			runner = new boolean[3];
			while (outCount < 3) {	// 아웃 카운트가 3일 경우 이닝 종료
				hitterIdx %= 9;	// 타자의 순서
				int hit = scoreOfHitter[i][orderOfHitter[hitterIdx++]];	// 해당 순번 타자가 이번 이닝에서 칠 결과
				if (hit == 0) {
					outCount++;
				} else if (hit == 4) {
					for (int j = runner.length - 1; j >= 0; j--) {	// 홈런일 경우 모든 주자는 홈으로 들어오고 득점
						if (runner[j] == true) {
							gameScore++;
							runner[j] = false;
						}
					}
					gameScore++;
				} else {
					for (int j = runner.length - 1; j >= 0; j--) {	// 타수만큼 진루 후 홈으로 돌아오는 선수는 득점
						if (runner[j] && (j + hit) >= runner.length) {	// 그렇지 않은 선수는 해당 베이스로 이동
							gameScore++;
							runner[j] = false;
						} else if (runner[j]) {
							runner[j + hit] = true;
							runner[j] = false;
						}
					}
					runner[hit - 1] = true;
				}
			}
			outCount = 0;
		}
		bestGameScore = Math.max(bestGameScore, gameScore);	// 최대 점수 비교
	}
}
