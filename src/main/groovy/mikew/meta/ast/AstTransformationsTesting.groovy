package mikew.meta.ast

// class ToiletRollTransformation adds getStatus method to classes annotated with @ToiletRoll
p = new Paper()
assert p.getStatus() == 'spinning'

@ToiletRoll
class Paper {}

// --------------------------------------------------------------------------------------

// class ChemicalSpillageTransformation adds the getCasualtiesExpected method to classes annotated with @ChemicalSpillage
def r = new River()
assert r.getCasualtiesExpected()

assert !new Brook().getCasualtiesExpected()

def c = new Canal()
assert !c.getCasualtiesExpected()

@ChemicalSpillage(isDeadly = true)
class River{}

@ChemicalSpillage(isDeadly = false)
class Brook{}

@ChemicalSpillage
class Canal{}