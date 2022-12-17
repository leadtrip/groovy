package mikew.anything.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.stream.IntStream;

public class MikeBfs {

    static char[][] TEST_INPUT = {
            {'S', 'a','b','q','p','o','n','m'},
            {'a','b','c','r','y','x','x','l'},
            {'a','c','c','s','z','E','x','k'},
            {'a','c','c','t','u','v','w','j'},
            {'a','b','d','e','f','g','h','i'},
    };

    static char [][] REAL_INPUT = {
            {'a', 'b', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a',},
            {'a', 'b', 'a', 'a', 'c', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a',},
            {'a', 'b', 'a', 'a', 'c', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'c', 'c', 'a', 'a',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'i', 'i', 'i', 'i', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'd', 'd', 'd', 'd', 'd', 'd', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'c', 'c', 'c', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'a', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'h', 'h', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'c', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'h', 'h', 'h', 'i', 'p', 'p', 'p', 'p', 'p', 'i', 'i', 'i', 'i', 'j', 'j', 'j', 'j', 'j', 'j', 'j', 'd', 'd', 'd', 'd', 'a', 'a', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'h', 'h', 'h', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'i', 'i', 'j', 'j', 'j', 'j', 'j', 'j', 'j', 'j', 'j', 'd', 'd', 'e', 'e', 'a', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'h', 'h', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'j', 'j', 'q', 'q', 'q', 'j', 'j', 'j', 'j', 'j', 'e', 'e', 'e', 'a', 'a', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'h', 'h', 'h', 'p', 'p', 'p', 'u', 'u', 'u', 'u', 'p', 'p', 'p', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'j', 'j', 'j', 'e', 'e', 'e', 'a', 'a', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'h', 'h', 'h', 'o', 'p', 'u', 'u', 'u', 'u', 'u', 'u', 'p', 'p', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'j', 'j', 'j', 'e', 'e', 'e', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'h', 'h', 'h', 'h', 'o', 'o', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'q', 'v', 'v', 'v', 'v', 'v', 'q', 'q', 'q', 'j', 'k', 'e', 'e', 'e', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'h', 'h', 'h', 'o', 'o', 'o', 'u', 'u', 'u', 'x', 'x', 'x', 'u', 'v', 'v', 'v', 'v', 'v', 'v', 'q', 'q', 'q', 'k', 'k', 'e', 'e', 'e', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'h', 'h', 'h', 'h', 'o', 'o', 'o', 'u', 'u', 'x', 'x', 'x', 'x', 'u', 'v', 'y', 'y', 'y', 'v', 'v', 'q', 'q', 'q', 'k', 'k', 'e', 'e', 'e', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'j', 'j', 'h', 'o', 'o', 'o', 'o', 'o', 'u', 'u', 'x', 'x', 'x', 'x', 'y', 'y', 'y', 'y', 'y', 'v', 'v', 'q', 'q', 'q', 'k', 'k', 'e', 'e', 'e', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'j', 'j', 'j', 'o', 'o', 'o', 'o', 't', 'u', 'u', 'u', 'x', 'x', 'x', 'x', 'y', 'y', 'y', 'y', 'y', 'v', 'v', 'q', 'q', 'k', 'k', 'k', 'e', 'e', 'e', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'j', 'j', 'j', 'o', 'o', 'o', 'o', 't', 't', 't', 'u', 'x', 'x', 'x', 'x', 'x', 'y', 'y', 'y', 'y', 'v', 'v', 'r', 'r', 'r', 'k', 'k', 'k', 'e', 'e', 'e', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'S', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'j', 'j', 'j', 'o', 'o', 'o', 't', 't', 't', 'x', 'x', 'x', 'E', 'z', 'z', 'z', 'z', 'y', 'y', 'v', 'v', 'v', 'r', 'r', 'r', 'k', 'k', 'k', 'f', 'f', 'f', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'a', 'a', 'c', 'j', 'j', 'j', 'o', 'o', 'o', 't', 't', 't', 'x', 'x', 'x', 'x', 'x', 'y', 'y', 'y', 'y', 'y', 'y', 'v', 'v', 'v', 'r', 'r', 'k', 'k', 'k', 'f', 'f', 'f', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'a', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'j', 'j', 'j', 'o', 'o', 'o', 't', 't', 't', 'x', 'x', 'x', 'x', 'y', 'x', 'y', 'y', 'y', 'y', 'y', 'y', 'w', 'v', 'v', 'r', 'r', 'k', 'k', 'k', 'f', 'f', 'f', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'a', 'a', 'a', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'j', 'j', 'j', 'n', 'n', 'n', 't', 't', 't', 't', 'x', 'x', 'x', 'x', 'y', 'y', 'y', 'y', 'y', 'y', 'y', 'w', 'w', 'w', 'r', 'r', 'k', 'k', 'k', 'f', 'f', 'f', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'a', 'a', 'c', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'a', 'a', 'a', 'c', 'j', 'j', 'j', 'n', 'n', 'n', 'n', 't', 't', 't', 't', 't', 'x', 'x', 'y', 'y', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'r', 'r', 'r', 'l', 'k', 'f', 'f', 'f', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'j', 'j', 'j', 'n', 'n', 'n', 'n', 'n', 't', 't', 't', 't', 'w', 'w', 'y', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'r', 'r', 'r', 'r', 'l', 'l', 'f', 'f', 'f', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'j', 'j', 'j', 'j', 'i', 'n', 'n', 'n', 'n', 't', 't', 't', 'w', 'w', 'w', 'w', 'w', 's', 's', 's', 'r', 'r', 'r', 'r', 'r', 'l', 'l', 'l', 'l', 'f', 'f', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'i', 'i', 'i', 'i', 'i', 'n', 'n', 'n', 'n', 't', 's', 'w', 'w', 'w', 'w', 's', 's', 's', 's', 'r', 'r', 'r', 'r', 'r', 'l', 'l', 'l', 'f', 'f', 'f', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'a', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'i', 'i', 'i', 'i', 'n', 'n', 'n', 's', 's', 's', 'w', 'w', 's', 's', 's', 's', 's', 'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l', 'f', 'f', 'f', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'i', 'i', 'i', 'i', 'n', 'n', 's', 's', 's', 's', 's', 's', 's', 'm', 'm', 'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l', 'f', 'f', 'f', 'a', 'a', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'i', 'i', 'i', 'n', 'n', 'm', 's', 's', 's', 's', 's', 'm', 'm', 'm', 'm', 'l', 'l', 'l', 'l', 'l', 'g', 'g', 'g', 'f', 'f', 'a', 'a', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'i', 'i', 'i', 'm', 'm', 'm', 's', 's', 's', 'm', 'm', 'm', 'm', 'm', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'a', 'a', 'a', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'i', 'i', 'i', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'i', 'i', 'i', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'h', 'h', 'g', 'g', 'g', 'g', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'i', 'i', 'h', 'h', 'm', 'm', 'm', 'm', 'h', 'h', 'h', 'h', 'g', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'h', 'h', 'h', 'h', 'h', 'h', 'h', 'h', 'h', 'h', 'h', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'h', 'h', 'h', 'h', 'h', 'h', 'h', 'h', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c',},
            {'a', 'b', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'a', 'a', 'a', 'a', 'c', 'a', 'c', 'a', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'h', 'h', 'h', 'h', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'a',},
            {'a', 'b', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'a', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a',},
            {'a', 'b', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'a', 'a', 'a', 'a', 'a',}
    };

    static final char START = 'S';
    static final char END = 'E';

    /**
     * Messing around with finding elements in a 2d array.
     * This is related to advent of code 2022 day 12
     */
    public static void main(String[] args) {
        MikeBfs mikeBfs = new MikeBfs();

        char[][] input = REAL_INPUT;

        var justFindRes = mikeBfs.justFind(input, START, END );
        System.out.println("Just find result: " + Arrays.toString(justFindRes));

        var justFindWithRulesRes = mikeBfs.justFindWithRules(input, START, END );
        System.out.println("Just find with rules result: " + Arrays.toString(justFindWithRulesRes));

        var justFindWithNode = mikeBfs.justFindWithNode( mikeBfs.nodeMatrix(input), START, END );
        System.out.println( "Just find with node result: " + justFindWithNode );

        var justFindWithRulesNode = mikeBfs.justFindWithRulesNode( mikeBfs.nodeMatrix(input), START, END  );
        System.out.println( "Just find with rules node result: " + justFindWithRulesNode );

        var findShortestPathNode = mikeBfs.findShortestPathNode( mikeBfs.nodeMatrix(input), END, START );
        System.out.println( "Shortest distance with node result: " + findShortestPathNode );
    }

    JavaNode[][] nodeMatrix( char[][] charMatrix ) {
        JavaNode[][] nm = new JavaNode[charMatrix.length][charMatrix[0].length];
        for( int rowIdx = 0; rowIdx < charMatrix.length; rowIdx++ ) {
            for( int colIdx = 0; colIdx < charMatrix[0].length; colIdx++ ) {
                nm[rowIdx][colIdx] = new JavaNode(rowIdx, colIdx, charMatrix[rowIdx][colIdx]);
            }
        }
        return nm;
    }

    /**
     * Just find the end and stop when found, not implementing any rules
     * @param matrix    - the 2d char array
     */
    public int[] justFind( char[][] matrix, char startValue, char endValue ) {
        int maxRows = matrix.length;      // total rows
        int maxCols = matrix[0].length;   // total cols

        int[][] visited = new int[maxRows][maxCols];

        int[] start = findTarget(startValue, matrix);
        int[] end = findTarget(endValue, matrix);

        System.out.println("start: " + start[0] + "," + start[1] + " - end: " + end[0] + "," + end[1]);

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start[0], start[1]});   // only using the cell row and col

        while (!q.isEmpty()) {
            int[] e = q.poll();

            int tr = e[0];          // current cell row
            int tc = e[1];          // current cell col

            if (!(0 <= tr && tr < maxRows && 0 <= tc && tc < maxCols)) {
                continue; // outside the grid
            }

            if (visited[tr][tc] == 1) {
                continue; // already been here
            }

            if ( endValue == matrix[tr][tc] ) {
                visited[tr][tc] = 9;    // for visualization
                return new int[]{tr, tc};
            }

            visited[tr][tc] = 1;

            q.add(new int[] {tr + 1, tc + 0});  // cell above
            q.add(new int[] {tr - 1, tc + 0});  // cell below
            q.add(new int[] {tr + 0, tc + 1});  // cell right
            q.add(new int[] {tr + 0, tc - 1});  // cell left
        }
        return null;
    }

    /**
     * Just find the given end value this time each cell contains a node
     * @param matrix        - the 2d node array
     * @param startValue      - the starting value
     * @param endValue        - the target value
     */
    private JavaNode justFindWithNode( JavaNode[][] matrix, char startValue, char endValue ) {
        JavaNode start = findTargetNode( startValue, matrix );
        JavaNode end = findTargetNode( endValue, matrix );

        System.out.println("start: " + start.row + "," + start.col + " - end: " + end.row + "," + end.col);

        Queue<JavaNode> q = new ArrayDeque<>();
        q.add( start );

        while (!q.isEmpty()) {
            JavaNode e = q.poll();

            int tr = e.row;          // current cell row
            int tc = e.col;          // current cell col

            if (e.visited) {
                continue; // already been here
            }

            if ( endValue == e.value ) {
                return e;
            }

            e.visited = true;

            addNode(q, matrix, tr+1, tc, null);   // cell above
            addNode(q, matrix, tr-1, tc, null);   // cell below
            addNode(q, matrix, tr, tc+1, null);   // cell right
            addNode(q, matrix, tr, tc-1, null);   // cell left
        }
        return null;
    }

    /**
     * Rules are you cannot move to an adjacent cell unless it's the same height or +1 current height.
     * e.g. a->a and a->b are fine but a->c is not allowed
     * Here we're just finding the endpoint but not recording the shortest distance
     */
    public int[] justFindWithRules( char[][] matrix, char startValue, char endValue ) {
        int maxRows = matrix.length;      // total rows
        int maxCols = matrix[0].length;   // total cols

        int[][] visited = new int[maxRows][maxCols];

        int[] start = findTarget(startValue, matrix);
        int[] end = findTarget(endValue, matrix);

        System.out.println("start: " + start[0] + "," + start[1] + " - end: " + end[0] + "," + end[1]);

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start[0], start[1], start[0], start[1]});

        while (!q.isEmpty()) {
            int[] e = q.poll();

            int tr = e[0];          // current cell row
            int tc = e[1];          // current cell col

            if (!(0 <= tr && tr < maxRows && 0 <= tc && tc < maxCols)) {
                continue; // outside the grid
            }

            if (visited[tr][tc] == 1) {
                continue; // already been here
            }

            if ( endValue == matrix[tr][tc] ) {
                visited[tr][tc] = 9;    // for visualization
                return new int[]{tr, tc};
            }

            int sr = e[2];      // previous/adjacent cell row
            int sc = e[3];      // previous/adjacent cell col
            int newHeight = matrix[tr][tc];    // using a char array so auto conversion to char numerical value
            int oldHeight = matrix[sr][sc];    // as above
            if (!(newHeight >= oldHeight - 1)) {
                continue; // can't go more than one step
            }

            visited[tr][tc] = 1;

            q.add(new int[] {tr + 1, tc + 0, tr, tc});  // cell above
            q.add(new int[] {tr - 1, tc + 0, tr, tc});  // cell below
            q.add(new int[] {tr + 0, tc + 1, tr, tc});  // cell right
            q.add(new int[] {tr + 0, tc - 1, tr, tc});  // cell left
        }
        return null;
    }

