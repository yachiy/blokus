package domain

import org.scalatest.FunSpec

class BlockSpec extends FunSpec {
  describe("A Block") {
    describe("#rodateLeft") {
      describe("L字型（3 x 3）") {
        val block = new Block(3, 3, Seq(Point(0, 0), Point(1, 0), Point(2, 0), Point(2, 1), Point(2, 2)))
        it("は90°左回転できること") {
          val result = block.rotateLeft()
          assert(result.points == Seq(Point(0, 2), Point(0, 1), Point(0, 0), Point(1, 0), Point(2, 0)))
        }
      }
      describe("L字型（2 x 2）") {
        val block = new Block(2, 2, Seq(Point(0, 0), Point(1, 0), Point(1, 1)))
        it("は90°左回転できること") {
          val result = block.rotateLeft()
          assert(result.points == Seq(Point(0, 1), Point(0, 0), Point(1, 0)))
        }
      }
      describe("凹字型（2 x 3）") {
        val block = new Block(2, 3, Seq(Point(0, 0), Point(1, 0), Point(1, 1), Point(1, 2), Point(0, 2)))
        it("は90°左回転できること") {
          val result = block.rotateLeft()
          assert(result.points == Seq(Point(0, 1), Point(0, 0), Point(1, 0), Point(2, 0), Point(2, 1)))
        }
      }
      describe("横棒（1 x 3）") {
        val block = new Block(1, 3, Seq(Point(0, 0), Point(1, 0), Point(2, 0)))
        it("は90°左回転できること") {
          val result = block.rotateLeft()
          assert(result.points == Seq(Point(0, 2), Point(0, 1), Point(0, 0)))
        }
      }
      describe("階段型（3 x 3）") {
        val block = new Block(3, 3, Seq(Point(0, 2), Point(1, 2), Point(2, 2), Point(1, 1), Point(2, 1), Point(2, 0)))
        it("は90°左回転できること") {
          val result = block.rotateLeft()
          assert(result.points == Seq(Point(2, 2), Point(2, 1), Point(2, 0), Point(1, 1), Point(1, 0), Point(0, 0)))
        }
      }
      describe("正方形（2 x 2）") {
        val block = new Block(2, 2, Seq(Point(0, 0), Point(1, 0), Point(0, 1), Point(1, 1)))
        it("は90°左回転できること") {
          val result = block.rotateLeft()
          assert(result.points == Seq(Point(0, 1), Point(0, 0), Point(1, 1), Point(1, 0)))
        }
      }
      describe("縦棒（5 x 1）") {
        val block = new Block(5, 1, Seq(Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3), Point(0, 4)))
        it("は90°左回転できること") {
          val result = block.rotateLeft()
          assert(result.points == Seq(Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0), Point(4, 0)))
        }
      }
      describe("縦棒（4 x 1）") {
        val block = new Block(4, 1, Seq(Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3)))
        it("は90°左回転できること") {
          val result = block.rotateLeft()
          assert(result.points == Seq(Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0)))
        }
      }
    }
  }
}