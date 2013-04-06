package recfun
import common._

object Main {

  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r || r == 0)
      1
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {

    def balance(open: Int, chars: List[Char]): Boolean = {
      if (chars.isEmpty) open == 0
      else nextChar(open, chars)
    }

    def nextChar(open: Int, chars: List[Char]): Boolean = {
      val o = open + delta(chars.head)
      if (o < 0) false
      else balance(o, chars.tail)
    }

    def delta(c: Char): Int = {
      if (c == '(') 1
      else if (c == ')') -1
      else 0
    }

    balance(0, chars)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {

    def loop(change: List[Int], accumulator: Int): Int = {
      val sum = change.sum
      if (sum == money)
        accumulator + 1
      else if (sum > money)
        accumulator
      else {
        val lastCoin = if (change.isEmpty) 0 else change.last
        val coinsToAdd = for (coin <- coins if coin >= lastCoin) yield coin
        coinsToAdd.foldLeft(accumulator)((acc, coin) => (loop(change :+ coin, acc)))
      }
    }

    loop(List(), 0)
  }
}
