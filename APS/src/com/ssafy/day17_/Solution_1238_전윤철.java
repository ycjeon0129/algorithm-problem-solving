package com.ssafy.day17_;
// D4. Contact
// bfs

import java.io.*;
import java.util.*;

public class Solution_1238_전윤철 {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, first;
	static Integer lastNumber;
	static List<Integer> contated, lastNumbers;
	static Graph edges;
	static Queue<Integer[]> queue;
	static class Graph extends ArrayList<ArrayList<Integer>> {
		List<Integer> relationList;	// 각 ArrayList의 인덱스 정보를 담을 ArrayList
		Graph(int first) {
			add(new ArrayList<>());	// 연락을 시작할 번호를 지정하는 생성자
			get(0).add(first);
			relationList = new ArrayList<Integer>();
			relationList.add(first);
		}
		void add(int from, int to) { // 연락 관계를 입력받는 메서드
			if (!relationList.contains(from)) {
				add(new ArrayList<>());
				get(size()-1).add(from);
				relationList.add(from);
			}
			if (!relationList.contains(to)) {
				add(new ArrayList<>());
				get(size()-1).add(to);
				relationList.add(to);
			}
			get(relationList.indexOf(from)).add(to);
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = 10;

		for (int t = 1; t <= T; t++) {
			lastNumber = -1;
			st = new StringTokenizer(br.readLine());
			bw.write("#" + t + " ");
			N = Integer.parseInt(st.nextToken());
			first = Integer.parseInt(st.nextToken()); // 연락을 시작할 노드
			edges = new Graph(first);
			st = new StringTokenizer(br.readLine());
			int start = 0, end = 0;
			for (int i = 0; i < N; i++) {
				if (i%2 == 0) {
					start = Integer.parseInt(st.nextToken());
				} else {
					end = Integer.parseInt(st.nextToken());
					edges.add(start, end);
				}
			}
			contated = new ArrayList<>(); // 이미 연락한 노드 정보를 보관할 ArrayList
			contact();
			Collections.sort(lastNumbers, Collections.reverseOrder()); // 마지막으로 연락한 노드들을 내림차순으로 정렬
			bw.write(lastNumbers.get(0) + "\n");
		}
		bw.flush();
		bw.close();
	}

	static void contact() {
		queue = new ArrayDeque<>();
		queue.offer(new Integer[] {edges.get(0).get(0), 0}); // 연락 시작 노드를 깊이 0으로 queue에 저장
		contated.add(first); // 연락 시작 노드 방문 처리

		while (!queue.isEmpty()) {
			Integer[] temp = queue.poll();
			int index = edges.relationList.indexOf(temp[0]); // 현 노드의 인덱스 계산
			if (!temp[1].equals(lastNumber)) { // 현 노드의 깊이가 이전과 다르다면
				lastNumbers = new ArrayList<>(); // 마지막에 연락받을 노드 리스트 초기화
				lastNumber = temp[1]; // 최대 깊이 갱신
			}
			lastNumbers.add(temp[0]); // 현 노드를 마지막 연락 리스트에 저장

			for (int i = 1; i < edges.get(index).size(); i++) {
				int next = edges.get(index).get(i); // 다음으로 연락받을 노드 리스트
				if (!contated.contains(next)) { // 해당 노드를 아직 방문하지 않았다면
					contated.add(next); // 해당 노드 방문 처리
					queue.offer(new Integer[] {next, temp[1]+1}); // 해당 노드를 현 깊이+1 로 방문 큐에 저장
				}
			}
		}
	}
}
