package week3

object Rationals {
  val r1 = new Rational(1, 2)                     //> r1  : week3.Rational = 1/2
  val r2 = new Rational(1, 4)                     //> r2  : week3.Rational = 1/4
  r1.add(r2)                                      //> res0: week3.Rational = 3/4
  
  val x = new Rational(1, 3)                      //> x  : week3.Rational = 1/3
  val y = new Rational(5, 7)                      //> y  : week3.Rational = 5/7
  val z = new Rational(3, 2)                      //> z  : week3.Rational = 3/2
  
  x.sub(y).sub(z)                                 //> res1: week3.Rational = -79/42
  y.add(y)                                        //> res2: week3.Rational = 10/7
}

class Rational(x: Int, y: Int) {

  require(y != 0, "Denominator must be nonzero")
  
  private val g = gcd(x, y)
  val numer = x / g
  val denom = y / g
  
  def this(x: Int) = this(x, 1)
  
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
    
  def add(that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)
  
  def sub(that: Rational): Rational =
    add(that.neg)
  
  def neg: Rational =
    new Rational(-numer, denom)
  
  def less(that: Rational): Boolean =
    this.numer * that.denom < that.numer * this.denom
    
  def max(that: Rational): Rational =
    if (this.less(that)) that else this
  
  override def toString =
    numer + "/" + denom
}