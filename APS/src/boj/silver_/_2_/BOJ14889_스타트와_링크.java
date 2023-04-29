package boj.silver_._2_;
// 조합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889_스타트와_링크 {

	static int N, fair = Integer.MAX_VALUE;
	static int[][] ability;
	static boolean[] team;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        ability = new int[N+1][N+1];
        team = new boolean[N+1];
        
        for (int i=1; i<=N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=1; j<=N; j++) {
    			ability[i][j] = Integer.parseInt(st.nextToken());
    		}
		}
        combi(1, 0);
        System.out.println(fair);
    }

	static void combi(int start, int depth) {
		if (depth==N/2) {
			balance();
			return;
		}
		for (int i=start; i<=N; i++) {
			if (i!=1 && depth==0) {
				return;
			}
			team[i] = true;
			combi(i+1, depth+1);
			team[i] = false;
		}
	}

	static void balance() {
		int start = 0;
		int link = 0;
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				if (team[i] && team[j]) {
					start += ability[i][j];
				} else if (!team[i] && !team[j]) {
					link += ability[i][j];
				}
			}
		}
		fair = Math.min(fair, Math.abs(start-link));
	}
    
    
}
