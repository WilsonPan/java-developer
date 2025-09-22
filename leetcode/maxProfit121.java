package leetcode;

public class maxProfit121 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }

    public static void main(String[] args) {

        int[] pirces = new int[] { 3, 7, 1, 2, 3 };

        System.out.println(new maxProfit121().maxProfit(pirces));
    }
}
