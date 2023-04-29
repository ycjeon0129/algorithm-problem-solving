package com.ssafy.day06;
// 암호문1

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1228_전윤철 {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	// 수정된 암호문을 담을 정수 리스트
	static List<Integer> pass;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		
		for (int t=1; t<=T; t++) {
			// 10번째 이상은 암호문에 사용하지 않음 -> 첫 암호문을 10까지만 입력받음 
			N = Integer.parseInt(br.readLine());
			pass = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<10; i++) {
				pass.add(Integer.parseInt(st.nextToken()));
			}
			M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				String trash = st.nextToken();
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				// 암호항의 수만큼 반복
				for (int j=0; j<y; j++) {
					int temp = Integer.parseInt(st.nextToken());
					// 수정할 인덱스가 10 이하인 경우 수정 내역 반영
					if (x+j < 10) {
						pass.add(x+j, temp);						
					}
					
				}
			}
			bw.write(String.format("#%d ", t));
			for (int i=0; i<10; i++) {
				bw.write(String.format("%d ", pass.get(i)));
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

}
