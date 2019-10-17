package wilburhsu.LeetCode;

public class _121_BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int minStockPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i = 0;i < prices.length;i++){
            if(prices[i] < minStockPrice)
                minStockPrice = prices[i];
            else if(prices[i] - minStockPrice > maxProfit)
                maxProfit = prices[i] - minStockPrice;
        }
        return maxProfit;
    }
}
