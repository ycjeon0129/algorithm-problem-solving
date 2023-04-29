package com.ssafy.day02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_15649_전윤철 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int[] arr;
	static int[] output;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        output = new int[M];
        visited = new boolean[N];
        
        // arr 배열에 1부터 값 할당
        for (int i=0; i<N; i++) {
            arr[i] = i+1;
        }
        
        // nPm 실행
        permut(0, N, M);
        bw.flush();
        bw.close();
    }

    static void permut(int depth, int n, int r) throws IOException {
        // 깊이가 끝에 도달하면 결과 배열 출력
    	if (depth == r) {
            print(output, r);
            return;
        }
    	
        for (int i=0; i<n; i++) {
        	// 방문하지 않았을 경우
            if (visited[i] != true) {
            	// 이번 반복에서 더 이상 방문하지 않음
                visited[i] = true;
                // 출력 배열의 현재 위치에 값 저장
                output[depth] = arr[i];
                // 이후 경우의 수에 대해 재귀함수 호출
                permut(depth + 1, n, r);
                // 이번 반복을 마치며 방문 기록 초기화
                visited[i] = false;;
            }
        }
    }

    static void print(int[] arr, int r) throws IOException {
        for (int i = 0; i < r; i++) {
        	bw.write(arr[i]+" ");
        }
        bw.newLine();
    }
}
