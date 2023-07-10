package boj;
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9466_텀_프로젝트 {

    static int N, answer;
    static int[] arr, checked;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            answer = 0;
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            checked = new int[N + 1];
            queue = new ArrayDeque<Integer>();
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            run();

            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }

    static void run() {
        for (int i = 1; i <= N; i++) {
            if ( checked[i] == 0) {
                dfs(i);
            }
        }
    }

    static void count(int num) {
        int temp = queue.poll();
        while ( temp != num ) {
            checked[temp] = -1;
            answer++;
            if (queue.isEmpty()) {
                break;
            }
            temp = queue.poll();
        }
        queue.clear();
    }

    static void dfs(int cur) {
        queue.offer(cur);
        if (cur == arr[cur]) {
            checked[cur] = 1;
            count(cur);
        } else if (checked[arr[cur]] == 0) {
            checked[cur] = 1;
            dfs(arr[cur]);
        } else if (checked[arr[cur]] > 0) {
            checked[cur] = 1;
            count(arr[cur]);
        } else {
            count(arr[cur]);
        }


//        else if (start == arr[cur]) {    // 시작점과 도착점이 같아 팀이 결정된 경우
//            in[cur] = true;
//        } else if (in[arr[cur]]) { // 이미 결정된 좌표를 만남 -> 현재까지의 좌표들이 모두 팀을 결정하지 못하는 경우
//            in[cur] = true;
//            answer += depth + 1;
//        } else if (cur == arr[cur]) {   // 미등록 1인 팀을 만남 -> 현재까지의 좌표들이 모두 팀을 결정하지 못하는 경우
//            answer += depth;
//            in[cur] = true;
//        } else {
//            in[cur] = true;
//            dfs(start, arr[cur], depth + 1);
//        }

    }
}
