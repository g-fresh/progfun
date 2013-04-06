package week1

object session {
  
  def abs(x: Double): Double =
      if (x < 0) -x else x                        //> abs: (x: Double)Double
      
  def sqrt(x: Double): Double = {
    
    def sqrtItr(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtItr(improve(guess))
    
    def isGoodEnough(guess: Double): Boolean =
      abs(guess * guess - x) / x < 0.001
    
    def improve(guess: Double): Double =
      (guess + x / guess) / 2
      
    sqrtItr(1.0)
  }                                               //> sqrt: (x: Double)Double
  
  sqrt(4)                                         //> res0: Double = 2.000609756097561
  sqrt(16)                                        //> res1: Double = 4.000000636692939
  sqrt(3)                                         //> res2: Double = 1.7321428571428572
}