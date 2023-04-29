package com.ssafy.day13_;
// D3. 상호의 배틀필드
// 그래프, 구현

import java.io.*;
import java.util.StringTokenizer;

public class Solution_1873_전윤철 {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int H, W, command;
	static String commands;
	// tank = { 행, 열, 방향 }
	static int[] tank;
	static int[][] battleField;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			battleField = new int[H][W];
			tank = new int[3];
			// 초기 배틀필드 입력 및 파싱
			for (int h = 0; h < H; h++) {
				String line = br.readLine();
				for (int w = 0; w < W; w++) {
					parsing(h, w, line.charAt(w));
				}
			}
			// 커맨드 입력
			command = Integer.parseInt(br.readLine());
			commands = br.readLine();
			bw.write("#" + t + " ");
			// 게임 시작!!
			play();
		}
		bw.flush();
		bw.close();
	}

	// 각 커맨드를 순차적으로 실행
	static void play() throws IOException {
		for (int i = 0; i < command; i++) {
			char cur = commands.charAt(i);
			if (cur == 'S') {
				shoot();
			} else {
				turnAndMove(cur);
			}
		}
		// 실행 후 배틀필드 출력
		print();
	}

	// 포탄 발사
	static void shoot() {
		// 현재 좌표에서 이동하지 않고 다음 진행방향의 상태 확인
		int signal = act(tank[0], tank[1], false);
		int i, j;
		// 범위를 벗어나지 않는다면 계속
		while (signal!=Integer.MIN_VALUE) {
			// 다음 위치가 벽이라면
			if (signal<0) {
				// 다음 좌표값 파싱 및 해당 좌표의 값 1 증가 후 종료
				// 벽돌벽(-1)이라면 포탄이 충돌한 후 평지(0~100)가 됨
				int co = ((signal+1)*-1);
				i = co / 100;
				j = co % 100;
				battleField[i][j]++;
				break;
			}
			// 다음 위치가 평지이거나 물이라면 해당 위치에서 계속 다음 진행방향의 상태 확인
			i = signal / 100;
			j = signal % 100;
			signal = act(i, j, false);
		}
	}

	// 회전 및 전진
	static void turnAndMove(char cur) {
		// 커맨드의 종류에 따라 이동 방향 설정
		int direction = -1;
		switch (cur) {
		case 'U':
			direction = 0;
			break;
		case 'D':
			direction = 1;
			break;
		case 'L':
			direction = 2;
			break;
		case 'R':
			direction = 3;
			break;
		}
		tank[2] = direction;
		// 탱크의 현재 위치에서 진행방향으로 1칸 전진
		act(tank[0], tank[1], true);
	}

	// 커맨드 동작 수행
	static int act(int i, int j, boolean move) {
		// 탱크의 위치 및 방향에 따라 다음 위치 설정
		int ni = i + (tank[2]>1 ? 0: (tank[2]==1 ? 1: -1));
		int nj = j + (tank[2]<2 ? 0: (tank[2]==2 ? -1: 1));
		// 배틀필드를 벗어난다면 정수 최솟값 리턴
		if (ni<0 || ni>=H || nj<0 || nj>=W) {
			return Integer.MIN_VALUE;
		}
		// 벽이라면(음수) 해당 좌표를 음수로 변환하여 리턴
		if (battleField[ni][nj] < 0) {
			return ((ni*100+nj) * (-1)) - 1;
		}
		// 물이라면 해당 좌표 리턴
		if (battleField[ni][nj] > 100) {
			return ni*100+nj;
		}
		// 이동을 포함한 커맨드라면 탱크 위치 이동
		if (move) {
			tank[0] = ni;
			tank[1] = nj;
		}
		// 평지라면 해당 좌표 리턴
		return ni*100+nj;
	}

	// 입력된 문자열 형태의 배틀필드를 정수로 변환
	static void parsing(int i, int j, char status) {
		int value = 0;
		switch (status) {
		case '*':
			value = -1;
			break;
		case '#':
			value = -200;
			break;
		case '-':
			value = 200;
			break;
		case '^':
			tank[0] = i;
			tank[1] = j;
			tank[2] = 0;
			break;
		case 'v':
			tank[0] = i;
			tank[1] = j;
			tank[2] = 1;
			break;
		case '<':
			tank[0] = i;
			tank[1] = j;
			tank[2] = 2;
			break;
		case '>':
			tank[0] = i;
			tank[1] = j;
			tank[2] = 3;
			break;
		}
		battleField[i][j] = value;
	}

	// 정수 형태의 게임 실행 결과 배틀필드를 문자열 형태로 복원하여 출력
	static void print() throws IOException {
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				// 탱크가 위치해 있다면 방향에 따라 해당 문자 출력
				if (h==tank[0] && w==tank[1]) {
					switch (tank[2]) {
						case 0:
							bw.write('^');
							break;
						case 1:
							bw.write('v');
							break;
						case 2:
							bw.write('<');
							break;
						case 3:
							bw.write('>');
							break;
					}
				}
				// 각 필드 상태에 맞는 문자 출력
				// -1			:	벽돌벽
				// -1 이하 음수	:	강철벽
				// 0			:	평지
				// 200			:	물
				else if (battleField[h][w]==-1) {
					bw.write('*');
				} else if (battleField[h][w]<-1) {
					bw.write('#');
				} else if (battleField[h][w]==200) {
					bw.write('-');
				} else {
					bw.write('.');
				}
			}
			bw.newLine();
		}
	}
}
