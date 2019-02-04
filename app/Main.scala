package model

object Main extends App {
  println("start")
  val newGame = new Game(Seq(1, 2, 3))
  val turn1 = newGame.start()
  val turn2 = turn1.play(turn1.turnPlayerId, Point(0, 0), new Block(3, 3, Seq(Point(0, 0), Point(0, 1), Point(0, 2), Point(1, 0), Point(2, 0))))
  println("finish")
}
