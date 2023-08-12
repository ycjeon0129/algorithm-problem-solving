package swea.d2;
// B형 특강 1주차
// bit masking

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1288_새로운_불면증_치료법 {

    static int N, answer;
    static final int GOAL = 0b1_111_111_111;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            answer = 0;
            int state = 0;
            while (state != GOAL) {
                answer++;
                int temp = N * answer;
                while (temp > 0) {
                    int val = temp % 10;
                    state |= (1 << val);
                    temp /= 10;
                }
            }
            sb.append(String.format("#%d %d\n", t, N * answer));

        }
        System.out.println(sb);
    }
}
