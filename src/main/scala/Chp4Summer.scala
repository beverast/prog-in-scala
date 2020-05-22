import chp4ClassesAndObjects.ChecksumAccumulator

object Chp4Summer {
  def main(args: Array[String]) = {
    for (arg <- args)
      println(arg + ": " + ChecksumAccumulator.calculate(arg))
  }
}