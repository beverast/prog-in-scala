package chp8FunctionsAndClosures

import scala.io.Source

object ImprovedLongLines {
  def processFile(filename: String, width: Int) = {

    // Local funcs. have equivalent scope to "private" methods
    // Can access parent parameters, can't be accessed publicly
    def processLine(line: String) = {
      if (line.length > width)
        println(s"$filename: ${line.trim}")
    }
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }
}