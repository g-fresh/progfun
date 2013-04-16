package week3

object NaturalNumbers {
  
  val zero = Zero                                 //> zero  : week3.Zero.type = 0
  assert(zero == zero)
  assert(! (zero != zero))
  assert((zero + zero) == zero)
  assert((zero - zero) == zero)
  
  val one = zero.successor                        //> one  : week3.Nat = 1
  assert(one == one)
  assert(! (one != one))
  assert(! one.isZero)
  assert(! (zero + one).isZero)
  assert((zero + one) != zero)
  assert((zero + one) == one)
  assert(! (one + Zero).isZero)
  assert((one + zero) != Zero)
  assert((one + zero) == one)
  assert(! (one + one).isZero)
  assert((one + one) != Zero)

  val two = one.successor                         //> two  : week3.Nat = 2
  assert(! two.isZero)
  assert(! (one + one).isZero)
  assert((one + one) != zero)
  assert((one + one) == two)
  assert((one - one).isZero)
  assert((one - one) == zero)
  assert((one + one) - (two) == zero)

}

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
  def == (that: Nat): Boolean
  def != (that: Nat): Boolean
  def toInt: Int = {
    def count(n: Nat, counter: Int): Int =
      if (n.isZero) counter
      else count(n.predecessor, counter + 1)
    count(this, 0)
  }
  override def toString: String = toInt.toString
}

object Zero extends Nat {
  def isZero = true
  def predecessor = throw new NoSuchElementException
  def + (that: Nat) = that
  def - (that: Nat) = if (that.isZero) this else throw new NoSuchElementException
  def == (that: Nat): Boolean = that.isZero
  def != (that: Nat): Boolean = ! that.isZero
}

class Succ(val n: Nat) extends Nat {
  def isZero = false
  def predecessor = n
  def + (that: Nat) = new Succ(n + that)
  def - (that: Nat) = if (that.isZero) this else n - that.predecessor
  def == (that: Nat) = if (that.isZero) false else n == that.predecessor
  def != (that: Nat) = if (that.isZero) true else n != that.predecessor
}