package xyz.zzyymaggie.algorithm.self.coins;

/**
 * 纸币面额有1，2，5，10，20，50，100,假设有一个自动售货机需要给用户找零x元，请打印所有可能的找零组合。
 */
public class CoinChangeWithAll {
    public static void test1(int x) {
        int sum;
        //符合条件的组合次数
        int count = 0;
        //循环次数
        int times = 0;
        //硬币面额
        int[] a = {1, 2, 5, 10, 20, 50, 100};
        for (int last = 0; last <= x / a[6]; last++) {
            //100元可能出现的张数
            for (int i = 0; i <= x / a[5]; i++) {
                //50元可能出现的张数
                for (int j = 0; j <= x / a[4]; j++) {
                    //20元可能出现的张数
                    for (int k = 0; k <= x / a[3]; k++) {
                        //10元可能出现的张数
                        for (int l = 0; l <= x / a[2]; l++) {
                            //5元可能出现的张数
                            for (int m = 0; m <= x / a[1]; m++) {
                                //1元可能出现的张数
                                //for(int n=0;n<x/1;n++){//这步循环可省略
                                int n = x - (last * a[6] + i * a[5] + j * a[4] + k * a[3] + l * a[2] + m * a[1]);
                                sum = last * a[6] + i * a[5] + j * a[4] + k * a[3] + l * a[2] + m * a[1] + n * a[0];
                                times++;
                                if (sum == x && n >= 0) {
                                    count++;
                                    System.out.println(String.format("%d,%d,%d,%d,%d,%d,%d", n, m, l, k, j, i, last));
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("循环次数：" + times);
        System.out.println("组合数：" + count);
    }

    public static void main(String[] args) {
        CoinChangeWithAll.test1(200);
    }
}
