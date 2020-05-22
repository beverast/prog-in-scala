package chp10CompositionAndInheritance

class Cat {
  val dangerous: Boolean = false
}

class Tiger(
             override val dangerous: Boolean,
             private var age: Int,
           ) extends Cat
