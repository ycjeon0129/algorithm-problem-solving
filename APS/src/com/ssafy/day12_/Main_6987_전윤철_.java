package com.ssafy.day12_;
// gold 5. 월드컵
// back-tracking, dfs

import java.io.*;
import java.util.StringTokenizer;

public class Main_6987_전윤철_ {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//	static int winTotal, drawTotal, loseTotal, drawCountry;
	static final int cases = 4, result = 3, country = 6;
	static int[] output;
	static int[][] board, simulations;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < cases; i++) {
			st = new StringTokenizer(br.readLine());
			int able = 1;
			board = new int[country][result];
			simulations = new int[country][country];
			for (int j = 0; j < country; j++) {
				simulations[i][i] = -1;
			}
//			winTotal = drawTotal = loseTotal = drawCountry =0;
			for (int j = 0; j < country; j++) {
				for (int k = 0; k < result; k++) {
					board[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			if (!possible()) {
				able = 0;
			}

			bw.write(able + " ");

		}
		bw.flush();
		bw.close();
	}

	static boolean possible() {
//		if (!formatCheck()) {
//			return false;
//		}
		for (int i = 0; i < country; i++) {
			for (int j = 0; j < result; j++) {
				int emptyResult = 0, target = board[i][j];
				int[] input = new int[country];
				for (int k = 0; k < country; k++) {
					if (simulations[i][k]==0) {
						input[emptyResult++] = k;
					}
				}
				output = new int[emptyResult];
				//////////////////////////////////////////////
//				combi();
				//////////////////////////////////////////////
			}
			
		}
		return true;
	}

//	static boolean formatCheck() {
//		winTotal += win;
//		if (draw != 0) {
//			drawCountry++;
//		}
//		drawTotal += draw;
//		loseTotal += lose;

//		if (win + draw + lose != 5) {
//			result = 0;
//			break;
//		}

//	if (winTotal != loseTotal || drawTotal % 2 != 0 || winTotal + drawTotal + loseTotal != 30 || drawCountry%2 != 0) {
//		result = 0;
//	}
//		return false;
//	}

}
