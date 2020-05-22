class Greetings {

  def arrayLoop(): Unit = {
    // Arrays are mutable by default
    val greetString = new Array[String](3)
    greetString(0) = "Hello"
    greetString(1) = ", "
    greetString(2) = "Universe."

    for (i <- 0 to 2)
      print(greetString(i))
    println()

    val numNames = Array.apply("zero", "one", "two")
  }


  def lists(): Unit = {
    val oneTwo = List(1, 2)
    val threeFour = List(3, 4)
    val oneTwoThreeFour = oneTwo ::: threeFour  // ::: is concatenation
    println(oneTwo + " and " + threeFour + " were not mutated.")
    println("Thus, " + oneTwoThreeFour + " is a new List.")

    val twoThree = List(2, 3)
    val oneTwoThree = 1 :: twoThree // :: is 'cons', prepend elem. to front of list
    print(oneTwoThree)

    val twoThreeFour = 2 :: 3 :: 4 :: Nil // Nil can be shorthand for an empty List
    // Append method doesn't exist bc of op. complexity
    // Instead, prepend then call Reverse or use ListBuffer then call toList
  }

  def tuples(): Unit = {
    // Like lists: immutable by default
    // Unlike lists: Can hold multiple types
    val pair = (99, "Luftballons")
    println(pair._1)
    println(pair._2)
  }

  def setsAndMaps(): Unit = {
    // Sets are default immutable, you can `import scala.collection.mutable` though
    // jetSet is `var` bc its immutable, so modification == reassigning
    var jetSet = Set("Boeing", "Airbus")
    jetSet += "Lear"
    println(jetSet.contains("Cessna"))

    // movieSet is `val` because its mutable, so modification == no reassignment
    import scala.collection.mutable
    val movieSet = mutable.Set("Hitch", "Poltergeist")
    movieSet += "Shrek"
    println(movieSet)

    // Explicit type parameterization is required for an empty factory method
    val treasureMap = mutable.Map[Int, String]()
    treasureMap += (1 -> "Go to island")
    treasureMap += (2 -> "Find the big X on the ground.")
    treasureMap += (3 -> "Dig.")
    println(treasureMap(2))

    // Immutable Maps are default, no import necessary
    val romanNumeral = Map(
      1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V"
    )
    println(romanNumeral(4))
  }

  def readFile(file: String): Unit = {
    import scala.io.Source
    if (!file.isEmpty) {
      for (line <- Source.fromFile(file).getLines())
        println(line.length.toString + " " + line)
    }
    else
      Console.err.println("Please enter filename.")
  }
}