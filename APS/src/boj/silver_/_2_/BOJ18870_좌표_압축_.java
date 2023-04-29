package boj.silver_._2_;
// 시간초과

import java.io.*;
import java.util.*;

public class BOJ18870_좌표_압축_ {

    static int N;
    static int[] input;
    static PriorityQueue<Integer> orders;
    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        input = new int[N];
        orders = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            if (!orders.contains(input[i])) {
                orders.add(input[i]);
            }
        }

        int idx = 0;
        while (!orders.isEmpty()) {
            map.put(orders.poll(), idx++);
        }
        for (int i = 0; i < N; i++) {
            bw.write(String.format("%d ", map.get(input[i])));
        }
        bw.flush();
        bw.close();
    }
}
