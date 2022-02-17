package mikew.anything

//         A            B      C
//         |            |      |
//         -            |      |
//       -----          |      |
//     ---------        |      |
//   --------------     |      |

// recursive function to solve tower of hanoi puzzle
static void towerOfHanoi(int n,
                         String from_rod,
                         String to_rod,
                         String aux_rod)
{
    if (n == 0)
    {
        return;
    }
    towerOfHanoi(n - 1, from_rod, aux_rod, to_rod)
    System.out.println "Move disk $n from rod $from_rod to rod $to_rod"
    towerOfHanoi(n - 1, aux_rod, to_rod, from_rod)
}

int n = 3; // Number of disks
towerOfHanoi(n, 'A', 'C', 'B')

//----------------------------------------------------------------

def recurseUntil( x ) {
    if ( x == 10 ) {
        return x
    }
    recurseUntil( ++x )
}

println recurseUntil( 0 )