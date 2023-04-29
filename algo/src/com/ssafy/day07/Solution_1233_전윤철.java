package com.ssafy.day07;
// D4. 사칙연산 유효성 검사
//

import java.io.*;
import java.util.StringTokenizer;

public class Solution_1233_전윤철 {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, idx, output;
	static char alphabet;
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		
		for (int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			// 일단 맞았다고 가정
			output = 1;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				idx = Integer.parseInt(st.nextToken());
				alphabet = st.nextToken().charAt(0);
				// 연산자인데 자식 노드가 없다면 틀렸다
				if (isOp()) {
					if (!st.hasMoreTokens()) {
						output = 0;
					}
				}
				// 연산자가 아닌데 자식 노드가 있다면 틀렸다
				else {
					if (st.hasMoreTokens()) {
						output = 0;
					}
				}
			}
			bw.write(String.format("#%d %d\n", t, output));
		}
		bw.flush();
		bw.close();
	}

	// 연산자라면 true, 아니라면 false
	static boolean isOp() {
		boolean result = false;
		switch (alphabet) {
		case '+':
		case '-':
		case '*':
		case '/':
			result = true;
			break;
		default:
			break;
		}
		return result;
	}

}
