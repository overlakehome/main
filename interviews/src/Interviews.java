import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Interviews {
    public static class Feburary2011AamazonInterview {
        // There were 4 interviewers in a day loop; 40 minutes are assigned each interviewer.
        // 3 of interviewers challenged Erin with 6 technical problems to design, code, and test.
        // 1 of interviewers challenged Erin with 3+ behavioral interview questions, e.g. resolving conflicts.

        // [Mike] Can you write me a program that returns the number of words in a string?
        // [Erin] Can I use the white board to take notes about this problem? [Mike] go ahead.
        // [Erin] First of all, I like to clarify functional requirements (cases of input and output)
        // What happens if the input is 'null'? What about an empty "" string?
        // 15+ questions worked out a bunch of input and output cases as follows: 
        // null: error out, "": 0, " a ": 1, " aa ": 1, " a b ": 2, and the like.
        // [Erin] What are delimiters? [Mike] ' ' (a space) [Erin] so, we assume, tabs and crLF yield words.
        // 
        // [Erin] Moving on, I like to clarify non-functional requirements (irrelevant to I/O)
        // Performance (time & space), security, parallel executions, s/w & h/w platforms (OS, CPU w/ L2 cache, memory)
        // Will a linear-time, constant-space algorithm work for you? [Mike] Yes, it will.
        // How big the string is? Does the string fits all in RAM, or not? Should it be partially loaded from disk?
        // How many CPUs and computers do we use? Can we use String#split function? [Mike] Let's assume that we cannot.
        // 
        // [Erin] How to indicate an error given these options?
        // to return errors, throw exceptions, set out parameters, or side effects into fields. [Mike] Your choice.
        //
        // ** side notes on Erin's standard procedures:
        // 1. to specify functionalities and non-functionalities with 15 to 30 clarifying questions.
        //    classical input and output cases, and interesting cases,
        //    boundary conditions, legal and illegal input cases, and error indications,
        //    constraints: time & space, performance, security, configuration, concurrent executions, and the like.
        // 2. to agree on function signatures and then the algorithms before you begin writing any code.
        // 3. to validate input argument, and short-circuit cases for null, empty string or collections.
        // 4. to make notes on time and space complexities in-between iterations as follows:
        public static int getWordCount(String s) {
            if (null == s) throw new IllegalArgumentException("'s' must be non-null.");
            if ("".equals(s)) return 0;

            // [Mike] what is the time and space complexity?
            // [Erin] time complexity: O(n), space complexity: O(1)
            // [Mike] can you make it better?
            // [Erin] No as there is an essential complexity where we must scan the chars in the string.
            int count = 0;
            char previous = ' ';
            for (int i = 0; i < s.length(); i++) {
                if (' ' == previous && ' ' != s.charAt(i)) {
                    count++;
                }

                previous = s.charAt(i);
            }

            return count;
        }

        // [Mike] Design test cases for your algorithm
        // [Erin] I would do positive and negative cases.
        // positive cases (also called verification tests):
        // "", " ", "  ", "aa", "  aa", "aa  ", "  ab  cd", "ab  cd  ", and the like.
        // negative cases (also called falsification tests):
        // 'null' should return an exception.
        //
        // ** side notes on Erin's standard procedures: 
        // - do not forget to note why you design test cases this way, BVA, EQ-partitioning, and PICT.
        // - do not forget to note on condition coverage, path coverage, quality risks, and FMEA.
        @Test
        public void testGetWordCount() {
            try {
                assertEquals(0, getWordCount(null));
                fail("getWordCount(null) must throw IllegalArgumentException.");
            } catch (IllegalArgumentException e) {
            }

            // 0 alternation
            assertEquals(0, getWordCount(""));
            assertEquals(0, getWordCount("  "));
            assertEquals(1, getWordCount("ab"));

            // 1 alternation
            assertEquals(1, getWordCount("  ab"));
            assertEquals(1, getWordCount("ab  "));

            // 2 alternations
            assertEquals(1, getWordCount("  ab  cd"));
            assertEquals(2, getWordCount("ab  cd  "));

            // [Erin] I assume that further iterations belong to EQ classes of 0, 1, and 2 iterations.
        }

        // [Mike] Find a string is anagram of another? [Erin] What is it?
        // [Erin] Does this function signature make sense to you? [Mike] That totally makes sense to me.
        // [Erin] What are the time and space requirements? [Mike] I like your best algorithm.
        // [Erin] Let me give you alternatives with pros and cons.
        // One approach is to keep a map where the key is a distinct char,
        // and the value is the number of occurrences of the char.
        // Count up occurrences of each char of 's', and then down with 't'.
        // Then, in the end, we must see all zeros in the map of occurrences.
        // This algorithm runs with time: O(n), and space: O(n).
        // [Erin] Another way is to sort chars in s and t and compare them.
        // Time: n*log(n) for quick-sort, merge-sort, and heap-sort.
        // Space (stack depth): log(n) for quick-sort with randomization, and tail-recursion.
        // [Erin] The other way is to generate permutations of 's' and see if they contains t.
        // Time & Space: this factorial algorithm, O(n!) is worse than exponential algorithms such as O(2^^n).
        public static boolean isAnagram(String s, String t) {
            if (s == t) return true;
            if (null == s || null == t) return false;
            if (s.length() != t.length()) return false;

            // TODO: code the approach with a occurrence counting map.
            throw new NotImplementedException();
        }

        // ** Side-talks with Mike: map, hash functions, resolving collisions, designing hash functions.
        // [Mike] why your algorithm is linear? i.e. why your hash map has constant time operations?
        // [Erin] I assume that we use well designed hash function. blah, bash, bash, Java's String hash uses Multiply 31.
        // [Mike] How would you design a hash function for 26 letters (lower case 'a' - 'z').
        // [Erin] Multiply by 29, that is a prime number greater than 26; I don't have Ph. D on number theories, do you?

        // [Todd] Write a function that returns a number that is occurring odd number of times in an integer array.
        // ** like usual, specified functionalities & non-functionalities, explored alternatives w/ trade-offs.
        // the alternatives: 
        // - counting occurrences on map, and then scanning (time & space: linear)
        // - alternating bit-array, and then scanning (same as the counting map)
        // - sorting & scanning (time: n*log(n), space: log(n) stack depth)
        public static int findOddOccuringNumber(int[] ia) {
            throw new NotImplementedException();
        }

        // [Todd] Serialize and de-serialize a binary tree into a string; this problem is too big to solve in 20 minutes.
        // ** like usual, specified functionalities & non-functionalities; described approach w/ infix and postfix expressions.
        // [Todd] Serialization seems too straightforward w/ your approach; let us move on to implementing de-serialization.
        // ** talks on deriving recurrence relationship; spent 5+ minutes wrestling with base and recursive cases.
        // Erin turned down Sam's suggestion in the middle, as Sam's approach is to store a complete binary tree with nulls.
        // Todd agreed w/ Erin that a sparse binary tree uses up a memory that is 2 to the power of height at the worst case.
        public static void fromStrings(BNode<Character> root, String inorder, String preorder, int left, int right) {
            root.data = preorder.charAt(left);
            int pivot = inorder.indexOf(root.data);
            int r = left + 1;
            for (; r <= right && -1 == inorder.indexOf(preorder.charAt(r), pivot + 1); r++) {
            }

            if (left + 1 <= r - 1) {
                fromStrings(root.left = BNode.of(root), inorder, preorder, left + 1, r - 1);
            }

            if (r <= right) {
                fromStrings(root.right = BNode.of(root), inorder, preorder, r, right);
            }
        }

        //        a
        //     b      d
        //  c       e   g
        //        f       h
        //
        // preorder: a b c d e f g h
        // inorder : c b a f e d g h
        // 
        // ** side-notes: Erin verified the dev code w/ a test case when she came home after the interview as follows:
        @Test
        public void testFromStrings() {
            // the infix and prefix are given as a serialized form of a tree.
            BNode<Character> root = new BNode<Character>();
            fromStrings(root, "cbafedgh", "abcdefgh", 0, 7);

            assertEquals('a', root.data.charValue());
            assertEquals('b', root.left.data.charValue());
            assertEquals('d', root.right.data.charValue());
            assertEquals('c', root.left.left.data.charValue());
            assertEquals(null, root.left.right);
            assertEquals('e', root.right.left.data.charValue());
            assertEquals('g', root.right.right.data.charValue());
            assertEquals('f', root.right.left.left.data.charValue());
            assertEquals(null, root.right.left.right);
            assertEquals(null, root.right.right.left);
            assertEquals('h', root.right.right.right.data.charValue());
        }

        // [Naga] Find if a directed acyclic graph (DAG) has a cycle or not.
        // ** there were specification, class designs of Edge, Graph, and then DFS.
        public boolean hasCycle() {
            try {
                dfs();
                return false;
            } catch (IllegalStateException e) {
                return true;
            }
        }

        public void dfs() {
            // TODO:
            // - set and clear a flag on 'processed' boolean array when you enter and leave a node.
            // - throw an illegal state exception if 'processed' is true when crossing to child nodes.
            // ** side note: there was a logical bug on one line, and worked out the fix w/ Naga's help.

            throw new IllegalStateException("This is not a tree as there is a cyle");
        }

        // [Naga] Design classes for a Tetris game
        // Originally, Erin was asked to design classes for expressions such as (1 * 2 + 3 ** 4).
        // There was no need to implement any functions, but the overall class design was expected.
        // ** some clarifications, and then a number of classes, enums, fields, and methods.
        public static interface TetrisEngine {
            // TODO:
            // fields such as currentPiece, queuedPieces, boardArray, gameState
            //   gamesPlayed, bestScores, currentLevel, currentScore, currentSpeed.
            // methods: setUp, takeDown, clear, movePiece, updateState, removeLines.
        }

        public static interface TetrisPiece {
            // fields such as location, direction, shape, and color.
        }

        public static enum TetrisShape {
            Line, Square, ZigZag, ZagZig, L, InverseL
        }
    }

    public static class BNode<T extends Comparable<T>> {
        public T data;
        public BNode<T> left;
        public BNode<T> right;
        public BNode<T> parent;

        public static <T extends Comparable<T>> BNode<T> of(BNode<T> parent) {
            BNode<T> node = new BNode<T>();
            node.parent = parent;
            return node;
        }
    }
}
