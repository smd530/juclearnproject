class Phone {

    public synchronized void sendEmail() {
        System.out.println("sendEmail");
    }

    public synchronized void sendMsg() {
        System.out.println("sendMsg");

    }
}


/**
 * 对线程8锁
 *
 * @author shanmingda
 * @date 2020-10-10 17:25
 */
public class Lock8 {
}
