package domain

import scala.util.Random

class Game(val playerIds: Seq[Int], val turnPlayerId: Int = 0, val turnCount: Int = 1, val passCount: Seq[Int], val board: Board) {
  def start(): Game = {
    val order = Random.shuffle(playerIds)
    new Game(order, order.head, turnCount, Seq.fill(playerIds.size)(0), board)
  }

  def play(leftTop: Point, block: Block): Game = {
    val put = board.put(turnPlayerId, leftTop, block)
    if (board == put) {
      val turnPlayerIdIndex = playerIds.indexOf(turnPlayerId)
      new Game(
        playerIds,
        if (turnPlayerIdIndex == playerIds.size) playerIds.head else playerIds(turnPlayerIdIndex + 1),
        turnCount + 1,
        passCount,
        board)
    } else {
      this
    }
  }

  def pass(): Game = {
    val turnPlayerIdIndex = playerIds.indexOf(turnPlayerId)
    val newPassCount: Seq[Int] = passCount
    newPassCount updated(turnPlayerIdIndex, passCount(turnPlayerIdIndex) + 1)
    new Game(
      playerIds,
      if (turnPlayerIdIndex == playerIds.size) playerIds.head else playerIds(turnPlayerIdIndex + 1),
      turnCount + 1,
      newPassCount,
      board)
  }
}
