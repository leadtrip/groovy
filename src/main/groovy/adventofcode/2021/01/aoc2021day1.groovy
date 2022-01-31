def depths = new File( "../resources/day1input.txt" ).collect {it.toInteger()}
println depths.indexed().findAll { it.value > depths[it.key-1]}.size()

def depthsCollated = depths.collate(3, 1, false).indexed()
println depthsCollated.findAll{  it.key > 0 && it.value.sum() > depthsCollated[it.key-1].sum() }.size()
