package xyz.zzyymaggie.algorithm.self;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Main2 main = new Main2();
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            String str = scan.nextLine();
            System.out.println(main.entrance(str));
        }
    }

    public int entrance(String str) {
        String[] pairs = str.split(" ");
        int[] prices = new int[pairs.length];
        for(int i=0;i<pairs.length;i++) {
            prices[i] = this.getRealPrice(pairs[i]);
        }
        return getProfit(prices, 0, prices.length - 1);
    }

    public int getProfit(int[] a, int start, int end) {
        int sum = 0;
        for (int i = start + 1; i <= end; i++) {
            if (a[i] >= a[i - 1]) {
                sum += a[i] - a[i - 1];
            } else {
                sum += getProfit(a, i, end);
                break;
            }
        }
        return sum;
    }

    public int getRealPrice(String str) {
        int price = 0;
        String value = str.substring(0, str.length() - 1);
        int realValue = Integer.parseInt(value);
        String symbol = str.substring(str.length() - 1);
        if ("Y".equalsIgnoreCase(symbol)) {
            price = realValue;
        }else if("S".equalsIgnoreCase(symbol)) {
            price = realValue * 7;
        }
        return price;
    }


}
