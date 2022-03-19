package LeetCode_Class;

import java.util.*;

public class Bank {
    long[] balance;
    public Bank(long[] balance) {
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 > balance.length || account2 > balance.length || balance[account1-1]<money) {
            return false;
        }
        balance[account1-1] -= money;
        balance[account2-1] += money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if(account > balance.length) {
            return false;
        }
        balance[account-1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if(account > balance.length || balance[account-1] < money) {
            return false;
        }
        balance[account-1] -= money;
        return true;
    }



}

// ------------------
class solution_1 {
    // 面试题 17.18. 最短超串  短的元素均不相同
    // 滑动窗口 like 76.最小覆盖字串
    public int[] shortestSeq(int[] big, int[] small) {
        int[] res = new int[2];
        return res;
    }

    public String toGoatLatin(String sentence) {
        Set<Character> set = new HashSet<>();
        Collections.addAll(set,'a','e','i','o','u','A','E','I','O','U');
        String[] tmp = sentence.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tmp.length; i++) {
            String s = tmp[i];
            if (set.contains(s.charAt(0))) {
                res.append(s);
            } else {
                res.append(s.substring(1)).append(s.charAt(0));
            }
            res.append("ma");
            for (int j = 0; j < i + 1; j++) {
                res.append("a");
            }
            res.append(" ");
        }
        return res.toString().trim();
    }



}