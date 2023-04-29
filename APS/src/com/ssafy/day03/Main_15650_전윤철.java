package com.ssafy.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_15650_전윤철 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    // N과 M 배열
    static int[] arr;
    static int[] output;

    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        output = new int[M];

        for (int i=0; i<N; i++) {
            arr[i] = i+1;
        }

        combi(0, 0, N, M);
        bw.flush();
        bw.close();
    }
    
    // 시작점, 깊이, N, M을 파라미터로 갖는 메서드
    private static void combi(int start, int depth, int N, int M) throws IOException {
    	// 최대 깊이 도달 시 출력 후 종료
        if (depth == M) {
            print(output, M);
            return ;
        }
        // 시작점부터 반복문 시작
        for (int i=start; i<N; i++) {
        	// 현재 깊이 위치에 출력값 저장
            output[depth] = arr[i];
            // 다음 위치, 다음 깊이에서 재귀함수 호출
            combi(i+1, depth+1, N, M);
        }

    }
    
    // 출력~~
    static void print(int[] arr, int M) throws IOException {
        for (int i = 0; i < M; i++) {
        	bw.write(arr[i]+" ");
        }
        bw.newLine();
    }
}

