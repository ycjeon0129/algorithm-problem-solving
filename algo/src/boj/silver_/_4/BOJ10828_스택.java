package boj.silver_._4;
// 스택

import java.io.*;
import java.util.*;

public class BOJ10828_스택 {

    static int N, idx;
    static int[] stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        idx = -1;
        stack = new int[N];
        int param;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            switch (op) {
                case "push":
                    param = Integer.parseInt(st.nextToken());
                    stack[++idx] = param;
                    break;
                case "pop":
                    if (idx < 0) bw.write(idx+"\n");
                    else bw.write(stack[idx--]+"\n");
                    break;
                case "size":
                    bw.write((idx+1)+"\n");
                    break;
                case "empty":
                    if (idx < 0) bw.write(1+"\n");
                    else bw.write(0+"\n");
                    break;
                case "top":
                    if (idx < 0) bw.write(idx+"\n");
                    else bw.write(stack[idx]+"\n");
                    break;
            }
        }
        bw.flush();
        bw.close();
    }
}
