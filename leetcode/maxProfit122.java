package leetcode;

public class maxProfit122 {

    /**
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        int maxProfit = 0, minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) { // 若价格比之前低，应该买入
                minPrice = prices[i];
            } else if (i == prices.length - 1) { // 最后一天，强制清盘
                if (prices[i] - minPrice > 0) {
                    maxProfit += (prices[i] - minPrice);
                }
            } else if (prices[i + 1] < prices[i]) { // 若后一天价格比当前低&&之前已经买入，就应该当天卖出
                maxProfit += (prices[i] - minPrice);
                minPrice = prices[i + 1];
            }

            System.out.println(
                    String.join(",", String.valueOf(prices[i]), String.valueOf(minPrice), String.valueOf(maxProfit)));
        }
        return maxProfit;
    }

    public static void main(String[] args) {

        int[] pirces = new int[] { 5, 4, 3, 2, 1 };

        System.out.println(new maxProfit122().maxProfit(pirces));
    }
}
