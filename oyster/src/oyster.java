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

// Heapsort competes with quicksort, another very efficient nearly-in-place sort algorithm
// Quicksort is typically somewhat faster, due to better cache behavior and other factors.
// The worst-case running time for quicksort is makes it unacceptable for large data sets.
// Heapsort relies strongly on random access, and its poor locality of reference requires
// efficient random access to be practical.
// Mergesort is a stable sort, unlike quicksort and heapsort, and can be easily adapted
// to operate on linked lists and very large set on slow media with long access time.
// Heapsort requires only @(1) auxiliary space instead of Mergesort @(n) auxiliary space,
// quicksort with in-place partitioning and tail recursion uses only O(log n) space.

public class oyster {
    public static class ArrayAndStrings {
        // 1.1. Implement an algorithm to determine if a string has all unique characters.
        //      What if you can not use additional data structures?

        // 1.2. Write code to reverse a C-Style String.
        //      (C-String means that “abcd” is represented as five characters, including the null character.)

        // 1.3. Design an algorithm and write code to remove the duplicate characters in a string without using any additional buffer.
        //      NOTE: One or two additional variables are fine. An extra copy of the array is not.
        //      FOLLOW UP: Write the test cases for this method.

        // 1.4. Write a method to decide if two strings are anagrams or not.

        // 1.5. Write a method to replace all spaces in a string with '%20'.

        // 1.6. Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in place?

        // 1.7. Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column is set to 0.

        // 1.8. Assume you have a method isSubstring which checks if one word is a substring of another.
        //      Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring
        //      (i.e., "waterbottle" is a rotation of "erbottlewat").

        // 1-A. Find the max sum sub array.
        //      Kadane's algorithm http://en.wikipedia.org/wiki/Maximum_subarray_problem
        public static int[] findMaxSumSubArrayUsingKadane(int... a) {
            int maxHead = 0, maxTail = 0, maxSum = a[0];
            for (int head = 0, tail = 1, sum = a[0]; tail < a.length; tail++) {
                sum = sum + a[tail];
                if (sum > maxSum) {
                    maxHead = head;
                    maxTail = tail;
                    maxSum = sum;
                }

                if (0 > sum) {
                    head = tail + 1;
                    sum = 0;
                }
            }

            return new int[] { maxSum, maxHead, maxTail };
        }
    }

    public static class LinkedLists {
        // 2.1. Write code to remove duplicates from an unsorted linked list.
        //      FOLLOW UP: How would you solve this problem if a temporary buffer is not allowed?

        // 2.2. Implement an algorithm to find the nth to last element of a singly linked list.

        // 2.3. Implement an algorithm to delete a node in the middle of a single linked list, given only access to that node.
        //      EXAMPLE: Input: the node ‘c’ from the linked list a->b->c->d->e
        //               Result: nothing is returned, but the new linked list looks like a->b->d->e

        // 2.4. You have two numbers represented by a linked list, where each node contains a single digit.
        //      The digits are stored in reverse order, such that the 1's digit is at the head of the list.
        //      Write a function that adds the two numbers and returns the sum as a linked list.
        //      EXAMPLE: Input: (3 -> 1 -> 5), (5 -> 9 -> 2)
        //               Output: 8 -> 0 -> 8

        // 2.5. Given a circular linked list, implement an algorithm which returns node at the beginning of the loop.
        //      DEFINITION: Circular linked list: A (corrupt) linked list in which a node’s next pointer points to an earlier node, so as to make a loop in the linked list.
        //      EXAMPLE: Input: A -> B -> C -> D -> E -> C [the same C as earlier]
        //               Output: C

        public static class SNode<T> implements Comparable<SNode<T>> {
            public T item;
            public SNode<T> next;

            public SNode<T> mergeSort(SNode<T> p) {
                if (null == p || null == p.next)
                    return p;
                SNode<T> q = partition(p);
                p = mergeSort(p);
                q = mergeSort(q);
                p = merge(p, q);
                return p;
            }

            public SNode<T> partition(SNode<T> p) {
                SNode<T> p1 = p;
                SNode<T> p2 = p.next;
                while (null != p2 && null != p2.next) {
                    p2 = p2.next.next;
                    p2 = p1.next;
                }

                SNode<T> q = p1.next;
                p1.next = null;
                return q;
            }

            public SNode<T> merge(SNode<T> p, SNode<T> q) {
                SNode<T> head = null, r = null;
                while (null != p && null != q) {
                    SNode<T> next;
                    if (p.compareTo(q) < 0) {
                        next = p;
                        p = p.next;
                    } else {
                        next = q;
                        q = q.next;
                    }

                    if (null == head) {
                        head = r = next;
                    } else {
                        r.next = next;
                        r = r.next;
                    }
                }

                if (null == p)
                    r.next = q;
                if (null == q)
                    r.next = p;
                return head;
            }

