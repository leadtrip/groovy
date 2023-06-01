package mikew.adventOfCode._2022._12;

import java.util.*;

class Aoc2022day12 {

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

    public static void main(String[] args) {
        Aoc2022day12 aoc2022day12 = new Aoc2022day12();
        aoc2022day12.part1();   // 412
        aoc2022day12.part2();   // 402
    }

    private void part1() {
        var matrix = nodeMatrix(REAL_INPUT);
        Node start = findTargetNode(END, matrix);
        Node end = findTargetNode(START, matrix);
        var findShortestPathNode = findShortestPath(matrix, start, end);
        System.out.println("Shortest distance: " + findShortestPathNode);

    }

    private void part2() {
        var shortest = Arrays.stream( nodeMatrix(REAL_INPUT))
                .flatMap(r -> Arrays.stream(r).filter(n -> n.value == 'a'))
                .map( node -> findShortestPath( nodeMatrix(REAL_INPUT), findTargetNode(END, nodeMatrix(REAL_INPUT)), node ) )
                .filter( integer -> integer != -1 )
                .min(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);

        System.out.println("Shortest distance from any a: " + shortest );
    }

    /**
     * Convert char array to Node array
     * @param charMatrix    - the char array to convert
     */
    Node[][] nodeMatrix(char[][] charMatrix) {
        Node[][] nm = new Node[charMatrix.length][charMatrix[0].length];
        for (int rowIdx = 0; rowIdx < charMatrix.length; rowIdx++) {
            for (int colIdx = 0; colIdx < charMatrix[0].length; colIdx++) {
                nm[rowIdx][colIdx] = new Node(rowIdx, colIdx, charMatrix[rowIdx][colIdx]);
            }
        }
        return nm;
    }

    private int findShortestPath(Node[][] matrix, Node start, Node end) {
        start.prevNode = start; // assign prev node same as start node to get going
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);

        int[][] distance = new int[matrix.length][matrix[0].length];
        for (int[] r : distance) {
            Arrays.fill(r, -1);
        }

        while (!q.isEmpty()) {
            Node thisNode = q.poll();

            if (thisNode.visited) {
                continue;
            }

            if (!thisNode.canMove()) {
                continue;
            }

            distance[thisNode.row][thisNode.col] = thisNode.distance;

            thisNode.visited = true;

            addNode(q, matrix, thisNode.row + 1, thisNode.col, thisNode);   // above
            addNode(q, matrix, thisNode.row - 1, thisNode.col, thisNode);   // below
            addNode(q, matrix, thisNode.row, thisNode.col + 1, thisNode);   // right
            addNode(q, matrix, thisNode.row, thisNode.col - 1, thisNode);   // left
        }

        return distance[end.row][end.col];
    }

    /**
     * Add a node to the queue and silently handle the error if out of bounds
     *
     * @param queue       - the queue to add the node to
     * @param matrix      - the 2d array of nodes
     * @param row         - the row in the matrix to fetch the node from
     * @param col         - the column in the matrix to fetch the node from
     * @param currentNode - the current node, required if rules are to be followed about adjacent cell heights
     */
    void addNode(Queue<Node> queue, Node[][] matrix, int row, int col, Node currentNode) {
        try {
            Node adjacentNode = matrix[row][col];
            adjacentNode.prevNode = currentNode;
            adjacentNode.distance = currentNode.distance + 1;
            queue.add(adjacentNode);
        } catch (Exception ignored) {}
    }

    static Node findTargetNode(char target, Node[][] grid) {
        return Arrays.stream(grid)
                .flatMap(r -> Arrays.stream(r).filter(n -> n.value == target))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    static Node findNodeByCoords( int r, int c, Node[][] matrix ) {
        return Arrays.stream(matrix)
                .flatMap(row -> Arrays.stream(row).filter(n -> n.row == r && n.col == c))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    class Node {
        int row;
        int col;
        char value;
        boolean visited;
        Integer distance;
        Node prevNode;

        Node(int row, int col, char val) {
            this.row = row;
            this.col = col;
            this.value = val;
            this.distance = 0;
        }

        Integer getDistance() {
            return distance;
        }

        int actualValue() {
            return value == 'S' ? 'a' : value == 'E' ? 'z' : value;
        }

        boolean canMove() {
            int newHeight = actualValue();
            int oldHeight = prevNode.actualValue();
            return newHeight >= oldHeight - 1;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "row=" + row +
                    ", col=" + col +
                    ", value=" + value +
                    ", distance=" + distance +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return row == node.row && col == node.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}