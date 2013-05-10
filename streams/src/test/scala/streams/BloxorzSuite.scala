package streams

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Bloxorz._

@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) => move match {
        case Left => block.left
        case Right => block.right
        case Up => block.up
        case Down => block.down
      }
    }
  }

  trait Level1 extends SolutionChecker {
      /* terrain for level 1*/

    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }

  test("terrain function level 1") {
    new Level1 {
      assert(!terrain(Pos(-1,0)), "-1,0")
      assert(!terrain(Pos(0,-1)), "0,-1")
      assert(!terrain(Pos(0,8)), "0,8")
      assert(!terrain(Pos(0,11)), "0,11")
      assert(!terrain(Pos(4,11)), "4,11")
      assert(!terrain(Pos(6,0)), "6,0")
      assert(terrain(Pos(0,0)), "0,0")
      assert(terrain(Pos(0,1)), "0,1")
      assert(terrain(Pos(3,7)), "3,7")
    }
  }
  
  test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1,1))
    }
  }

  test("neighbors of startBlock") {
    new Level1() {
      val neighbors = startBlock.neighbors
      assert(neighbors.size === 4)
      assert(neighbors.contains((Block(Pos(-1, 1), Pos(0, 1)), Up)))
      assert(neighbors.contains((Block(Pos(1, 2), Pos(1, 3)), Right)))
      assert(neighbors.contains((Block(Pos(2, 1), Pos(3, 1)), Down)))
      assert(neighbors.contains((Block(Pos(1, -1), Pos(1, 0)), Left)))
    }
  }
  
  test("legalNeighbors of startBlock") {
    new Level1() {
      val legalNeighbors = startBlock.legalNeighbors
      assert(legalNeighbors.size === 2)
      assert(legalNeighbors.contains((Block(Pos(1, 2), Pos(1, 3)), Right)))
      assert(legalNeighbors.contains((Block(Pos(2, 1), Pos(3, 1)), Down)))
    }
  }
  
  test("done") {
    new Level1() {
      assert(! done(Block(Pos(4, 7), Pos(4, 8))))
      assert(! done(Block(Pos(4, 6), Pos(4, 7))))
      assert(done(Block(goal, goal)))
    }
  }
  
  test("neighborsWithHistory") {
    new Level1() {
      val result = neighborsWithHistory(Block(Pos(1, 1), Pos(1, 1)), List(Left, Up))
      assert(result.size === 2)
      assert(result contains (Block(Pos(1, 2), Pos(1, 3)), List(Right, Left, Up)))
      assert(result contains (Block(Pos(2, 1), Pos(3, 1)), List(Down, Left, Up)))
    }
  }
  
  test("newNeighborsOnly") {
    new Level1() {
      val neighbors = newNeighborsOnly(
        Set(
          (Block(Pos(1, 2), Pos(1, 3)), List(Right, Left, Up)),
          (Block(Pos(2, 1), Pos(3, 1)), List(Down, Left, Up))
        ).toStream,
        Set(Block(Pos(1, 2), Pos(1, 3)), Block(Pos(1, 1), Pos(1, 1)))
      )
      assert(neighbors === Set((Block(Pos(2, 1), Pos(3, 1)), List(Down, Left, Up))).toStream)
    }
  }
  
  test("optimal solution for level 1") {
    new Level1 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }
  
  trait LevelWithoutSolution extends SolutionChecker {

    val level = "ST"

    val optsolution = List.empty
  }
  
  test("solution length for level without solution") {
    new LevelWithoutSolution() {
      assert(solution.length == optsolution.length)
    }
  }
}
