package com.ssafy.day03;
//silver 1. 구간 합 구하기 5

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_11660_전윤철 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    // 2차원 배열 맵과 누적 합
	static short[][] map;
	static int[][] accum;
	static short N;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Short.parseShort(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new short[N+1][N+1];
		accum = new int[N+1][N+1];
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				map[i][j] = Short.parseShort(st.nextToken());
				// 2차원 배열의 첫 행, 첫 열은 사용하지 않음 -> j값이 좌측 끝일 경우 이전 칸 우측 끝으로 이동
				if (j==1) {
					accum[i][j] = map[i][j] + accum[i-1][N];
				} else {
					accum[i][j] = map[i][j] + accum[i][j-1];
				}
			}
		}
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			short x1 = Short.parseShort(st.nextToken());
			short y1 = Short.parseShort(st.nextToken());
			short x2 = Short.parseShort(st.nextToken());
			short y2 = Short.parseShort(st.nextToken());
			int sum = addSum(x1, y1, x2, y2);
			bw.write(sum+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	// 행의 누적 합 계산
	static int addSum(short x1, short y1, short x2, short y2) {

		int sum = 0;
		for (short x=x1; x<=x2; x++) {
			sum += addline(x, y1, y2);
		}
		
		return sum;
	}
	
	// 각 행의 열 값 누적 합 계산
	static int addline(short x, short y1, short y2) {
		short newX = x;
		short newY = (short) (y1-1);
		if (y1==1) {
			newX--;
			newY = N;
		}
		int sum = accum[x][y2] - accum[newX][newY];
		
		return sum;
	}
	
	
	
	
	
	
	
	

}
