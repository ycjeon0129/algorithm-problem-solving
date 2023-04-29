package boj.silver_._2_;
// 연결 요소의 개수
// Queue를 이용한 2차원 노드 탐색

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11724 {

	static int N, M, num;
	static boolean[][] graph;
	static boolean[] visited;
	static QueueManager qm = new QueueManager();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u][v] = true;
			graph[v][u] = true;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (graph[i][j]) {
					countGroup(i);
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				num++;
			}
		}

		System.out.println(num);

	}

	static void countGroup(int i) {
		visited[i] = true;
		while (true) {
			for (int j = 1; j <= N; j++) {
				// 연결이 있다면
				if (graph[i][j]) {
					graph[i][j] = false;
					if (!visited[j]) {
						qm.add(j);
						visited[j] = true;
					}
				}

			}
			if (qm.isEmpty()) {
				num++;
				break;
			}
			i = qm.remove().value;
		}

	}

	static class CCQueue {
		int value;
		CCQueue nextNode;

		public CCQueue(int value) {
			this.value = value;
			this.nextNode = null;
		}

	}

	static class QueueManager {
		CCQueue front, rear;

		public QueueManager() {
			front = rear = null;
		}

		void add(int value) {
			CCQueue element = new CCQueue(value);
			if (isEmpty()) {
				rear = front = element;
			} else {
				rear.nextNode = element;
				rear = element;
			}
		}

		CCQueue peek() {
			if (isEmpty()) {
				return null;
			}
			return front;
		}

		CCQueue remove() {
			if (isEmpty()) {
				return null;
			}
			CCQueue pop = front;
			front = front.nextNode;
			return pop;
		}

		boolean isEmpty() {
			if (front == null) {
				return true;
			}
			return false;
		}

	}
}
