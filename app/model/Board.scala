package model

class Board(val row: Int, val column: Int) {
  private val cells: Array[Array[Int]] = Array.ofDim(row, column)

  def accept(playerId: Int, leftTop: Point, block: Block): Boolean = {
    require(leftTop.x >= 0 && leftTop.y >= 0)
    require(leftTop.x + block.column < column && leftTop.y + block.row < row)

    val pointsOnBoard = block.points.map(_ + leftTop)
    if (isValid(pointsOnBoard, playerId)) {
      pointsOnBoard.foreach(put(playerId, _))
      true
    } else {
      false
    }
  }

  def in(point: Point): Boolean = point.x >= 0 && point.y >= 0 && point.x < column && point.y < row

  def valueOf(point: Point): Int = cells(point.x)(point.y)

  def put(playerId: Int, point: Point) {
    cells(point.x)(point.y) = playerId
  }

  private def isValid(points: Seq[Point], playerId: Int): Boolean = {
    !points.exists(valueOf(_) != 0) && !points.exists(hasAdjoinedCells(playerId, _)) && points.exists(touchedOnCorner(playerId, _))
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
