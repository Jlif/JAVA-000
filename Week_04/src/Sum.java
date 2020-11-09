import java.util.concurrent.Callable;

/**
 * @author jiangchen
 * @date 2020/11/09
 */
public class Sum implements Callable<Integer> {

    @Override
    public Integer call() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
