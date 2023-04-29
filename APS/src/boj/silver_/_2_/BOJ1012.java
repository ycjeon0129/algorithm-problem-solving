package boj.silver_._2_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1012 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
    static {
        try {
            st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[][] map;
    static int[] result;
    static List<Pair> queue;
    
	public static void main(String[] args) throws IOException {
		
		int T = Integer.parseInt(st.nextToken());
		result = new int[T];
			
		for (int t=0; t<T; t++) {
			queue = new ArrayList<Pair>();
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			map = new boolean[N][M];
			int x, y;
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				map[y][x] = true;
			}
			result[t] = search(M, N);
		}
		
		for (int t=0; t<T; t++) {
			bw.write(result[t]+"");
			bw.newLine();
		}
		bw.flush();
		bw.close();
		
	}
	static int search(int M, int N) {
		int count = 0;
		for (int i=0; i<map.length; i++) {
			for (int j=0; j<map[0].length; j++) {
				if (map[i][j]) {
					push(new Pair(j, i));
					around(j, i, M, N);
					while (peek() != null) {
						Pair temp = peek();
						around(temp.x, temp.y, M, N);
						pop();
					}
					count++;
				}
			}
		}
		
		return count;
	}

	static void around(int x, int y, int M, int N) {
		int dx[] = {1, 0, -1, 0};
		int dy[] = {0, -1, 0, 1};
		
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
				continue;
			}
			if (map[ny][nx]) {
				push(new Pair(nx, ny));
				map[ny][nx] = false;
			}
		}
	}
	
	static void push(Pair co) {
		queue.add(co);
	}
	static Pair pop() {
		if (queue.size() == 0) {
			return null;
		}
		return queue.remove(0);
	}
	static Pair peek() {
		if (queue.size() == 0) {
			return null;
		}
		return queue.get(0);
	}
	public static class Pair {
		int x, y;
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}

}