    /**
     * Find the given end value with rules this time each cell contains a node
     * Rules are you cannot move to an adjacent cell unless it's the same height or +1 current height.
     * e.g. a->a and a->b are fine but a->c is not allowed
     * We're not recording the shortest distance here
     *
     * @param matrix - the 2d node array
     */
    private JavaNode justFindWithRulesNode( JavaNode[][] matrix, char startValue, char endValue ) {
        JavaNode start = findTargetNode( startValue, matrix );
        JavaNode end = findTargetNode( endValue, matrix );

        System.out.println("start: " + start.row + "," + start.col + " - end: " + end.row + "," + end.col);

        start.prevNode = start;
        Queue<JavaNode> q = new ArrayDeque<>();
        q.add( start );

        while (!q.isEmpty()) {
            JavaNode thisNode = q.poll();

            int tr = thisNode.row;          // current cell row
            int tc = thisNode.col;          // current cell col

            if (thisNode.visited) {
                continue; // already been here
            }

            if ( endValue == thisNode.value ) {
                return thisNode;
            }

            JavaNode prevNode = thisNode.prevNode;
            int newHeight = thisNode.value;
            int oldHeight = prevNode.value;
            if (!(newHeight >= oldHeight - 1)) {
                continue; // can't go more than one step
            }

            thisNode.visited = true;

            addNode(q, matrix, tr+1, tc, thisNode);   // cell above
            addNode(q, matrix, tr-1, tc, thisNode);   // cell below
            addNode(q, matrix, tr, tc+1, thisNode);   // cell right
            addNode(q, matrix, tr, tc-1, thisNode);   // cell left
        }
        return null;
    }

