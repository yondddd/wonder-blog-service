//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yond.blog.support.extension.ordered;

import com.yond.blog.support.extension.annotation.Activation;
import com.yond.blog.support.extension.annotation.Order;

import java.util.Comparator;

public class OrderedManager {
    public static Comparator COMPARATOR = getComparator();
    public static Comparator ACTIVATION_COMPARATOR = getActivationComparator();

    public OrderedManager() {
    }

    public static <T> Comparator<T> getComparator() {
        Comparator<T> comparator = (o1, o2) -> {
            Order order1 = o1.getClass().getAnnotation(Order.class);
            boolean o1HashOrder = false;
            boolean o2HashOrder = false;
            int value1 = -2147483648;
            if (order1 != null) {
                o1HashOrder = true;
                value1 = order1.value();
            }

            Order order2 = o2.getClass().getAnnotation(Order.class);
            int value2 = -2147483648;
            if (null != order2) {
                o2HashOrder = true;
                value2 = order2.value();
            }

            if (o1HashOrder && o2HashOrder) {
                if (value1 == value2) {
                    return 0;
                } else {
                    return value1 < value2 ? -1 : 1;
                }
            } else if (o1 instanceof Comparable comparable1 && o2 instanceof Comparable comparable2) {
                return comparable1.compareTo(comparable2);
            } else if (value1 == value2) {
                return 0;
            } else {
                return value1 < value2 ? -1 : 1;
            }
        };
        return comparator;
    }

    public static <T> Comparator<T> getActivationComparator() {
        Comparator<T> comparator = (o1, o2) -> {
            Activation p1 = o1.getClass().getAnnotation(Activation.class);
            Activation p2 = o2.getClass().getAnnotation(Activation.class);
            if (p1 == null) {
                return 1;
            } else {
                return p2 == null ? -1 : p1.sequence() - p2.sequence();
            }
        };
        return comparator;
    }
}
