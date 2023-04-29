package com.ssafy.day19;
// 벌꿀채취
// brute-force, 그래프 탐색

import java.io.*;
import java.util.*;

public class Solution_2115_전윤철 {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, C, totalHoney;
	static int[][] honeycomb;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    int T = Integer.parseInt(st.nextToken());
	    
	    for (int t = 1; t <= T; t++) {
			bw.write(String.format("#%d ", t));
			totalHoney = 0;
	    	
	    	st = new StringTokenizer(br.readLine());
	    	N = Integer.parseInt(st.nextToken());
	    	M = Integer.parseInt(st.nextToken());
	    	C = Integer.parseInt(st.nextToken());
	    	honeycomb = new int[N][N];
	    	
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					honeycomb[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			collect();	// 벌꿀 채취 시작

			bw.write(String.format("%d\n", totalHoney));
		}
	    bw.flush();
	    bw.close();
	}

	static void collect() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - M + 1; j++) {	// 모든 구역을 탐색하며 현 구역과 현 구역을 제외한 구역 최댓값의 합 갱신
				totalHoney = Math.max(totalHoney, getSum(i, j) + findPartner(i, j));
			}
		}
	}

	static int findPartner(int i, int j) {	// 현 구역을 제외한 이후 구역의 최댓값 계산
		int partner = 0;
		for (int k = i; k < N; k++) {
			for (int l = 0; l < N - M + 1; l++) {
				if (k==i && l<j+M) {	// 현 구역과 같은 행이라면 현 구역 이후부터 탐색
					l = j + M;
				}
				if (l+M > N) continue;	// 해당 행에서 구역을 더이상 만들 수 없다면 다음 행으로 이동
				partner = Math.max(partner, getSum(k, l));	// 파트너의 최댓값 갱신
			}
		}
		return partner;
	}

	static int getSum(int i, int j) {	// 해당 구역의 최댓값 계산
		int powSum = 0, idx = (int) Math.pow(2, M);
		int[] candidates = new int[M];	// 해당 구역 설정
		for (int m = 0; m < M; m++) {
			candidates[m] = honeycomb[i][j+m];
		}
		for (int n = 0; n < idx; n++) {	// 모든 부분집합 확인
			int tempSum = 0, tempPowSum = 0;
			for (int k = 0; k < M; k++) {
				if ( (n & (1 << k) ) > 0 ) {	// 비트마스크. 1이면 부분집합에 포함, 0이면 제외
					if (tempSum + candidates[k] > C) {	// 용량이 가득찼다면 중지
						break;
					}
					tempSum += candidates[k];	// 현재까지 채취한 꿀의 용량 계산
					tempPowSum += candidates[k] * candidates[k];	// 꿀의 가치 갱신
				}
			}
			powSum = Math.max(powSum, tempPowSum);	// 모든 부분집합에 대해 최대 꿀 가치 갱신
		}
		return powSum;
	}
}
