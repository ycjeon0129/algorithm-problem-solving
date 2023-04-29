package boj.silver_._1_;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1697_숨바꼭질 {

	static int N, K, minSec = Integer.MAX_VALUE;
	static boolean[] visited;
	static Queue<co> hideAndSeek;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		visited = new boolean[100_001];
		hideAndSeek = new ArrayDeque<co>();

		find();

		System.out.println(minSec);
	}

	static void find() {
		hideAndSeek.offer(new co(K, 0));
		co cur = null;
		while (hideAndSeek.size() > 0) {
			cur = hideAndSeek.poll();
			if (cur.x>100_000 || cur.x<0) {
				continue;
			}
			if (!visited[cur.x]) {
				visited[cur.x] = true;
				if (cur.x != N) {
					if (cur.x % 2 == 0) {
						hideAndSeek.offer(new co(cur.x / 2, cur.sec + 1));
					}
					hideAndSeek.offer(new co(cur.x - 1, cur.sec + 1));
					hideAndSeek.offer(new co(cur.x + 1, cur.sec + 1));
				} else {
					minSec = minSec > cur.sec ? cur.sec : minSec;
				}
			}

		}
	}

	static class co {
		int x;
		int sec;

		co(int x, int sec) {
			this.x = x;
			this.sec = sec;
		}
	}
}
