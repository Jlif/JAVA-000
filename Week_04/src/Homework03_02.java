import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author jiangchen
 * @date 2020/11/09
 */
public class Homework03_02 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ExecutorService executorService = new ThreadPoolExecutor(16, 32, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1000));
        CountDownLatch latch = new CountDownLatch(1);
        Future<Integer> future = executorService.submit(new Sum(latch));
        executorService.shutdown();
        try {
            latch.await(5, TimeUnit.SECONDS);
            System.out.println("异步计算结果为：" + future.get());
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Sum implements Callable<Integer> {

        private final CountDownLatch latch;

        public Sum(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public Integer call() {
            latch.countDown();
            return fibo(36);
        }

        private static int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }
    }
}
