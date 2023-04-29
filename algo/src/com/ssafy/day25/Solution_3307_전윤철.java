package com.ssafy.day25;
// D3. 최장 증가 부분 수열
// 최장 증가 부분 수열

import java.io.*;
import java.util.*;

public class Solution_3307_전윤철 {
	
	static int N, len;
	static int[] arr, C;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(st.nextToken());
        
        for (int t = 1; t <= T; t++) {
        	
        	N = Integer.parseInt(br.readLine());
        	len = 0;
        	arr = new int[N];
        	C = new int[N+1];	// 길이 k의 증가 수열에 대해 길이 k 위치에 올 수 있는 가장 작은 값을 C[k]에 저장
        	st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int idx = 0;	// 기존 arr 배열의 해당 값을 C 배열에 저장할 인덱스 
			for (int i = 0; i < N; i++) {
				for (int j = len; j >= 0; j--) {	// 현재 최장 길이부터 역으로 탐색
					if (C[j] < arr[i]) {	// 현재 값이 C 배열의 현 위치보다는 크다면
						idx = j;
						break;
					}
				}
				if (C[idx+1] == 0) {	// 다음 값이 아직 존재하지 않는다면 -> 최장 길이 갱신
					len++;
				}
				C[idx+1] = arr[i];	// C 베열 갱신
			}
        	sb.append(String.format("#%d %d\n", t, len));
		}
        System.out.println(sb);
	}

}
