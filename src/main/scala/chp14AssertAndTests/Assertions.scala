package chp14AssertAndTests

object Assertions {
  def eq(a: Int, b: Int): Boolean = {
    assert(a.isFinite, "An object or string can be passed with the AssertionError")
    if (a == b) true else false
  } ensuring (b.isFinite) // If ensuring returns `True` then the func body result is passed, else throw AssertionError



}