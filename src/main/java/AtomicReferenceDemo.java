import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Customer {
    private int id;

    private int age;
}

/**
 * 原子引用
 *
 * @author shanmingda
 * @date 2020-10-15 23:57
 */
public class AtomicReferenceDemo {

    public static synchronized void main(String[] args) {

        Customer customer1 = new Customer(1, 23);
        Customer customer2 = new Customer(2, 24);
        AtomicReference<Customer> atomicReference = new AtomicReference();
        atomicReference.set(customer1);
        boolean b = atomicReference.compareAndSet(customer1, customer2);
        System.out.println(b);
        System.out.println(atomicReference.get());

    }
}
