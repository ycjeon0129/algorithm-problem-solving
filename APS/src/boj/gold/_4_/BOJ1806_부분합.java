package boj.gold._4_;
// 누적 합, 투 포인터

import java.io.*;
import java.util.*;

public class BOJ1806_부분합 {

    static int N, S;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        run();
    }

    static void run() {
        int fore = 0, rear = 0, sum = 0, len = N+1;
        for (; fore < N; fore++) {  // fore가 마지막 수에 도달할 때까지 탐색
            sum += arr[fore];   // fore 수 합 연산
            if (sum >= S) { // 누적 합이 목표를 도달했을 때
                while (sum >= S) {    // 누적 합이 목표를 만족하는 동안
                    sum -= arr[rear++]; // rear 수 제외
                }
                len = Math.min(len, fore - rear + 2);   // 길이 갱신
            }
        }
        if (len == N+1) len = 0;    // 모든 수의 합이 목표 수보다 작다면 길이 0
        System.out.println(len);
    }
}
