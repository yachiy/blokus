package domain

case class Point(x: Int, y: Int) {
  def rotateLeft(): Point = Point(y, -x)

  def rotateRight(): Point = Point(-y, x)

  def +(other: Point): Point = Point(x + other.x, y + other.y)

  def left(): Point = Point(x - 1, y)

  def right(): Point = Point(x + 1, y)

  def down(): Point = Point(x, y - 1)

  def up(): Point = Point(x, y + 1)

}