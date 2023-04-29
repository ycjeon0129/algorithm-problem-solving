package com.ssafy.day08;
// silver 5. 색종이

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2563_전윤철 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 색종이의 개수 num, 검은 영역의 넓이 result
		int num = Integer.parseInt(st.nextToken());
		int result = 0;
		
		// 전체 범위에 대하여 색종이 여부를 판단하는 배열 area
		boolean[][] area = new boolean[100][100];
		
		// num개의 색종이에 대하여 좌표 입력 후 해당 색종이의 범위만큼 배열 area에 true 저장
		for (int i=0; i<num; i++) {
			st = new StringTokenizer(br.readLine());
			int coX = Integer.parseInt(st.nextToken());
			int coY = Integer.parseInt(st.nextToken());
			for (int j=coX; j<coX+10; j++) {
				for (int k=coY; k<coY+10; k++) {
					area[j][k] = true;
				}
			}
		}
		
		// 배열 area에서 true 개수 result에 저장
		for (int i1=0; i1<100; i1++) {
			for (int j1=0; j1<100; j1++) {
				if (area[i1][j1] == true) {
					result++;
				}
			}
		}
		
		System.out.println(result);
		
	}

}