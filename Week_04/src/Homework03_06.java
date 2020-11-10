import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author jiangchen
 * @date 2020/11/09
 */
public class Homework03_06 {

    private static volatile int result;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CyclicBarrier barrier = new CyclicBarrier(2);
        Thread sub = new Thread(new Sum(barrier));
        sub.start();
        try {
            barrier.await();
            System.out.println("异步计算结果为：" + result);
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    static class Sum implements Runnable {

        private CyclicBarrier barrier;

        public Sum(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                result = fibo(36);
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private static int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }

    }
}
