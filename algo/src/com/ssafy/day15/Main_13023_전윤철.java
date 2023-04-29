package com.ssafy.day15;
// gold 5. ABCDE
// dfs, 그래프

import java.io.*;
import java.util.*;

public class Main_13023_전윤철 {
	
	static int N, M, result;
	static final int insider = 5;
	static boolean[] known; 
	static ArrayList<Integer>[] camper;

	public static void main(String[] args) throws IOException {		
		 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		camper = new ArrayList[N];
		known = new boolean[N];
		result = 0;
		
		int front, rear;
		// 입력 정보에 대한 인접 리스트 생성
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			front = Integer.parseInt(st.nextToken());
			rear = Integer.parseInt(st.nextToken());
			if (camper[front] == null) {
				camper[front] = new ArrayList<Integer>();
			} 
			if (camper[rear] == null) {
				camper[rear] = new ArrayList<Integer>();
			}
			camper[front].add(rear); 
			camper[rear].add(front);
		}
		for (int i = 0; i < N; i++) {
			known[i] = true;
			if (camper[i]==null) {
				continue;
			}
			if (connectRelation(i, 1) ) {
				break;
			}
			known[i] = false;
		}
		System.out.println(result);
	}
	
	// 재귀로 구현한 연결 정보 확인 dfs. 길이가 5 이상이라면 true 리턴 후 종료
	static boolean connectRelation(int idx, int length) {
		for (int i = 0; i < camper[idx].size(); i++) {
			if (!known[camper[idx].get(i)]) {
				if (length==insider-1) {
					result = 1;
					return true;
				}
				known[camper[idx].get(i)] = true;
				connectRelation(camper[idx].get(i), length+1);
				known[camper[idx].get(i)] = false;
			}
		}
		return false;
	}

}
