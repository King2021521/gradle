package com.zxm.gradle.utils;

import java.util.Collection;
import java.util.function.Function;

/**
 * @Author zhangxiaomin
 * @description 通用工具类
 */
public class CommonUtils {
    /**
     * 判断两个集合中的元素是否有相同值
     *
     * @param c1
     * @param f1
     * @param c2
     * @param f2
     * @param <T1>
     * @param <T2>
     * @param <R1>
     * @param <R2>
     * @return
     */
    public static <T1, T2, R1, R2> Boolean existSame(Collection<T1> c1, Function<T1, R1> f1, Collection<T2> c2, Function<T2, R2> f2) {
        return c1.stream().anyMatch(t1 -> c2.stream().anyMatch(t2 -> f1.apply(t1).equals(f2.apply(t2))));
    }
}
