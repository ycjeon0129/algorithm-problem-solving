package com.ssafy.day02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_2805_전윤철 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] result;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		result = new int[T];
		for (int t=0; t<T; t++) {
			byte N = Byte.parseByte(br.readLine());
			byte[][] farm = new byte[N][N];
			// 농장의 중앙 위치
			byte pivot = (byte) (N/2);
			
			// 농장 입력
			for (int i=0; i<N; i++) {
				String line = br.readLine();
				for (int j=0; j<N; j++) {
					farm[i][j] = (byte) (line.charAt(j)-'0');
				}
			}
			
			// 실행
			for (int i=0; i<N; i++) {
				// 반복문의 시작 위치는 농장의 중앙위치에서 한칸씩 줄었다가 0이 되면 다시 중앙위치까지 증가
				int a = Math.abs(pivot-i);
				for (int j=a; j<N-a; j++) {
					// 반복문 덧셈
					result[t] += farm[i][j];
				}
					
			}	
		}
		
		// 결과 출력
		for (int t=0; t<T; t++) {
			bw.write("#");
			bw.write(t+1+" ");
			bw.write(result[t]+"\n");
		}
		bw.flush();
		bw.close();
	}

}
