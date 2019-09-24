package com.CK;

public class Main {

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
//        int[] prices = {1, 2, 3, 4, 5};
        new Solution().maxProfit(prices);
    }
}

class Solution3 {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int n = prices.length, localMin = prices[0];
        int[] left = new int[n], right = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                left[0] = 0;
                continue;
            }
            left[i] = Math.max(left[i - 1], prices[i] - localMin);
            localMin = Math.min(localMin, prices[i]);
        }

        int localMax = prices[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                right[i] = 0;
                continue;
            }
            right[i] = Math.max(right[i + 1], localMax - prices[i]);
            localMax = Math.max(localMax, prices[i]);
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            result = Math.max(Math.max(result, left[i] + right[i + 1]), right[i]);
        }
        return result;
    }
}

class Solution2 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        if (prices.length == 2) return Math.max(0, prices[1] - prices[0]);

        int[] left = new int[prices.length];
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        int max = prices[prices.length - 1];
        int maxRight = 0;
        int result = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(prices[i], max);
            maxRight = Math.max(maxRight, max - prices[i]);
            result = Math.max(left[i] + maxRight, result);
        }
        return result;
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int oneBuy = Integer.MIN_VALUE;
        int oneBuyOneSell = 0;
        int twoBuy = Integer.MIN_VALUE;
        int twoBuyTwoSell = 0;
        for (int p : prices) {
            twoBuyTwoSell = Math.max(twoBuyTwoSell, p + twoBuy);
            twoBuy = Math.max(twoBuy, oneBuyOneSell - p);
            oneBuyOneSell = Math.max(oneBuyOneSell, p + oneBuy);
            oneBuy = Math.max(oneBuy, -p);
        }
        return Math.max(oneBuyOneSell, twoBuyTwoSell);
    }
}