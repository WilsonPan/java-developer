package leetcode;

public class canJump55 {

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1)
            return true;

        int n = nums.length - 1;
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (max >= n)
                return true;
            if (max >= i) {
                max = Math.max(max, nums[i] + i);
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[] nums = new int[] { 3, 2, 1, 0, 4 };

        System.out.println(new canJump55().canJump(nums));
    }
}
