object Main extends App {
  def generateList(n: Int): List[Int] = {
    (0 to n).toList
  }

  val list = generateList(10)
  list.filter(_ % 2 == 0).map(_ * 2).foreach(println)
}
