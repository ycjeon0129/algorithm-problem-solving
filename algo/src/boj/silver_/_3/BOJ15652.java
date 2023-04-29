package boj.silver_._3;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ15652 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static {
        try {
            st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static int[] arr;
    static int[] output;

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        output = new int[M];

        for (int i=0; i<N; i++) {
            arr[i] = i+1;
        }

        combiR(arr, output, 0, N, M, 0);

        bw.flush();
        bw.close();
    }

    static void combiR(int[] arr, int[] output, int depth, int N, int M, int ptr) throws IOException {
        if (depth == M) {
            print(output, M);
            return;
        }
        for (int i=ptr; i<N; i++) {
            output[depth] = arr[i];
            combiR(arr, output, depth+1, N, M, ptr);
            ptr++;
        }
    }

    static void print(int[] output, int M) throws IOException {
        for (int i=0; i<M; i++) {
            bw.write(output[i] + " ");
        }
        bw.newLine();
    }
}
