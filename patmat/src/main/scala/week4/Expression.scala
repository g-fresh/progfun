package week4

trait Expression {
  
  def eval: Int = {
    this match {
      case Number(value) => value
      case Sum(left, right) => left.eval + right.eval
      case Product(left, right) => left.eval * right.eval
    }
  }
}

case class Number(value: Int) extends Expression

case class Sum(left: Expression, right: Expression) extends Expression

case class Product(left: Expression, right: Expression) extends Expression

case class Variable(name: String) extends Expression