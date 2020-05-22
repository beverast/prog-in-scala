/*
Topics covered:
- Composition & Inheritance
- `abstract` classes
- Parameterless methods
- `extend`-ing classes
- Overriding methods and fields
- Parametric fields
- Invoking superclass constructors
- Polymorphism & dynamic binding
- `final` members and classes
- Factory objects & methods
 */

/*
NOTES:
- If a class contains an abstract, or unimplemented, method, then the class must be declared as abstract.
- Abstract methods do not require the `abstract` modifier. If they have an impl. then they're "concrete" methods.
- Abstract classes cannot be instantiated.

- Style: Keep empty parens on parameterless methods when they have side effects, i.e. println()
- Style: Remove parens when the method doesn't mutate state of have side effects: "hello".length, ie property access
- Never define a method with side effects without parens bc invocation would look like a field selection

- The `extends` clause has 2 effects: the sublcass inherits all Non-Private members of the superclass and
- makes the child class a Subtype of the superclass: ArrayElement is a subclass of Element
- "Inheritance" means 1: private mems are not inherited and 2: if a subclass implements a superclass member, then it is not inherited
- One can say a concrete member "implements" the abstract one
- "Subtyping" means that a value of the subclass can be used wherever the value of a superclass is required

- The `override` modifier is required for all members that override a parent's concrete member
- `override` is optional when a subclass overrides a parent's abstract method

- The `final` modifier prevents a class member from being overridden by a subclass
- `final` as a class modifier prevents a class from being subclassed

 */
package chp10CompositionAndInheritance

import Element.elem

abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length

  def above(that: Element): Element = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    elem(this1.contents ++ that1.contents)
  }

  def beside(that: Element): Element = {
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem(
      for (
        (line1, line2) <- this1.contents zip that1.contents
      ) yield line1 + line2
    )
  }

  def widen(w: Int): Element =
    if (w <= width) this
    else {
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right
    }

  def heighten(h: Int): Element =
    if (h <= height) this
    else {
      val top = elem(' ', width, (h - height) / 2)
      val bot = elem(' ', width, h - height - top.height)
      top above this above bot
    }

  override def toString = contents.mkString("\n")
}

// Factory object, also a singleton object
object Element {

  // A subclass providing a concrete implementation of contents
  // `contents` is an immutable "Parametric Type" here, but it can be mutable with `var` as well
  private class ArrayElement(val contents: Array[String]) extends Element

  // Invoking a superclass constructor
  private class LineElement(s: String) extends Element {
    val contents = Array(s)
    override def width = s.length
    override def height = 1
  }

  private class UniformElement(
                                ch: Char,
                                override val width: Int,
                                override val height: Int
                              ) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }

  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)
  def elem(ch: Char, width: Int, height: Int): Element =
    new UniformElement(ch, width, height)
  def elem(line: String): Element =
    new LineElement(line)
}
