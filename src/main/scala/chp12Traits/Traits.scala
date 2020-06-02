package chp12Traits

// A trait is much like a `class` but it cannot be passed constructor parameters
trait Philosophical {
  def philosophize() = println("I consume memory, therefore I am!")
}

trait hasLegs
class Animal

// Frog subclasses Animal and mixes in Philosophical and hasLegs
class Frog extends Animal with Philosophical {
  override def toString: String = "green"

  // Trait methods can be overridden
  override def philosophize(): Unit = println("It ain't easy being " + toString + "!")
}

// Scala provides an `Ordered` trait for mixing-in comparison methods such as <, <=, >, >=
// Simply provide the type parameter and define compare()
class Rational(n: Int, d: Int) extends Ordered[Rational] {
  // Return positive Int if `this` is g.t., 0 if they're equal, and negative if l.t.
  def compare(that: Rational): Int = (this.n * that.d) - (this.d * that.n)
}

object Traits {
  val frog = new Frog()
  frog.philosophize()

  // A trait also defines a type. `phil` could've been initialized with any object
  // whose class mixes in the `Philosophical` trait.
  val phil: Philosophical = frog
  phil.philosophize()


}

