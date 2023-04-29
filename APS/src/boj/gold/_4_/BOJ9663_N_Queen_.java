package boj.gold._4_;
// Brute-Force, Back-Tracking

import java.io.*;

public class BOJ9663_N_Queen_ {

	static int N, possiblePossibility;
	static boolean[][] board;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		board = new boolean[N][N];
		possiblePossibility = 0;

		play(0);
		bw.write(possiblePossibility+"");
		bw.flush();
		bw.close();
	}

	static void play(int depth) {	// 각 행에 대해 Queen을 배치하며 유효할 경우 재귀 호출
		for (int c = 0; c < N; c++) {	// 해당 행에서 각 열마다 Queen 배치
			board[depth][c] = true;
			if (!isValid(depth, c)) {	// 유효성 검사
				board[depth][c] = false;	// 유효하지 않다면 배치한 Queen을 제거 후 다음 경우의 수로 이동
				continue;
			}
			if (depth == N - 1) {	// 마지막 행까지 배치를 완료했다면 유효한 경우의 수 1 증가
				possiblePossibility++;
				board[depth][c] = false;
				return;
			}
			play(depth + 1);	// 다음 행으로 재귀
			board[depth][c] = false;
		}

	}

	static boolean isValid(int i, int j) {	// 현재 체스판의 상태 유효성 검사
		int ny = i-1, nx = j-1;
		while (isIn(ny, nx)) {	// 좌측 대각선
			if (board[ny][nx]) return false;
			ny--;
			nx--;
		}
		ny = i-1;
		nx = j+1;
		while (isIn(ny, nx)) {	// 우측 대각선
			if (board[ny][nx]) return false;
			ny--;
			nx++;
		}
		for (int r = i-1; r >= 0; r--) {	// 행 검사
			if (board[r][j]) return false;
		}
		return true;
	}

	static boolean isIn(int i, int j) {
		return !(i<0 || i>=N || j<0 || j>=N);
	}

}
