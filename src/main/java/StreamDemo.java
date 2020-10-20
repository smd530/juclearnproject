import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * TODO
 *
 * @author shanmingda
 * @date 2020-10-14 16:49
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
class User {

    // 连式编程 + 流式计算

    private Integer id;

    private String name;

    private int age;


}

/**
 * 请按照给点数据 找出同时满足以下条件的用户 也即以下条件全部满足
 * 偶数ID
 * 年龄大于24
 * 用户名转为大写
 * 用户名字字母倒排序
 * 只输出一个用户名字
 */
public class StreamDemo {

    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 25);
        User u4 = new User(14, "d", 26);
        User u5 = new User(16, "e", 27);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream().filter(u -> {
            return u.getId() % 2 == 0;
        }).filter(user -> {
            return user.getAge() > 24;
        }).map(user -> {
            return user.getName().toUpperCase();
        }).sorted((o1, o2) -> {
            return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);

        // 把花括号去掉 可以去掉return
    }

}


