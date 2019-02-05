package domain

class Block(val row: Int, val column: Int, val points: Seq[Point]) {
  def rotateLeft(): Block = new Block(column, row, points.map(_.rotateLeft()))

  def rotateRight(): Block = new Block(column, row, points.map(_.rotateRight()))
}