            @Override
            public int compareTo(SNode<T> o) {
                return 0;
            }
        }

        public static class DNode<T> {
            public T item;
            public DNode<T> next;
            public DNode<T> prev;

            public static <T> DNode<T> reverse(DNode<T> current) {
                if (null == current)
                    return current;

                while (null != current.next) {
                    DNode<T> save = current.next;
                    current.next = current.prev;
                    current.prev = save;
                    current = save;
                }

                return current;
            }
        }
    }

    public static class Stacks {
        
    }

    public static class Recursions {
        public static String toExcelColumn(int n) {
            // 26 cases : A - Z
            // 26^2 cases : AA - ZZ
            // 26^3 cases : AAA - ZZZ

            int cases = 26;
            int k = 0;
            for (; n > cases; k++) { // k is the level of recursion.
                n -= cases;
                cases *= 26;
            }

            n -= 1; // n falls between 0 and (26^k - 1).
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= k; i++) {
                sb.insert(0, (char) (n % 26 + 'A'));
                n /= 26;
            }

            return sb.toString();
        }

        public static int fromExcelColumn(String s) {
            int columns = 0;
            for (int k = 1, cases = 26; k < s.length(); k++, cases *= 26) {
                columns += cases;
            }

            for (int k = s.length() - 1, cases = 1; k >= 0; k--, cases *= 26) {
                columns += cases * (int) (s.charAt(k) - 'A');
            }

            return columns + 1;
        }
    }

    public static class Sorting {
        public static void mergeSort(int[] elements) {
            int[] temp = new int[elements.length];
            mergeSort(elements, temp, 0, elements.length - 1);
        }

        private static void mergeSort(int[] input, int[] temp, int left, int right) {
            if (left < right) {
                int center = (left + right) / 2;
                mergeSort(input, temp, left, center);
                mergeSort(input, temp, center + 1, right);
                merge(input, temp, left, center + 1, right);
            }
        }

        private static void merge(int[] input, int[] temp, int left, int right, int rightEnd) {
            int leftEnd = right - 1;
            int leftBegin = left;
            int position = 0;
            while (left <= leftEnd && right <= rightEnd) {
                if (input[left] <= input[right]) {
                    temp[position++] = input[left++];
                } else {
                    temp[position++] = input[right++];
                }
            }

            while (left <= leftEnd) {
                temp[position++] = input[left++];
            }

            while (right <= rightEnd) {
                temp[position++] = input[right++];
            }

            System.arraycopy(temp, 0, input, leftBegin, rightEnd - leftBegin + 1);
        }
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

    public static int weighedChoice(int... weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
        }

        int random = new Random().nextInt(sum);
        for (int i = 0; i < weights.length; i++, random -= weights[i]) {
            if (random < weights[i])
                return i;
        }

        // http://download.oracle.com/javase/1.4.2/docs/guide/lang/assert.html
        throw new RuntimeException("This bug should go unhandled.");
    }

    public static void knuthShuffle(List<Integer> list) {
        Random random = new Random();
        for (int i = 0; i < list.size(); i++) {
            int j = i + random.nextInt(list.size() - i);
            Integer save = list.get(j);
            list.set(j, list.get(i));
            list.set(i, save);
        }
    }

    public static int rand7() {
        int rand21;
        do {
            rand21 = 5 * (rand5() - 1); // 0, 5, ..., 20.
            rand21 += rand5(); // 1, 2, ..., 5.
        } while (rand21 > 21);

        assert rand21 >= 1 && rand21 <= 21;
        return rand21 % 7 + 1; // 1, 2, ..., 7
    }

    public static int rand5() {
        return new Random().nextInt() % 5 + 1;
    }

    public static int indexOutOfCycle(int i, int... args) {
        if (null == args)
            throw new IllegalArgumentException("'args' must be non-null.");
        if (0 == args.length)
            throw new IllegalArgumentException("'args' must not be empty.");

        return indexOutOfCycle(0, args.length - 1, i, args);
    }

    private static int indexOutOfCycle(int left, int right, int i, int... args) {
        int pivot = (left + right) / 2;
        if (i == args[pivot]) {
            return pivot;
        } else if (left == right) {
            return -1;
        } else if (i == args[right]) {
            return right;
        }

        if (args[right] < args[pivot]) {
            if (args[right] < i && i < args[pivot]) {
                return indexOutOfCycle(left, pivot - 1, i, args);
            } else {
                return indexOutOfCycle(pivot + 1, right - 1, i, args);
            }
        } else {
            if (args[pivot] < i && i < args[right]) {
                return indexOutOfCycle(pivot + 1, right - 1, i, args);
            } else {
                return indexOutOfCycle(left, pivot - 1, i, args);
            }
        }
    }

    public static int smallestOutOfCycle(int... args) {
        if (null == args)
            throw new IllegalArgumentException("'args' must be non-null.");
        if (0 == args.length)
            throw new IllegalArgumentException("'args' must not be empty.");

        return smallestOutOfCycle(0, args.length - 1, args);
    }

    private static int smallestOutOfCycle(int left, int right, int... args) {
        if (right == left) {
            return args[left];
        } else {
            int pivot = (left + right) / 2;
            if (args[right] < args[pivot]) {
                return smallestOutOfCycle(pivot + 1, right, args);
            } else {
                return smallestOutOfCycle(left, pivot, args);
            }
        }
    }

    /*
     * Positive test cases: - {1} yields {1}. - {1, 2, 2} yields {2}. - {1, 2,
     * 2, 3, 3, 3} yields {3}. - {2, 2} yields {2}. - {1, 1, 2, 2} yields {1,
     * 2}.
     * 
     * Negative test cases: - null throws NPE. - empty yields empty.
     */
    @Test
    public void testFindModesUsingMap() {
        testFindModes(new Function<int[], List<Integer>>() {
            public List<Integer> apply(int[] input) {
                return findModesUsingMap(input);
            };
        });
    }

    @Test
    public void testFindModesUsingArray() {
        testFindModes(new Function<int[], List<Integer>>() {
            public List<Integer> apply(int[] input) {
                return findModesUsingArray(input);
            };
        });
    }

    public void testFindModes(Function<int[], List<Integer>> findModes) {
        try {
            findModes.apply(null);
            fail("findsModes should have thrown NPE.");
        } catch (AssertionError ae) {
        } catch (NullPointerException npe) {
        } catch (Exception e) {
            fail("findsModes should have thrown NPE.");
        }

        assertEquals(ImmutableList.of(), findModes.apply(new int[0]));

        assertEquals(ImmutableList.of(1), findModes.apply(new int[] { 1 }));
        assertEquals(ImmutableList.of(2), findModes.apply(new int[] { 1, 2, 2 }));
        assertEquals(ImmutableList.of(3), findModes.apply(new int[] { 1, 2, 2, 3, 3, 3 }));

        assertEquals(ImmutableList.of(2), findModes.apply(new int[] { 2, 2 }));
        assertEquals(ImmutableList.of(1, 2), findModes.apply(new int[] { 1, 1, 2, 2 }));
    }

    public static List<Integer> findModesUsingMap(int... numbers) {
        // We assert preconditions; arguments according to assumptions (for
        // debug builds).
        assert null != numbers;

        // We validate arguments if this is a public interface (for debug and
        // retail builds).
        if (null == numbers) {
            throw new NullPointerException("'numbers' must be non-null.");
        }

        // We short-circuit known cases such as an empty set of numbers.
        if (0 == numbers.length) {
            return ImmutableList.of();
        }

        int maximumHits = 1;
        List<Integer> modes = new ArrayList<Integer>();
        Map<Integer, Integer> hitsByNumber = new HashMap<Integer, Integer>();
        for (int number : numbers) {
            if (hitsByNumber.containsKey(number)) {
                hitsByNumber.put(number, 1 + hitsByNumber.get(number));
            } else {
                hitsByNumber.put(number, 1);
            }

            if (hitsByNumber.get(number) >= maximumHits) {
                if (hitsByNumber.get(number) > maximumHits) {
                    maximumHits = hitsByNumber.get(number);
                    modes.clear();
                }

                modes.add(number);
            }
        }

        // We assert postconditions.
        assert !modes.isEmpty();
        return modes;
    }

    public static List<Integer> findModesUsingArray(int... numbers) {
        // We assert preconditions; arguments according to assumptions (for
        // debug builds).
        assert null != numbers;

        // We validate arguments if this is a public interface (for debug and
        // retail builds).
        if (null == numbers) {
            throw new NullPointerException("'numbers' must be non-null.");
        }

        // We short-circuit known cases such as an empty set of numbers.
        if (0 == numbers.length) {
            return ImmutableList.of();
        }

        int min = numbers[0];
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            min = Math.min(min, numbers[i]); // 1, 2, 2, 3, 3, 3
            max = Math.max(max, numbers[i]);
        }

        int maximumHits = 1;
        List<Integer> modes = new ArrayList<Integer>();

        int[] hitsByNumber = new int[max - min + 1]; // 11, 10..20 -> 10 rooms
        for (int number : numbers) {
            hitsByNumber[number - min]++; // {0, 0, ... } => {0, 1, ... }
            if (hitsByNumber[number - min] >= maximumHits) {
                if (hitsByNumber[number - min] > maximumHits) {
                    modes.clear();
                    maximumHits = hitsByNumber[number - min];
                }

                modes.add(number);
            }
        }

        return modes;
    }

    public static void swap(char[] chars, int i, int j) {
        if (chars[i] != chars[j]) {
            chars[i] ^= chars[j];
            chars[j] ^= chars[i];
            chars[i] ^= chars[j];
        }
    }

    public static void swap(int[] ints, int i, int j) {
        if (ints[i] != ints[j]) {
            ints[i] ^= ints[j];
            ints[j] ^= ints[i];
            ints[i] ^= ints[j];
        }
    }

    @Test
    public void testIndexOutOfCycle() {
        assertEquals(4, indexOutOfCycle(30, new int[] { 90, 100, 10, 20, 30, 40, 50, 60, 70, 80 }));
        assertEquals(3, indexOutOfCycle(20, new int[] { 90, 100, 10, 20, 30, 40, 50, 60, 70, 80 }));
        assertEquals(0, indexOutOfCycle(90, new int[] { 90, 100, 10, 20, 30, 40, 50, 60, 70, 80 }));
        assertEquals(-1, indexOutOfCycle(95, new int[] { 90, 100, 10, 20, 30, 40, 50, 60, 70, 80 }));
        assertEquals(5, indexOutOfCycle(40, new int[] { 90, 100, 10, 20, 30, 40, 50, 60, 70, 80 }));
        assertEquals(9, indexOutOfCycle(80, new int[] { 90, 100, 10, 20, 30, 40, 50, 60, 70, 80 }));
        assertEquals(8, indexOutOfCycle(70, new int[] { 90, 100, 10, 20, 30, 40, 50, 60, 70, 80 }));
        assertEquals(-1, indexOutOfCycle(75, new int[] { 90, 100, 10, 20, 30, 40, 50, 60, 70, 80 }));

        assertEquals(4, indexOutOfCycle(70, new int[] { 30, 40, 50, 60, 70, 80, 90, 100, 10, 20 }));
        assertEquals(0, indexOutOfCycle(30, new int[] { 30, 40, 50, 60, 70, 80, 90, 100, 10, 20 }));
        assertEquals(3, indexOutOfCycle(60, new int[] { 30, 40, 50, 60, 70, 80, 90, 100, 10, 20 }));
        assertEquals(-1, indexOutOfCycle(55, new int[] { 30, 40, 50, 60, 70, 80, 90, 100, 10, 20 }));
        assertEquals(5, indexOutOfCycle(80, new int[] { 30, 40, 50, 60, 70, 80, 90, 100, 10, 20 }));
        assertEquals(9, indexOutOfCycle(20, new int[] { 30, 40, 50, 60, 70, 80, 90, 100, 10, 20 }));
        assertEquals(8, indexOutOfCycle(10, new int[] { 30, 40, 50, 60, 70, 80, 90, 100, 10, 20 }));
        assertEquals(-1, indexOutOfCycle(15, new int[] { 30, 40, 50, 60, 70, 80, 90, 100, 10, 20 }));
    }

    @Test
    public void testFindSmallestOutOfCycle() {
        try {
            smallestOutOfCycle(null);
            fail("'numbers' must be non-null.");
        } catch (IllegalArgumentException e) {
        }

        try {
            smallestOutOfCycle(new int[0]);
            fail("'numbers' must not be empty.");
        } catch (IllegalArgumentException e) {
        }

        assertEquals(6, smallestOutOfCycle(new int[] { 6 }));
        assertEquals(6, smallestOutOfCycle(new int[] { 6, 7 }));
        assertEquals(6, smallestOutOfCycle(new int[] { 7, 6 }));
        assertEquals(6, smallestOutOfCycle(new int[] { 38, 40, 55, 89, 6, 13, 20, 23, 36 }));
        assertEquals(6, smallestOutOfCycle(new int[] { 6, 13, 20, 23, 36, 38, 40, 55, 89 }));
        assertEquals(6, smallestOutOfCycle(new int[] { 13, 20, 23, 36, 38, 40, 55, 89, 6 }));
    }

    @Test
    public void testToAndFromExcelColumn() {
        assertEquals("AB", Recursions.toExcelColumn(28));
        assertEquals("ABC", Recursions.toExcelColumn(731));
        assertEquals(28, Recursions.fromExcelColumn(Recursions.toExcelColumn(28)));
        assertEquals(731, Recursions.fromExcelColumn(Recursions.toExcelColumn(731)));
    }
}