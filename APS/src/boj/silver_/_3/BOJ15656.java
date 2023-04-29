package boj.silver_._3;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15656 {
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

    static int[] arr;
    static int[] output;

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        output = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        permutR(0, N, M);

        bw.flush();
        bw.close();
    }

    static void permutR(int depth, int N, int M) throws IOException {
        if (depth == M) {
            print(M);
            return;
        }
        for (int i=0; i<N; i++) {
            output[depth] = arr[i];
            permutR(depth+1, N, M);
        }
    }

    private static void print(int M) throws IOException {
        for (int i=0; i<M; i++) {
            bw.write(output[i] + " ");
        }
        bw.newLine();
    }
}


