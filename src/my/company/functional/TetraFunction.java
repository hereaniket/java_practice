package my.company.functional;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
public interface TetraFunction<A,B,C, D> {
    D apply(A a, B b, C c);
}

class FunctionInvoker {
    public static void main(String[] args) {

        Predicate<Boolean> p = num -> false;
        Consumer<Integer> consumer = num -> {
            num.byteValue();
        };

        Supplier<String> supplier = () -> "Aniket";



        Predicate<Integer> btf = n -> n > 5;

        TetraFunction function = (TetraFunction) (a,b,c) -> invokeThisFunction((int)a, (int) b, (int) c);
        int result = (int)function.apply(10,20,30);
        System.out.println(result);

        btf.test(10);
        supplier.get();
    }

    public static int invokeThisFunction(int a, int b, int c){
        return (a+b+c);
    }
}
