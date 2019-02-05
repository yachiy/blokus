package domain

class Block(val row: Int, val column: Int, val points: Seq[Point]) {
  def rotateLeft(): Block = {
    val movedPoints = points.map(_ - Point(column / 2, row / 2))
    val rotated = movedPoints.map(_.rotateLeft())
    val reversedPoints = rotated.map(_ + Point(row / 2, column / 2 - (1 - row % 2)))
    new Block(column, row, reversedPoints)
  }

  def rotateRight(): Block = new Block(column, row, points.map(_.rotateRight()))
}
