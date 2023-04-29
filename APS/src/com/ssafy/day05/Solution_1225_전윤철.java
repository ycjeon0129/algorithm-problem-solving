package com.ssafy.day05;
// 암호생성기
// 수학?
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1225_전윤철 {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	// 입력결과 배열 code, 출력 배열 pass
	static int[] code, pass;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		
		for (int t=1; t<=T; t++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			
			code = new int[8];
			
			for (int i=0; i<8; i++) {
				code[i] = Integer.parseInt(st.nextToken());
			}
			generatePW();
			bw.write("#"+t);
			for (int i=0; i<8; i++) {
				bw.write(" "+pass[i]);
			} bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	
	static void generatePW() {
		pass = code.clone();
		Arrays.sort(pass);
		int min = pass[0];
		int counter = min / 15;
		
		// 5싸이클이 돌 경우 배열 내 모든 수 15 감소
		for (int i=0; i<8; i++) {
			code[i] -= 15 * (counter-1);
		}
		int dec = 0;
		// 감소값을 1씩 증가시켜가며 배열 값 감소 및 값이 0 이하가 될 경우 해당 감소값(인덱스) 저장
		loop1: for (int i=0; i<10; i++) {
			for (int j=0; j<8; j++) {
				code[(i*8+j)%8] -= ( ( dec++ ) % 5 ) + 1;
				if (code[(i*8+j)%8]<=0) {
					code[(i*8+j)%8] = 0;
					break loop1;
				}
			}
		}
		// 생성된 pass 배열을 0이 가장 뒤에 가도록 조정
		for (int i=0; i<8; i++) {
			pass[i] = code[(i+dec)%8];
		}
	}

}
