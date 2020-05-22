class Hello {
  println("Hello, universe. This is a scipt.")

  def loop_good(n: Int): Unit = {
    var i = 0
    while (i < n) {
      print(i + " ")
      i += 1
    }
  }

  def loop_better(n: Int): Unit = {
    val n_list = (0 to n).toList
    n_list.foreach(num => print(num + " "))
  }

  def loop_best(n: Int): Unit = {
    for (i <- 0 to n) print(i + " ")
  }
}
