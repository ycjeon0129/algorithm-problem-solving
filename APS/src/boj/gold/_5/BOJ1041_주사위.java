package boj.gold._5;
// 수학, 그리디

import java.io.*;
import java.util.*;

public class BOJ1041_주사위 {
	
	static int N;
	static int[][] vertices = { {0, 1, 2}, {0, 2, 4}, {0, 4, 3}, {0, 3, 1},	// 꼭짓점에 올 수 있는 모든 주사위 면 조합
								{5, 1, 2}, {5, 2, 4}, {5, 4, 3}, {5, 3, 1} };
	static int[][] edges = { {0, 1}, {0, 2}, {0, 3}, {0, 4},	// 모서리에 올 수 있는 모든 주사위 면 조합
							 {5, 1}, {5, 2}, {5, 3}, {5, 4},
							 {1, 2}, {2, 4}, {3, 4}, {3, 1} };
	static long sumOfFacets;
	static int[] dice;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		dice = new int[6];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		if (N == 1) {
			Arrays.sort(dice);
			for (int i = 0; i < 5; i++) {
				sumOfFacets += dice[i];
			}
		} else {
			int vertex = Integer.MAX_VALUE, edge = Integer.MAX_VALUE, temp;
			for (int i = 0; i < vertices.length; i++) {
				temp = 0;
				for (int j = 0; j < vertices[0].length; j++) {
					temp += dice[vertices[i][j]];
				}
				vertex = Math.min(vertex, temp);
			}
			for (int i = 0; i < edges.length; i++) {
				temp = 0;
				for (int j = 0; j < edges[0].length; j++) {
					temp += dice[edges[i][j]];
				}
				edge = Math.min(edge, temp);
			}
			Arrays.sort(dice);

			sumOfFacets += (long) (4l * vertex);
			sumOfFacets += (long) ((8l * N - 12l) * edge);
			sumOfFacets += (long) (((long) (5l * N * N) - (16l * N) + 12) * dice[0]);
		}
		System.out.println(sumOfFacets);
	}

}
