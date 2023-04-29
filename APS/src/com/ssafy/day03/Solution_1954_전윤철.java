package com.ssafy.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution_1954_전윤철 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] snail;
    static int N;
    static int Direction = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			snail = new int[N+1][N+1];
			
			// 영차 영차 집을 짓자
			makeSnailHouse();
			
			bw.write("#"+t+"\n");
			print();
			
		}
		
		bw.flush();
		bw.close();

	}

	// 위치 별 넣을 값 value, 시작 좌표 (x, y), 시작점의 경우 방문 여부를 참으로 설정 후 반복문 실행
	static void makeSnailHouse() {
		int value = 1;
		int x = 1;
		int y = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 현재 위치에 value 삽입
				snail[x][y] = value++;
				// 이동해야 할 좌표 구하기
				Co cur = getNext(x, y);
				// 끝에 도달했을 경우 종료
				if (cur == null) {
					return;
				}
				// 다음 좌표 설정
				x = cur.x;
				y = cur.y;
			}
		}
		
	}
	
	// 다음 좌표 구하기
	static Co getNext(int x, int y) {
		// 우 하 좌 상 순으로 이동
		int[] dx = {0, +1, 0, -1};
		int[] dy = {+1, 0, -1, 0};
		
		for (int i=0; i<4; i++) {
			// static Direction 변수를 통해 현재 이동 방향 기록
			int nx = x + dx[(i+Direction)%4];
			int ny = y + dy[(i+Direction)%4];
			if (nx>0 && nx<=N && ny>0 && ny<=N && snail[nx][ny]==0) {
				// 이동 방향이 바뀔 경우 Direction값 변경
				Direction += i;
				return new Co(nx, ny);
			}
		}
		
		return null;
	}
	
	// 출력
	static void print() throws IOException {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				bw.write(snail[i][j]+" ");
			}
			bw.newLine();
		}
		
	}
	
	// 좌표 클래스
	static class Co {
		int x; int y;
		Co(int x, int y) {
			this.x = x; this.y = y;
		}
	}
}
