package com.ssafy.day06;
// gold 5. 탑
// 스택

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2493_전윤철 {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	// 탑의 갯수, 탑의 순서, 송신한 레이저가 아직 수신되지 않은 탑을 담은 스택의 인덱스
	static int N, idx, idx2=1;
	static int[] result;
	// 탑의 배열, 송신한 레이저가 아직 수신되지 않은 탑을 담은 스택
	static Tower[] towers, towers2;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		towers = new Tower[N+1];;
		towers2 = new Tower[N+1];
		result = new int[N+1];
		idx = N;
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			towers[i] = new Tower(i, Integer.parseInt(st.nextToken()));
		}
		fire();
		for (int i=1; i<=N; i++) {
			bw.write(result[i] + " ");
		}
		bw.flush();
		bw.close();
	}

	static void fire() {
		// 첫 탑에서 레이저 송신 및 해당 탑 스택에 저장
		towers2[idx2] = towers[idx--];
		
		// 마지막 탑까지
		while (idx>0) {
			// 스택이 비었다면 생략
			if (idx2>0) {
				// 최근 레이저 송신 탑의 높이가 스택에 담긴 탑의 높이보다 크다면 반복
				while (towers2[idx2].height < towers[idx].height) {
					// 스택에 담긴 탑의 레이저는 최근 송신 탑에서 수신됨
					result[towers2[idx2].idx] = towers[idx].idx;
					// 송신이 확인된 탑은 스택에서 제거
					towers2[idx2--] = null;
					// 스택이 비었다면 break;
					if (idx2==0) {
						break;
					}
				}
			}
			// 확인하지 않은 마지막 탑에서 레이저 송신 및 해당 탑 스택에 저장
			towers2[++idx2] = towers[idx--];
		}
		while (idx2>0) {
			result[towers2[idx2--].idx] = 0;
		}		
	}
	
	// 탑의 순서와 높이를 담은 클래스
	static class Tower {
		int idx=0;
		int height;
		public Tower(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
		
	}

}
