package boj.silver_._1_;
// 그리디, 정렬

import java.io.*;
import java.util.*;

public class BOJ1931_회의실_배정 {

    static int N, answer;
    static int[][] meetings;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        meetings = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken());
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(meetings, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[1]==o2[1]) ? o1[0]-o2[0]: o1[1]-o2[1];
            }
        });
        int end = 0;
        for (int[] cur : meetings) {
            if (end <= cur[0]) {
                end = cur[1];
                answer++;
            }
        }
        System.out.println(answer);
    }
}
