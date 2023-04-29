package boj.platinum._5_;
// 구현, 스택, 회의실 예약하기

import java.io.*;
import java.util.*;

public class BOJ14865_곡선_자르기 {

    static int N, notIncluded, notIncluding;
    static int[][] edges;
    static List<int[]> mountains;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        mountains = new ArrayList<int[]>(); // 봉우리를 저장할 ArrayList

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
        int firstX = x, firstY = y, lastY, spareStart = 0;  // 첫 정점 정보 저장
        boolean isBalanced = true, isFinished = true, isStartNegative = true;
        if (y > 0) {    // 시작 정점의 y값이 양수인 경우 대응
            isBalanced = false;
            spareStart = x;
            isStartNegative = false;
        }

        int start = 0, end = 0, spareEnd = 0;
        for (int i = 1; i < N; i++) {
            lastY = y;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            if (Integer.signum(y) != Integer.signum(lastY)) {   // 봉우리의 한 끝점인 경우
                if (y > 0) {
                    start = x;
                } else if (isBalanced) {    // 두 정점을 오름차순으로 정렬하여 봉우리 저장
                    end = x;
                    mountains.add(new int[] {start<end? start: end, start<end? end: start});			// 산 추가
                } else {    // 시작 정점의 y값이 양수인 경우 마지막에 나올 봉우리의 끝점과 연결하기 위해 저장
                    spareEnd = x;
                    isBalanced = true;
                    isFinished = false;
                }
            }
        }
        if (!isFinished) {  // 저장해놓은 시작 정점이 있다면
            if (y < 0) {    // 마지막 정점의 y값이 음수일 경우
                mountains.add(new int[] {spareStart<spareEnd? spareStart: spareEnd, spareStart<spareEnd? spareEnd: spareStart});
            } else {    // 마지막 정점의 y값이 양수일 경우
                mountains.add(new int[] {start<spareEnd? start: spareEnd, start<spareEnd? spareEnd: start});
            }
        }
        if (isStartNegative && Integer.signum(y)!=Integer.signum(firstY)) { // 시작 정점이 음수이고 끝 정점이 양수인 경우
            mountains.add(new int[] {start<firstX? start: firstX, start<firstX? firstX: start});
        }

        mountains.sort(new Comparator<int[]>() {	// 산을 시작점이 좌측에 있는 순으로 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int last = Integer.MIN_VALUE;
        int size = mountains.size();
        if (size == 1) {    // 봉우리가 1개일 때
            notIncluding++;
            notIncluded++;
        } else if (size == 2) { // 봉우리가 2개일 때
            notIncluding++;
            notIncluded++;
            if (mountains.get(0)[1] < mountains.get(1)[0]) {    // 두 봉우리가 모두 독립
                notIncluding++;
                notIncluded++;
            }
        } else {    // 봉우리가 3개 이상일 때
            if (mountains.get(0)[1] < mountains.get(1)[0]) {    // 첫 봉오리가 독립
                notIncluding++;
            }
            notIncluded++;
            last = mountains.get(0)[1];
            for (int i = 1; i < mountains.size()-1; i++) {
                if ( (mountains.get(i)[1]<mountains.get(i+1)[0]) && (last<mountains.get(i)[0]) ) { // 독립된 봉우리일 경우 두 수에 모두 1 추가
                    notIncluded++;
                    notIncluding++;

                } else {	// 독립되지 않았다면
                    if (mountains.get(i)[1] < mountains.get(i+1)[0]) {
                        notIncluding++;
                    }
                    if (last < mountains.get(i)[0]) {
                        notIncluded++;
                    }
                }
                last = Math.max(mountains.get(i)[1], last);
            }
            if (last < mountains.get(size-1)[0]) {  // 마지막 봉오리가 독립
                notIncluded++;
            }
            notIncluding++;
        }

        System.out.println(notIncluded + " " + notIncluding);
    }
}
