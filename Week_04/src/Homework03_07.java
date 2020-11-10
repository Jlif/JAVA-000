/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author jiangchen
 * @date 2020/11/09
 */
public class Homework03_07 {

    private static volatile int result;
    private static volatile boolean flag = true;
    private static final Object obj = new Object();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new Thread(new Sum(obj)).start();

        try {
            synchronized (obj) {
                flag = false;
                obj.wait();
                System.out.println("异步计算结果为：" + result);
                System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Sum implements Runnable {

        private final Object obj;

        public Sum(Object obj) {
            this.obj = obj;
        }

        @Override
        public void run() {
            while (flag) {
                Thread.yield();
            }
            synchronized (obj) {
                result = fibo(36);
                obj.notifyAll();
            }
        }

        private static int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }

    }
}
