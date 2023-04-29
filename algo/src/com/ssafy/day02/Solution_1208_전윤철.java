package com.ssafy.day02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1208_전윤철 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] result = new int[10];
	
	public static void main(String[] args) throws IOException {
		int T = 10;
		int N;
		for (int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			// 현재 상자 상태
			byte[] box = new byte[100];
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<100; i++) {
				box[i] = Byte.parseByte(st.nextToken());
			}
			// 상자를 오름차순 정렬
			Arrays.sort(box);
			int gap = -1;
			// 덤프 횟수만큼 반복
			for (int i=0; i<N; i++) {
				// 덤프 1회 수행 후 재정렬
				box[0]++;
				box[99]--;
				Arrays.sort(box);
				// 상자가 정렬됐다면 중지
				gap = box[99]-box[0];
				if (gap <= 1) {
					break;
				}
			}
			
			result[t] = gap;
		}
		
		
		for (int t=0; t<T; t++) {
			bw.write("#");
			bw.write(t+1 + " ");
			bw.write(result[t]+"\n");
		}
		bw.flush();
		bw.close();
	}

}
