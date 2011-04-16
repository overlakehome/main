import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

public class oyster2 {
    public static class Arrays {

    }

    public static class Strings {
        
    }

    public static class LinkedLists {
        public static class SNode<T> implements Comparable<SNode<T>> {
            public T item;
            public SNode<T> next;

            @Override
            public int compareTo(SNode<T> o) {
                return 0;
            }
        }

        public static class DNode<T> {
            public T item;
            public DNode<T> next;
            public DNode<T> prev;
        }
    }

    public static class Stacks {
        
    }

    public static class Recursions {

    }

    public static class Sorting {

    }

    public static class BNode<T extends Comparable<T>> implements Comparable<BNode<T>> {
        public T item;
        public BNode<T> left;
        public BNode<T> right;
        public BNode<T> parent;

        public static <T extends Comparable<T>> BNode<T> of(BNode<T> parent) {
            BNode<T> node = new BNode<T>();
            node.parent = parent;
            return node;
        }

        public static <T extends Comparable<T>> BNode<T> of(T item, BNode<T> left, BNode<T> right) {
            BNode<T> node = new BNode<T>();
            node.item = item;
            node.left = left;
            node.right = right;
            return node;
        }

        @Override
        public int compareTo(BNode<T> o) {
            return this.item.compareTo(o.item);
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }
}