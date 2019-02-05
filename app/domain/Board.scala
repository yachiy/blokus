package domain

class Board(val cells: Seq[Seq[Int]]) {
  private val ROW: Int = 14
  private val COLUMN: Int = 14

  def put(playerId: Int, leftTop: Point, block: Block): Board = {
    require(leftTop.x >= 0 && leftTop.y >= 0)
    require(leftTop.x + block.column < COLUMN && leftTop.y + block.row < ROW)

    val pointsOnBoard = block.points.map(_ + leftTop)
    if (isValid(pointsOnBoard, playerId)) {
      putPoint(playerId, pointsOnBoard)
    } else {
      this
    }
  }

  def countBlocks(): Map[Int, Int] = cells.flatten
    .filter(_ != 0)
    .groupBy(identity)
    .mapValues(_.length)

  private def putPoint(playerId: Int, points: Seq[Point]): Board = {
    val copy = cells
    points.foreach(p => copy(p.x) updated(p.y, playerId))
    new Board(copy)
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
      .exists(p => isCorner(p) || valueOf(p) == playerId)
  }

  private def in(point: Point): Boolean = point.x >= 0 && point.y >= 0 && point.x < COLUMN && point.y < ROW

  private def isCorner(point: Point): Boolean = (point.x == -1 && (point.y == -1 || point.y == ROW)) || (point.x == COLUMN && (point.y == -1 || point.y == ROW))

  private def valueOf(point: Point): Int = cells(point.x)(point.y)
}
