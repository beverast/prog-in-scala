package chp4ClassesAndObjects

class ChecksumAccumulator {
  // Access scope modifier: `private` only allows state update from within the class
  // `public` is Scala's default access modifier, no need to state it explicitly
  // Function parameters are `val` by default to simplify reasoning
  // Methods w/ result type `Unit` (for side-effecting) are referred to as Procedures
  private var sum = 0
  def add(b: Byte): Unit = { sum += b }
  def checksum(): Int = ~(sum & 0xFF) + 1

  // Semicolon inference: semicolons only necessary on multi-statement lines
  val s = "CSA initialized."; println(s)
  // The compiler can infer this just fine
  if (s.length > 2)
    println("`s` length > 2.")
  else
    println("`s` length too small.")
  // Chaining infix operators
  println(
    2 +
    3 +
    4
  )
}

object ChecksumAccumulator {
  import scala.collection.mutable

  private val cache = mutable.Map.empty[String, Int]

  def calculate(s:String): Int = {
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs)
      cs
    }
  }
}