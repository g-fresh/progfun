package week3

object intsets {
  
  val t1 = Empty.include(5).include(2)            //> t1  : week3.IntSet = {{.2.}5.}
  val t2 = (Empty include 1) include 3            //> t2  : week3.IntSet = {.1{.3.}}
  val result = t1 union t2                        //> result  : week3.IntSet = {.1{{.2.}3{.5.}}}
}

abstract class IntSet {
  
  def include(value: Int): IntSet
  
  def contains(value: Int): Boolean
  
  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  
  def include(value: Int): IntSet =
    new NonEmpty(value, Empty, Empty)
  
  def contains(value: Int): Boolean =
    false
    
  def union(other: IntSet): IntSet =
    other
  
  override def toString =
    "."
}

class NonEmpty(element: Int, left: IntSet, right: IntSet) extends IntSet {
  
  def include(value: Int): IntSet =
    if (value < element) new NonEmpty(element, left include value, right)
    else if (value > element) new NonEmpty(element, left, right include value)
    else this
     
  def contains(value: Int): Boolean =
    if (value < element) left contains value
    else if (value > element) right contains value
    else true
    
  def union(other: IntSet): IntSet =
    ((left union right) union other) include element
  
  override def toString =
    "{" + left + element + right + "}"
}