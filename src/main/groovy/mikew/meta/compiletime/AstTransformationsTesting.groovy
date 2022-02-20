package mikew.meta.compiletime

// NOTE REBUILD MODULE IF YOU GET MESSAGE ABOUT BEING UNABLE TO FIND THE **Transformation classes

// class ToiletRollTransformation adds getStatus method to classes annotated with @ToiletRoll
p = new Paper()
assert p.getStatus() == 'spinning'

@ToiletRoll
class Paper {}

// --------------------------------------------------------------------------------------

// class ChemicalSpillageTransformation adds the getCasualtiesExpected method to classes annotated with @ChemicalSpillage
def p = new Puddle()
assert p.getCasualtiesExpected()

assert !new Brook().getCasualtiesExpected()

def c = new Canal()
assert !c.getCasualtiesExpected()

@ChemicalSpillage(isDeadly = true)
class Puddle{}

@ChemicalSpillage(isDeadly = false)
class Brook{}

@ChemicalSpillage
class Canal{}