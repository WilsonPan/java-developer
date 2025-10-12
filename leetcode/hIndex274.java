public class hIndex274 {

    public int hIndex(int[] citations) {
        if (citations == null || citations.length <= 0)
            return 0;

        int left = 0, right = citations.length;
        int mid = 0, cnt = 0;
        while (left < right) {
            mid = (left + right + 1) >> 1;
            cnt = 0;
            for (int i = 0; i < citations.length; i++) {
                if (citations[i] >= mid) {
                    cnt++;
                }
            }
            if (cnt >= mid) {
                // 要找的答案在 [mid,right] 区间内
                left = mid;
            } else {
                // 要找的答案在 [0,mid) 区间内
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(new hIndex274().hIndex(new int[] { 3, 0, 6, 1, 5 }));
        System.out.println(new hIndex274().hIndex(new int[] { 1 }));
    }
}
