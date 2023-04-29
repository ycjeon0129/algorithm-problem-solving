package ssafy;

/*
    순열 (Permutation)
    서로 다른 것들 중 몇개를 뽑아서 한줄로 나열하는 것
    서로 다른 n개 중 r개를 택한다.
 */
public class Combination {
    static int N;
    static int M;
    static int[] result;
    static boolean[] visited;

    public static void main(String[] args) {
        N = 6;
        M = 3;

        result = new int[M];
        visited = new boolean[N];
        combination(0,0);
//        DupleCombination(0,0);
        BitComb(0,0,0);
    }

    // 중복이 없는 순열
    private static void combination(int cnt, int start) {
        if (cnt == M) {
            print();
            return;
        }
        for (int i = start; i < N; i++) {
            result[cnt] = i+1;
            combination(cnt + 1,i+1);
        }
    }

    // 중복 있는
    private static void DupleCombination(int cnt, int start) {
        if (cnt == M) {
            print();
            return;
        }
        for (int i = start; i < N; i++) {
                DupleCombination(cnt + 1,i);
                result[cnt] = i+1;
            DupleCombination(cnt+1,i);
            }
        }

    private static void print() {
        for (int i = 0; i < M; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }

    private static void BitComb(int cnt, int flag,int start) {
        if (cnt == M) {
            print();
            return;
        }
        for (int i = start; i < N; i++) {
            if ((flag & 1 << i) != 0) continue;
            result[cnt] = i+1;
            BitComb(cnt + 1, flag | 1 << i,i+1);
        }
    }
}

