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

abstract class IntQueue {
  def get(): Int
  def put(x: Int): Unit
}

class BasicIntQueue extends IntQueue {
  import collection.mutable.ArrayBuffer
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) = { buf += x }
}

// This trait declares `IntQueue` as a superclass meaning that only classes
// that also extend `IntQueue` can mix-in Doubling
// Note: an abstract method calling `super` is normally illegal, but for traits this works
// as long as the trait is mixed in after the method has been given a conrete implementation.
// This is a common pattern for traits implement "stackable modifications"
trait Doubling extends IntQueue {
  abstract override def put(x: Int) = { super.put(2 * x) }
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) = { super.put(x + 1) }
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) = { if (x >= 0) super.put(x) }
}

object Traits {
  val frog = new Frog()
  frog.philosophize()

  // A trait also defines a type. `phil` could've been initialized with any object
  // whose class mixes in the `Philosophical` trait.
  val phil: Philosophical = frog
  phil.philosophize()

  class MyQueue extends BasicIntQueue with Doubling
  val queue = new MyQueue
  queue.put(10)
  queue.get() // returns 20

  // Mixing in traits during instantiation
  // Mix order matters! Trait to the right are called first:
  // Filtering's get() will exec before Incrementing's get(), but they BOTH get called. Hence "stacking"
  val queue2 = new BasicIntQueue with Incrementing with Filtering
  queue2.put(10); queue2.put(-1100)
  queue2.get()

}

