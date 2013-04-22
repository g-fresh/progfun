package week4

object InsertionSort {

  def isort(xs: List[Int]): List[Int] = {
    def insert(x: Int, xs: List[Int]): List[Int] = xs match {
      case List() => List(x)
      case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
    }
    xs match {
      case List() => List()
      case y :: ys => insert(y, isort(ys))
    }
  }                                               //> isort: (xs: List[Int])List[Int]
  
  val list = List(3, 7, 2, 9, 1)                  //> list  : List[Int] = List(3, 7, 2, 9, 1)
  isort(list)                                     //> res0: List[Int] = List(1, 2, 3, 7, 9)
  
}