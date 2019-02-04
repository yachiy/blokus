package model

import scala.util.Random

class Game(val playerIds: Seq[Int], val turnPlayerId: Int = 0, val turn: Int = 1, val board: Board) {
  def start(): Game = {
    val order = Random.shuffle(playerIds)
    new Game(order, order.head, turn, board)
  }

  def play(leftTop: Point, block: Block): Game = {
    val put = board.put(turnPlayerId, leftTop, block)
    if (board == put) {
      val turnPlayerIdIndex = playerIds.indexOf(turnPlayerId)
      new Game(
        playerIds,
        if (turnPlayerIdIndex == playerIds.size) playerIds.head else playerIds(turnPlayerIdIndex + 1),
        turn + 1,
        board)
    } else {
      this
    }
  }

  def pass(): Game = {
    val turnPlayerIdIndex = playerIds.indexOf(turnPlayerId)
    new Game(
      playerIds,
      if (turnPlayerIdIndex == playerIds.size) playerIds.head else playerIds(turnPlayerIdIndex + 1),
      turn + 1,
      board)
  }
}
