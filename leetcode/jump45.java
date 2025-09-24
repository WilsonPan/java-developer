package leetcode;

public class jump45 {

    public int jump(int[] nums) {
        if (nums == null || nums.length <= 0)
            return 0;

        int max = 0, end = 0, step = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == end) {
                end = max;
                step++;
            }
        }
        return step;
    }

    public int jump(int[] nums, int start, int count) {
        return count;
    }

    public static void main(String[] args) {

        System.out.println(new jump45().jump(new int[] { 2, 3, 1, 1, 4 }) == 2);
        System.out.println(new jump45().jump(new int[] { 2, 3, 0, 1, 4 }) == 2);
        System.out.println(new jump45().jump(new int[] { 7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3 }) == 2);
    }
}
