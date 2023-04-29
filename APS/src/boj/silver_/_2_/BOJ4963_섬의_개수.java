package boj.silver_._2_;
// bfs, 그래프 탐색

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ4963_섬의_개수 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean[][] map;
	static int w, h, num;
	static List<Pair> co;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 || h == 0) {
				break;
			}
			num = 0;
			map = new boolean[h + 1][w + 1];
			for (int i = 1; i <= h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= w; j++) {
					if (Integer.parseInt(st.nextToken()) == 1) {
						map[i][j] = true;
					}
				}
			}

			islands();
			bw.write(num+"\n");
		}
		bw.flush();
		bw.close();
	}

	static void islands() {
		for (int i = 1; i <= h; i++) {
			for (int j = 1; j <= w; j++) {
				if (map[i][j]) {
					co = new ArrayList<Pair>();
					num++;
					map[i][j]=false;
					walk(i, j);
				}
			}
		}

	}

	static void walk(int y, int x) {
		int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

		for (int i = 0; i < dy.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 1 || nx > w || ny < 1 || ny > h) {
				continue;
			}
			if (map[ny][nx]) {
				co.add(new Pair(nx, ny));
				map[ny][nx] = false;				
			}
		}
		if (co.size()!=0) {
			for (int i=0; i<co.size(); i++) {
				if (co.get(i)==null) {
					continue;
				}
				int tempX = co.get(i).x;
				int tempY = co.get(i).y;
				co.set(i, null);
				walk(tempY, tempX);
			}
		}
	}

	static class Pair {
		int x = 0;
		int y = 0;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