    /**
     * Find the shortest path to the end value with rules and recording shortest distance
     * @param matrix        - the node matrix
     * @param startValue    - the start value
     * @param endValue      - the end value
     */
    private JavaNode findShortestPathNode( JavaNode[][] matrix, char startValue, char endValue ) {
        JavaNode start = findTargetNode( startValue, matrix );
        JavaNode end = findTargetNode( endValue, matrix );

        System.out.println("start: " + start.row + "," + start.col + " - end: " + end.row + "," + end.col);

        start.prevNode = start;
        Queue<JavaNode> q = new ArrayDeque<>();
        q.add( start );

        while (!q.isEmpty()) {
            JavaNode thisNode = q.poll();

            int tr = thisNode.row;          // current cell row
            int tc = thisNode.col;          // current cell col

            if (thisNode.visited) {
                continue; // already been here
            }

            if ( endValue == thisNode.value ) {
                return thisNode;
            }

            JavaNode prevNode = thisNode.prevNode;
            int newHeight = thisNode.value;
            int oldHeight = prevNode.value;
            if (!(newHeight >= oldHeight - 1)) {
                continue; // can't go more than one step
            }

            thisNode.visited = true;

            addNode(q, matrix, tr+1, tc, thisNode);   // cell above
            addNode(q, matrix, tr-1, tc, thisNode);   // cell below
            addNode(q, matrix, tr, tc+1, thisNode);   // cell right
            addNode(q, matrix, tr, tc-1, thisNode);   // cell left
        }

        return null;
    }

