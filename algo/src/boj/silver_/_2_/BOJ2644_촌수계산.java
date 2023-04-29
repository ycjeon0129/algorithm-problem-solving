package boj.silver_._2_;
// bfs, 트리 탐색

import java.io.*;
import java.util.*;

public class BOJ2644_촌수계산 {
	
	static int N, M, targetP, targetC;
	static int[][] family;
	static boolean[] isVisited;
	static Queue<int[]> queue;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		// 2차원 배열 family[][] 에 대해 각 행의 0열은 노드의 값, 행의 N열은 타겟을 탐색하는데 이동한 횟수(촌수)
		family = new int[N+1][N+1];
		isVisited = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		targetP = Integer.parseInt(st.nextToken());
		targetC = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 각 행의 0열에 해당 노드의 값 저장
			family[a][0] = a;
			family[b][0] = b;
			// 각 행의 1열 ~ N-1 열에 친척(연결된 노드) 추가
			add(a, b);
		}
		System.out.println(cal());
	}

	static int cal() {
		queue = new ArrayDeque<int[]>();
		// 시작 타겟을 큐에 추가 후 해당 노드 방문 처리
		queue.offer(family[targetP]);
		isVisited[targetP] = true;
		int[] temp;
		// bfs 탐색
		while (queue.size() > 0) {
			temp = queue.poll();
			// 각 행의 1 ~ N-1 열을 탐색하며 현 노드의 연결 정보 확인
			for (int i = 1; i < temp.length-1; i++) {
				// 해당 인접 노드가 타겟이라면 (현 노드까지 이동 횟수 + 1) 리턴
//				if (family[temp[i]][0]==targetC) {
				if (temp[i]==targetC) {
					return temp[N]+1;
				}
				// 더 이상 저장된 인접 노드 정보가 없다면 중단
				if (temp[i]==0) {
					break;
				}
				// 만약 해당 인접 노드를 방문한 적이 없다면
				// 해당 인접 노드 방문처리, 해당 노드까지 촌수 갱신, 해당 노드 큐에 저장
				if (!isVisited[temp[i]]) {
					isVisited[temp[i]] = true;
					family[temp[i]][N] = temp[N]+1;
					queue.add(family[temp[i]]);
				}
			}
		}
		// 모든 연결 정보를 확인해도 타겟을 찾지 못한다면 -1 리턴
		return -1;
	}

	static void add(int a, int b) {
		int idxA = 1, idxB = 1;
		// 이미 값이 있는(노드 연결 정보가 이미 등록된) 열의 경우 인덱스 증가
		// 노드 연결 정보가 저장되지 않은 첫 열에 새로운 연결 노드 추가
		while (family[a][idxA]!=0) {
			idxA++;
		}
		family[a][idxA] = b;
		while (family[b][idxB]!=0) {
			idxB++;
		}
		family[b][idxB] = a;
	}

}
