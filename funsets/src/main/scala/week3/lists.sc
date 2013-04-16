package week3

object lists {

  def nth[T](n: Int, list: List[T]): T = {
    if (list.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) list.head
    else nth(n - 1, list.tail)
  }                                               //> nth: [T](n: Int, list: week3.List[T])T
  
  val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> list  : week3.Cons[Int] = week3.Cons@49a1afb1
  nth(0, list)                                    //> res0: Int = 1
  nth(1, list)                                    //> res1: Int = 2
  nth(2, list)                                    //> res2: Int = 3
  nth(3, list)                                    //> java.lang.IndexOutOfBoundsException
                                                  //| 	at week3.lists$$anonfun$main$1.nth$1(week3.lists.scala:6)
                                                  //| 	at week3.lists$$anonfun$main$1.apply$mcV$sp(week3.lists.scala:15)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week3.lists$.main(week3.lists.scala:3)
                                                  //| 	at week3.lists.main(week3.lists.scala)
  
}