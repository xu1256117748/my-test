package com.xyk;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2021-11-08 10:04
 * @Description 雪花算法
 * 分布式id生成算法的有很多种，Twitter的SnowFlake就是其中经典的一种。
 * ------------------------共计64bit的大整数--------------------------
 * 0-00000000 00000000 00000000 00000000 0-00000000 00-00000000 0000
 * 符号位--------------时间戳-----------------工作机器id------序列号-----
 * -----------------------------------------------------------------
 * 1bit，不用，因为二进制中最高位是符号位，1表示负数，0表示正数。生成的id一般都是用整数，所以最高位固定为0。
 * -----------------------------------------------------------------
 * 41bit-时间戳，用来记录时间戳，毫秒级。
 * - 41位可以表示个数字，
 * - 如果只用来表示正整数（计算机中正数包含0），可以表示的数值范围是：0 至 ，减1是因为可表示的数值范围是从0开始算的，而不是1。
 * - 也就是说41位可以表示个毫秒的值，转化成单位年则是年
 * -----------------------------------------------------------------
 * 10bit-工作机器id，用来记录工作机器id。
 * - 可以部署在个节点，包括5位datacenterId和5位workerId
 * - 5位（bit）可以表示的最大正整数是，即可以用0、1、2、3、....31这32个数字，来表示不同的datecenterId或workerId
 * -----------------------------------------------------------------
 * 12bit-序列号，序列号，用来记录同毫秒内产生的不同id。
 * - 12位（bit）可以表示的最大正整数是，即可以用0、1、2、3、....4094这4095个数字，来表示同一机器同一时间截（毫秒)内产生的4095个ID序号。
 * -----------------------------------------------------------------
 * 由于在Java中64bit的整数是long类型，所以在Java中SnowFlake算法生成的id就是long来存储的。
 * -------------------------------------------------------------
 * SnowFlake可以保证：
 * 所有生成的id按时间趋势递增
 * 整个分布式系统内不会产生重复id（因为有datacenterId和workerId来做区分）
 * -------------------------------------------------------------
 */
public class SnowFlakeUtil {
    public static class IdWorker{

        //下面两个每个5位，加起来就是10位的工作机器id
        private long workerId;    //工作id
        private long datacenterId;   //数据id
        //12位的序列号
        private long sequence;

        public IdWorker(long workerId, long datacenterId, long sequence){
            // sanity check for workerId
            if (workerId > maxWorkerId || workerId < 0) {
                throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
            }
            if (datacenterId > maxDatacenterId || datacenterId < 0) {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
            }
            System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                    timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

            this.workerId = workerId;
            this.datacenterId = datacenterId;
            this.sequence = sequence;
        }

        //初始时间戳
        private long twepoch = 1288834974657L;

        //长度为5位
        private long workerIdBits = 5L;
        private long datacenterIdBits = 5L;
        //最大值
        private long maxWorkerId = -1L ^ (-1L << workerIdBits);
        private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
        //序列号id长度
        private long sequenceBits = 12L;
        //序列号最大值
        private long sequenceMask = -1L ^ (-1L << sequenceBits);
        //工作id需要左移的位数，12位
        private long workerIdShift = sequenceBits;
        //数据id需要左移位数 12+5=17位
        private long datacenterIdShift = sequenceBits + workerIdBits;
        //时间戳需要左移位数 12+5+5=22位
        private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

        //上次时间戳，初始值为负数
        private long lastTimestamp = -1L;

        public long getWorkerId(){
            return workerId;
        }

        public long getDatacenterId(){
            return datacenterId;
        }

        public long getTimestamp(){
            return System.currentTimeMillis();
        }

        //下一个ID生成算法
        public synchronized long nextId() {
            long timestamp = timeGen();

            //获取当前时间戳如果小于上次时间戳，则表示时间戳获取出现异常
            if (timestamp < lastTimestamp) {
                System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                        lastTimestamp - timestamp));
            }

            //获取当前时间戳如果等于上次时间戳（同一毫秒内），则在序列号加一；否则序列号赋值为0，从0开始。
            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) & sequenceMask;
                if (sequence == 0) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0;
            }

            //将上次时间戳值刷新
            lastTimestamp = timestamp;

            /**
             * 返回结果：
             * (timestamp - twepoch) << timestampLeftShift) 表示将时间戳减去初始时间戳，再左移相应位数
             * (datacenterId << datacenterIdShift) 表示将数据id左移相应位数
             * (workerId << workerIdShift) 表示将工作id左移相应位数
             * | 是按位或运算符，例如：x | y，只有当x，y都为0的时候结果才为0，其它情况结果都为1。
             * 因为个部分只有相应位上的值有意义，其它位上都是0，所以将各部分的值进行 | 运算就能得到最终拼接好的id
             */
            return ((timestamp - twepoch) << timestampLeftShift) |
                    (datacenterId << datacenterIdShift) |
                    (workerId << workerIdShift) |
                    sequence;
        }

        //获取时间戳，并与上次时间戳比较
        private long tilNextMillis(long lastTimestamp) {
            long timestamp = timeGen();
            while (timestamp <= lastTimestamp) {
                timestamp = timeGen();
            }
            return timestamp;
        }

        //获取系统时间戳
        private long timeGen(){
            return System.currentTimeMillis();
        }

        //---------------测试---------------
        public static void main(String[] args) {
            IdWorker worker = new IdWorker(1,1,1);
            for (int i = 0; i < 30; i++) {
                System.out.println(worker.nextId());
            }
        }

    }
}