    /**
     * Add a node to the queue and silently handle the error if out of bounds
     * @param queue         - the queue to add the node to
     * @param matrix        - the 2d array of nodes
     * @param row           - the row in the matrix to fetch the node from
     * @param col           - the column in the matrix to fetch the node from
     * @param currentNode   - the current node, required if rules are to be followed about adjacent cell heights
     */
    JavaNode addNode( Queue<JavaNode> queue, JavaNode[][] matrix, int row, int col, JavaNode currentNode ) {
        try{
            JavaNode adjacentNode = matrix[row][col];
            if( currentNode != null ) {
                adjacentNode.prevNode = currentNode;
                adjacentNode.distance = currentNode.distance +1;
            }
            queue.add(adjacentNode);
            return adjacentNode;
        }catch (Exception e) {
            return null;
        }
    }

    private static int[] findTarget(char target, char[][] grid) {
        return IntStream.range(0, grid.length).boxed()
                .flatMap(r -> IntStream.range(0, grid[r].length)
                        .filter(c -> grid[r][c] == target)
                        .mapToObj(c -> new int[] {r, c}))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    private static JavaNode findTargetNode(char target, JavaNode[][] grid) {
        return Arrays.stream(grid)
                .flatMap( r -> Arrays.stream(r).filter(n -> n.value == target) )
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    class JavaNode {
        int row;
        int col;
        char value;

        boolean visited;

        Integer distance;
        JavaNode prevNode;

        public JavaNode(int row, int col, char val) {
            this.row = row;
            this.col = col;
            this.value = val;
            this.distance = 0;
        }

        public char getValue() {
            return value;
        }

        public void setValue(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "JavaNode{" +
                    "row=" + row +
                    ", col=" + col +
                    ", value=" + value +
                    ", distance=" + distance +
                    '}';
        }
    }
}
