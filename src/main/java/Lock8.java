import java.util.concurrent.TimeUnit;

class Phone {

    public synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendEmail");
    }

    public synchronized void sendMsg() {
        System.out.println("sendMsg");
    }

    public void hello() {
        System.out.println("hello");
    }
}


/**
 * 多线程8锁
 *
 * 1.标准访问 请问先打印邮件还是短信：邮件
 * 2.邮件方法暂停4秒 请问先打印邮件还是短信：邮件
 * 3.新增一个普通方法hello() 请问先打印邮件还是hello：hello
 * 4.两部手机先打印短信还是邮件：短信
 * 5.两个静态同步方法 同一部 先打印短信还是邮件：邮件
 * 6.两个静态同步方法 两部手机 先打印短信还是邮件：邮件
 * 7.一个普通同步方法 一个静态同步方法 1部手机 先打印邮件还是短信：短信
 * 8.一个普通同步方法 一个静态同步方法 2部手机 先打印邮件还是短信：邮件
 *
 * @author shanmingda
 * @date 2020-10-10 17:25
 */
public class Lock8 {

    public static void main(String[] args) {

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "A").start();

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
//            phone.hello();
            phone2.sendMsg();
        }, "B").start();


    }

}
