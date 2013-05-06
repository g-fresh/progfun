package week5

object lists {
  val fruit = List("apples", "oranges", "pears")  //> fruit  : List[String] = List(apples, oranges, pears)
  val nums = List(1, 2, 3)                        //> nums  : List[Int] = List(1, 2, 3)
  val diag = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
                                                  //> diag  : List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
                                                  //| 
  val empty = List()                              //> empty  : List[Nothing] = List()
  
  /*
  def removeAt[T](n: Int, xs: List[T]): List[T] = {
    if (n == 0) xs.tail
    else List(xs.head) ++ removeAt(n - 1, xs.tail)
  }
  */
  
  def removeAt[T](n: Int, xs: List[T]): List[T] = {
     (xs take n) ::: (xs drop n + 1)
  }                                               //> removeAt: [T](n: Int, xs: List[T])List[T]
   
  assert(removeAt(0, fruit) == List("oranges", "pears"))
  assert(removeAt(1, fruit) == List("apples", "pears"))
  assert(removeAt(2, fruit) == List("apples", "oranges"))
  assert(removeAt(3, fruit) == List("apples", "oranges", "pears"))
  
  def flatten(xs: List[Any]): List[Any] = {
    List()
  }                                               //> flatten: (xs: List[Any])List[Any]

  //assert(flatten(List(List(1, 1), 2, List(3, List(5, 8)))) == List(1, 1, 2, 3, 5, 8))
  
  val pair = ("number", 1)                        //> pair  : (String, Int) = (number,1)
  val (name, value) = pair                        //> name  : String = number
                                                  //| value  : Int = 1
  println(name)                                   //> number
  
  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, rest) = xs span (y => y == x)
      first :: pack(rest)
  }                                               //> pack: [T](xs: List[T])List[List[T]]
  
  val data = List("a", "a", "a", "b", "b", "a", "c")
                                                  //> data  : List[String] = List(a, a, a, b, b, a, c)
  pack(data)                                      //> res0: List[List[String]] = List(List(a, a, a), List(b, b), List(a), List(c)
                                                  //| )
  
  def encode[T](xs: List[T]): List[(T, Int)] = {
    pack(xs) map (list => (list.head, list.size))
  }                                               //> encode: [T](xs: List[T])List[(T, Int)]
  
  encode(data)                                    //> res1: List[(String, Int)] = List((a,3), (b,2), (a,1), (c,1))
}