package week3

object Lecture2 {

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)
  }                                               //> product: (f: Int => Int)(a: Int, b: Int)Int
  
  product(x => x)(1, 3)                           //> res0: Int = 6
  
  def factorial(n: Int): Int = {
    product(x => x)(1, n)
  }                                               //> factorial: (n: Int)Int
  
  factorial(4)                                    //> res1: Int = 24
  
  def foo(combine: (Int, Int) => Int, unit: Int)(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) unit
    else combine(f(a), foo(combine, unit)(f)(a + 1, b))
  }                                               //> foo: (combine: (Int, Int) => Int, unit: Int)(f: Int => Int)(a: Int, b: Int)I
                                                  //| nt
  def sum(x: Int, y: Int): Int = x + y            //> sum: (x: Int, y: Int)Int
  
  def prod(x: Int, y: Int): Int = x * y           //> prod: (x: Int, y: Int)Int
     
  foo(sum, 0)(x => x)(1, 3)                       //> res2: Int = 6
  foo(prod, 1)(x => x)(1, 3)                      //> res3: Int = 6
}