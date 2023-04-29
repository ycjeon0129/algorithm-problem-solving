package boj.silver_._3;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ15651 {
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

        for (int i=0; i<N; i++) {
            arr[i] = i+1;
        }
        permutR(arr, output, 0, N, M);

        bw.flush();
        br.close();
    }

    static void permutR(int[] arr, int[] output, int depth, int N, int M) throws IOException {
        if (depth == M) {
            print(output, M);
            return;
        }
        for (int i=0; i<N; i++) {
            output[depth] = arr[i];
            permutR(arr, output, depth+1, N, M);
        }
    }

    static void print(int[] output, int M) throws IOException {

        for (int i=0; i<M; i++) {
            bw.write(String.valueOf(output[i])+" ");
        }
        bw.newLine();
    }
}
