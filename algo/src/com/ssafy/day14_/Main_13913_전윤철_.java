package com.ssafy.day14_;
// gold 4. 숨바꼭질 4

import java.io.*;
import java.util.*;

public class Main_13913_전윤철_ {

	static int N, K;
	static final int MAX = 200_001;
	static int[] timestamps = new int[MAX];
	static Queue<Integer> queue;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static {
		Arrays.fill(timestamps, Integer.MAX_VALUE);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (N == K) {
			bw.write(String.format("%d\n%d", 0, N));
		} else {
			queue = new ArrayDeque<Integer>();
			queue.offer(K);
			timestamps[K] = 0;
			bfs();
		}
		bw.flush();
		bw.close();
	}

	private static void bfs() throws IOException {
		loop: while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 0; i < 3; i++) {
				if (isEnd(cur, i)) {
					break loop;
				}
			}
		}
		tracebackAndprint();
	}

	static boolean isEnd(int cur, int i) {
		int next = cur;
		switch (i) {
		case 0:
			next += 1;
			break;
		case 1:
			next -= 1;
			break;
		case 2:
			if (next % 2 == 1) {
				return false;
			}
			next /= 2;
			break;
		}
		if (!isIn(next)) {
			return false;
		}
		if (timestamps[cur] + 1 < timestamps[next]) {
			timestamps[next] = timestamps[cur] + 1;
			if (next == N) {
				return true;
			}
			queue.offer(next);
		}
		return false;
	}

	static boolean isIn(int x) {
		if (x < 0 || x >= Math.max(N, K) * 2 + 1) {
			return false;
		}
		return true;
	}

	private static void tracebackAndprint() throws IOException {
		int x = N, minTime = timestamps[N];
		bw.write(minTime + "\n" + x + " ");
		while (x != K) {
			if (isIn(x + 1) && timestamps[x + 1] == minTime - 1) {
				x++;
			} else if (isIn(x - 1) && timestamps[x - 1] == minTime - 1) {
				x--;
			} else if (isIn(x * 2) && timestamps[x * 2] == minTime - 1) {
				x *= 2;
			}
			minTime--;
			bw.write(x + " ");
		}
	}

}
