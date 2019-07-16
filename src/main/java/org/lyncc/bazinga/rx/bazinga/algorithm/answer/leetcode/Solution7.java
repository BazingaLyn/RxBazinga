package org.lyncc.bazinga.rx.bazinga.algorithm.answer.leetcode;

public class Solution7 {

    public static int reverse(int x) {

        int p = x;
        int result = 0;
        while (p != 0) {
            int q = p % 10;
            result = result * 10 + q;
            p = p / 10;
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println((1 << 31) - 1);
        System.out.println(reverse(5488));
    }

}
