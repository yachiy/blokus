package domain

import org.scalatest.FunSpec

class PointSpec extends FunSpec {
  describe("A Point") {
    describe("#rodateLeft") {
      it("は90°左回転できること") {
        val result = Point(1, 0).rotateLeft()
        assert(result == Point(0, -1))
      }
      it("は180°左回転できること") {
        val result = Point(1, 0).rotateLeft().rotateLeft()
        assert(result == Point(-1, 0))
      }
      it("は270°左回転できること") {
        val result = Point(1, 0).rotateLeft().rotateLeft().rotateLeft()
        assert(result == Point(0, 1))
      }
      it("は360°左回転できること") {
        val result = Point(1, 0).rotateLeft().rotateLeft().rotateLeft().rotateLeft()
        assert(result == Point(1, 0))
      }
    }
    describe("#rodateRight") {
      it("は90°右回転できること") {
        val result = Point(1, 0).rotateRight()
        assert(result == Point(0, 1))
      }
      it("は180°右回転できること") {
        val result = Point(1, 0).rotateRight().rotateRight()
        assert(result == Point(-1, 0))
      }
      it("は270°右回転できること") {
        val result = Point(1, 0).rotateRight().rotateRight().rotateRight()
        assert(result == Point(0, -1))
      }
      it("は360°右回転できること") {
        val result = Point(1, 0).rotateRight().rotateRight().rotateRight().rotateRight()
        assert(result == Point(1, 0))
      }
    }
    it("は90度右回転して90度左回転したら元の座標に戻ること") {
      val result = Point(1, 0).rotateRight().rotateLeft()
      assert(result == Point(1, 0))
    }
  }
}