package Producer_Consumer;

import java.util.Random;


public class Producer implements Runnable{
    private Good good;
    public Producer() {

    }
    public Producer(int id, String name) {
        good = new Good(id, name);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Random random = new Random();
                int i = random.nextInt();
                TestPC.queue.offer(i);
                System.out.println(Thread.currentThread().getName() + "生产商品:" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        synchronized (TestPC.queue) {
            while (TestPC.queue.size() >= TestPC.MAX_POOL) {
                try {
                    TestPC.queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            TestPC.queue.offer(good);
            System.out.println(Thread.currentThread().getName() + "生产商品:" + good.getId());
            TestPC.queue.notify();
        }*/

    }
}
/*
    public boolean offer(E e) {
        Objects.requireNonNull(e);
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (count == items.length)
                return false;
            else {
                enqueue(e);
                return true;
            }
        } finally {
            lock.unlock();
        }
    }
 */