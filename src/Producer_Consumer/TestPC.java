package Producer_Consumer;

import java.util.Queue;
import java.util.concurrent.*;

// https://blog.csdn.net/qq_40550018/article/details/87859399
// https://www.jianshu.com/p/e29632593057
/*
 不加锁，多线程读的话，是不是可以每个线程维护一个自己的pivot
 生产者队列生产到index，消费者i查看自己的pivot_i，大于index就wait，小于index就读取队列，并且pivot + 1
 上述即
 消息的消费模型有两种，推送模型（push）和拉取模型（pull）：
 kafka 采用拉取模型，由消费者自己记录消费状态，每个消费者互相独立地顺序拉取每个分区的消息
*/
public class TestPC {

    public static final int MAX_POOL = 10;
    public static Queue<Integer> queue = new ArrayBlockingQueue<>(MAX_POOL);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 5; i++) {
            service.submit(new Producer());
        }
        for (int i = 0; i < 10; i++) {
            service.submit(new Consumer());
        }
    }

}
