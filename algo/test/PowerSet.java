package ssafy;

public class PowerSet {
    static int N;
    static int M;
    static int[] result;
    static boolean[] visited;
    public static void main(String[] args) {
        N = 3;

        visited = new boolean[N];
        powerSet(0);
        bitPowerSet(    0);
    }


    private static void powerSet(int cnt){
        if( cnt == N){
            print();
            return;
        }

            visited[cnt] = false;
            powerSet(cnt+1);
            visited[cnt] = true;
            powerSet(cnt+1);
    }

    private static void bitPowerSet(int cnt){
        // 부분집합의 개수만큼 돈다 2^n
        for(int i = 0; i < 1<<N; i++){
            for(int j = 0; j < N; j++){
                if((i & 1<< j) !=0 )continue;
                System.out.print(j+1+" ");
            }
            System.out.println();
        }
    }
    private static void print() {

        for(int i = 0; i < N; i++){
            if (visited[i]) System.out.print(i+1+" ");
        }
        System.out.println();
    }
}
