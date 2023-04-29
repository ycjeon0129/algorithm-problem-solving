package com.ssafy.day04;
// gold 5. 신기한 소수

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main_2023_전윤철 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static byte N;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextByte();
		// 한자리 소수 4개에 경우 각각 실행
		surprisingPrime(2, (byte) 1);
		surprisingPrime(3, (byte) 1);
		surprisingPrime(5, (byte) 1);
		surprisingPrime(7, (byte) 1);
		
		bw.flush();
		bw.close();
	}

	static void surprisingPrime(int n, byte digit) throws IOException {
		// 목표 자릿수 도달
		if (digit==N) {
			// 소수일 경우 결과 출력
			if (isPrime(n)) {
				bw.write(n+"\n");
			}
			// 소수가 아닐 경우 1의 자릿수를 바꿔가며 소수 판별 및 출력
			else {
				for (int i=1; i<10; i++) {
					if (isPrime(n+i)) {
						bw.write((n+i)+"\n");
					}
				}
			}
		} 
		// 소수일 경우 자릿수를 올려서 재귀 함수 호출
		else if (isPrime(n)) {
			surprisingPrime(n*10, (byte) (digit+1));
		}
		// 소수가 아닐 경우 1의 자릿수를 바꿔가며 소수 판별 및 재귀 함수 호출
		else {
			int i=1;
			while (i<10) {
				if (isPrime(n+i)) {
					surprisingPrime((n+i)*10, (byte) (digit+1));
				}
				i++;
			}
		}
	}
	
	// 소수 판별 함수
	static boolean isPrime(int n) throws IOException {
		if (n<2) {
			return false;
		}
		for (int i=2; i*i<=n; i++) {
			if (n%i==0) {
				return false;
			}
		}
		return true;
	}
}
