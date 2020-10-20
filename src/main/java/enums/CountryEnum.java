package enums;

import lombok.Getter;

/**
 * 六国枚举
 *
 * @author shanmingda
 * @date 2020-10-18 21:28
 */
public enum CountryEnum {

    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "韩"), SIX(6, "魏");

    @Getter private int id;

    @Getter private String name;

    CountryEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CountryEnum getEnum(int index) {

        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if (index == value.getId()) {
                return value;
            }
        }
        return null;
    }


}
