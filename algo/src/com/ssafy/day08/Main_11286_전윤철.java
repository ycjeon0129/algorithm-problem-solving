package com.ssafy.day08;
// silver 1. 절대값 힙

import java.io.*;

public class Main_11286_전윤철 {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, num;
	static int[][] minAbsHeap;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		num = 1;
		int cur;
		// 최소 절대값 힙
		minAbsHeap = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			cur = Integer.parseInt(br.readLine());
			// 입력값이 0이 아니라면 힙에 추가
			if (cur != 0) {
				minAbsHeap[num][0] = cur;
				minAbsHeap[num][1] = Math.abs(cur);
				// 새로운 값이 추가된 힙에 힙 유효성 검사
				setMAH();
				// 힙에 추가된 자료 개수 갱신
				num++;
			}
			// 입력값이 0이라면 루트 노드 반환 및 힙 갱신 
			else {
				getMinAbs();
			}
		}
		bw.flush();
		bw.close();
	}

	// 힙의 루트 노드 반환 및 갱신
	static void getMinAbs() throws IOException {
		// 힙이 비어있다면 0 출력
		if (num == 1) {
			bw.write(0+"\n");
			return;
		}
		// 힙의 루트 노드 출력
		bw.write(minAbsHeap[1][0]+"\n");
		// 길이가 2 이상이라면 말단 노드를 루트 노드로 옮김
		if (num != 2) {
			minAbsHeap[1][0] = minAbsHeap[num - 1][0];
			minAbsHeap[1][1] = minAbsHeap[num - 1][1];
		}
		// 기존 말단 노드 초기화
		minAbsHeap[num - 1][0] = 0;
		minAbsHeap[(num--) - 1][1] = 0;
		// 각 노드의 좌, 우 자식 노드에 대해 힙 유효성 검사
		setMAHRe(1, 0);
		setMAHRe(1, 1);
	}

	// 루트부터 유효성 검사
	static void setMAHRe(int start, int add) {
		int parent = start;
		int child = start * 2 + add;
		if (child > N) {
			return;
		}
		if (check(child, parent)) {
			if (minAbsHeap[child][0] == 0) {
				return;
			}
			if (check(child, parent)) {
				swap(child, parent);
				setMAHRe(child, 0);
				setMAHRe(child, 1);
			}
		}
	}

	// 말단 노드부터 유효성 검사
	static void setMAH() {
		int parent = num / 2;
		int child = num;
		
		while (check(child, parent)) {
			swap(child, parent);
			parent /= 2;
			child /= 2;
		}
	}

	// 자식 노드가 부모 노드와 바뀌어야 하는가
	static boolean check(int child, int parent) {
		if (minAbsHeap[child][1] < minAbsHeap[parent][1]
				|| (minAbsHeap[child][1] == minAbsHeap[parent][1] && minAbsHeap[child][0] < minAbsHeap[parent][0])) {
			return true;
		}
		return false;
	}

	// 두 노드를 바꾸어라
	static void swap(int child, int parent) {
		int[] temp = minAbsHeap[child];
		minAbsHeap[child] = minAbsHeap[parent];
		minAbsHeap[parent] = temp;
	}
	
}
