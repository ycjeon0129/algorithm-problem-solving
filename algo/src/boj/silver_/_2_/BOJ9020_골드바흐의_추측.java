package boj.silver_._2_;
// 정수론

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BOJ9020_골드바흐의_추측 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static boolean[] prime = new boolean[10_001];
    static List<Integer> primesOnly = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        findPrimes();

        for (int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            goldbach(N);
        }
        bw.flush();
        bw.close();
    }

    static void goldbach(int n) throws IOException {
        String msg = null;
        int idx = 0;
        while ( primesOnly.get(idx) <= (N/2) ) {
            if (!prime[n-primesOnly.get(idx)]) {
                msg = primesOnly.get(idx) + " " + (n-primesOnly.get(idx));
            }
            idx++;
        }
        bw.write(msg + "\n");
    }

    static void findPrimes() {
        prime[0] = prime[1] = true;
        for (int i=2; i*i<prime.length; i++) {
            if (prime[i]) {
                continue;
            }
            for (int j=i+i; j<prime.length; j+=i) {
                prime[j] = true;
            }
        }
        for (int i=2; i<prime.length; i++) {
            if (!prime[i]) {
                primesOnly.add(i);
            }
        }
    }
}
