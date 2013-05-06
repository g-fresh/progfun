package week6

object nqueens {
 
  def queens(n: Int): Set[List[Int]] = {
    
    def placeQueens(k: Int): Set[List[Int]] =
      if (k == 0) Set(List())
      else for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens
    
    def isSafe(col: Int, queens: List[Int]): Boolean = {
      val row = queens.size
      val queensWithRow = (row - 1 to 0 by -1) zip queens
      queensWithRow forall {
        case (r, c) => col != c && math.abs(col - c) != row - r
      }
    }
    
    placeQueens(n)
  }                                               //> queens: (n: Int)Set[List[Int]]
     
  def show(queens: List[Int]) = {
    val lines =
      for (col <- queens.reverse)
      yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
    "\n" + (lines mkString "\n")
  }                                               //> show: (queens: List[Int])String
  
  (queens(6) take 3 map show) mkString "\n"       //> res0: String = "
                                                  //| * * X * * * 
                                                  //| * * * * * X 
                                                  //| * X * * * * 
                                                  //| * * * * X * 
                                                  //| X * * * * * 
                                                  //| * * * X * * 
                                                  //| 
                                                  //| * X * * * * 
                                                  //| * * * X * * 
                                                  //| * * * * * X 
                                                  //| X * * * * * 
                                                  //| * * X * * * 
                                                  //| * * * * X * 
                                                  //| 
                                                  //| * * * X * * 
                                                  //| X * * * * * 
                                                  //| * * * * X * 
                                                  //| * X * * * * 
                                                  //| * * * * * X 
                                                  //| * * X * * * "
}