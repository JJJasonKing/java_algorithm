package Producer_Consumer;

public class Consumer implements Runnable{

    @Override
    public void run() {
        try {
            while (true) {
                Integer e = (Integer)TestPC.queue.poll();
                System.out.println(Thread.currentThread().getName() + "生产商品:" + e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        while (true) {
            synchronized (TestPC.queue) {
                while (TestPC.queue.isEmpty()) {
                    try {
                        TestPC.queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Good g = TestPC.queue.poll();
                System.out.println(Thread.currentThread().getName() + "消费商品:" + g.getId());
                TestPC.queue.notify();
            }
        }
        */
    }
}

/*
    public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return (count == 0) ? null : dequeue();
        } finally {
            lock.unlock();
        }
    }
 */
