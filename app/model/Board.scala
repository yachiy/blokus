package model

class Board(val row: Int, val column: Int) {
  private val cells: Array[Array[Int]] = Array.ofDim(row, column)

  // FIXME 良い書き方教えてください
  def accept(playerId: Int, leftTop: Point, block: Block): Boolean = {
    require(leftTop.x >= 0 && leftTop.y >= 0)
    require(leftTop.x + block.column < column && leftTop.y + block.row < row)

    val pointsOnBoard = block.points.map(_ + leftTop)
    if (pointsOnBoard.exists(valueOf(_) != 0)) {
      return false
    }
    if (pointsOnBoard.exists(hasAdjoinedCells(playerId, _))) {
      return false
    }
    if (!pointsOnBoard.exists(touchedOnCorner(playerId, _))) {
      return false
    }
    pointsOnBoard.foreach(put(playerId, _))
    true
  }

  def countBlocks(): Map[Int, Int] = cells.flatten
    .filter(_ != 0)
    .groupBy(identity)
    .mapValues(_.length)

  private def in(point: Point): Boolean = point.x >= 0 && point.y >= 0 && point.x < column && point.y < row

  private def valueOf(point: Point): Int = cells(point.x)(point.y)

  private def put(playerId: Int, point: Point) {
    cells(point.x)(point.y) = playerId
  }

  private def hasAdjoinedCells(playerId: Int, point: Point): Boolean = {
    Seq(point.left(), point.right(), point.up(), point.down())
      .filter(in)
      .exists(valueOf(_) == playerId)
  }

  private def touchedOnCorner(playerId: Int, point: Point): Boolean = {
    Seq(point.left().up(), point.left().down(), point.right().up(), point.right().down())
      .filter(in)
      .exists(valueOf(_) == playerId)
  }
}
