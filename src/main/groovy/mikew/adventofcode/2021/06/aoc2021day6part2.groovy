def input = new File( "../resources/day6input.txt" ).eachLine{it.split ( ',' ) }.collect { it.toInteger()}

def ageList = (0..8).collect{ day -> input.findAll{ age -> age == day }.size() }
println ageList

def numberOfDays = 256

numberOfDays.times {
    def born = 0
    (0..8).each { day ->
        if ( day == 0 )
            born = ageList[day]
        else
            ageList[day-1] = ageList[day]
    }
    ageList[8] = born
    ageList[6] = ( ageList[6] + born )
}

println ageList.sum()
/*long noOfFish = countFish(80, input);
System.out.println("After 80 days: " + noOfFish);
noOfFish = countFish(256, input);
System.out.println("After 256 days: " + noOfFish);*/

long countFish(int numberOfDays, List<Integer> lanternFish) throws FileNotFoundException {

    Map<Long, Long> fishMapping = new HashMap<>();
    for (long i = 0; i <= 8; i++) {
        final long day = i;
        fishMapping.put(day, lanternFish.stream().filter(val -> val == day).count());
    }

    for (int i = 0; i < numberOfDays; i++) {
        long newFish = 0;
        for (long day = 0; day <= 8; day++) {
            if (day == 0) {
                newFish = fishMapping.get(day);
            } else {
                fishMapping.put(day - 1, fishMapping.get(day));
            }
        }
        fishMapping.put(8L, newFish);
        fishMapping.put(6L, fishMapping.get(6L) + newFish);
    }
    return fishMapping.values().stream().mapToLong(Long::longValue).sum();
}