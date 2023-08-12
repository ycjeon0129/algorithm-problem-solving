package swea.d3;
// B형 특강 1주차
// bit masking

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA10726_이진수_표현 {

    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int mask = (1 << N) - 1;
            String answer = ((mask | M) == M) ? "ON" : "OFF";
            sb.append(String.format("#%d %s\n", t, answer));
        }
        System.out.println(sb);
    }
}
