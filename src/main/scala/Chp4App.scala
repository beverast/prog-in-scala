import chp4ClassesAndObjects.ChecksumAccumulator

object FallWinterSpring extends App {
  for (season <- List("fall", "winter", "spring"))
    println(season + ": " + ChecksumAccumulator.calculate(season))
}