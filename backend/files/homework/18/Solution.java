package org.scu.judgingsystem.controller.student;

public class Solution {
    // 使用递归方法计算最大公约数
    public static int solve(int a, int b) {
        if (b == 0) {
            return a;
        }
        return solve(b, a % b);
    }
}
