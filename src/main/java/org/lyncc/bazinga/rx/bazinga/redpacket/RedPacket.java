package org.lyncc.bazinga.rx.bazinga.redpacket;

import java.util.LinkedList;
import java.util.List;

public class RedPacket {

    private static final int MIN_MONEY = 1;

    private static final int MAX_MONEY = 200 * 100;

    private static final int LESS = -1;

    private static final int MORE = -2;

    private static final int OK = 1;

    private static final double TIMES = 2.1F;

    private int recursiveCount = 0;

    public List<Integer> splitRedPacket(int money, int count) {

        List<Integer> moneys = new LinkedList<>();

        //金额检查，如果最大红包 * 个数 < 总金额；则需要调大最小红包 MAX_MONEY
        if (MAX_MONEY * count <= money) {
            System.err.println("请调大最小红包金额 MAX_MONEY=[" + MAX_MONEY + "]");
            return moneys ;
        }

        //计算出最大红包
        int max = (int) ((money / count) * TIMES);
        max = max > MAX_MONEY ? MAX_MONEY : max;

        for (int i = 0; i < count; i++) {
            //随机获取红包
            int redPacket = randomRedPacket(money, MIN_MONEY, max, count - i);
            moneys.add(redPacket);
            //总金额每次减少
            money -= redPacket;
        }

        return moneys;
    }
    private int randomRedPacket(int totalMoney, int minMoney, int maxMoney, int count) {

        //只有一个红包直接返回
        if (count == 1) {
            return totalMoney;
        }

        if (minMoney == maxMoney) {
            return minMoney;
        }

        if(count * minMoney == totalMoney){
            System.out.println("逗我玩吧");
            return minMoney;
        }

        //如果最大金额大于了剩余金额 则用剩余金额 因为这个 money 每分配一次都会减小
        maxMoney = maxMoney > totalMoney ? totalMoney : maxMoney;

        //在 minMoney到maxMoney 生成一个随机红包
        int redPacket = (int) (Math.random() * (maxMoney - minMoney) + minMoney);

        int lastMoney = totalMoney - redPacket;

        int status = checkMoney(lastMoney, count - 1);

        //正常金额
        if (OK == status) {
            return redPacket;
        }

        //如果生成的金额不合法 则递归重新生成
        if (LESS == status) {
            recursiveCount++;
            System.out.println("recursiveCount==" + recursiveCount);
            return randomRedPacket(totalMoney, minMoney, redPacket, count);
        }

        if (MORE == status) {
            recursiveCount++;
            System.out.println("recursiveCount===" + recursiveCount);
            return randomRedPacket(totalMoney, redPacket, maxMoney, count);
        }

        return redPacket;
    }

    private int checkMoney(int lastMoney, int count) {
        double avg = lastMoney / count;
        if (avg < MIN_MONEY) {
            return LESS;
        }

        if (avg > MAX_MONEY) {
            return MORE;
        }

        return OK;
    }

    public static void main(String[] args) {
        RedPacket redPacket = new RedPacket();
        List<Integer> redPackets = redPacket.splitRedPacket(4, 4);
        System.out.println(redPackets);

        int sum = 0;
        for (Integer red : redPackets) {
            sum += red;
        }
        System.out.println(sum);
    }
}
