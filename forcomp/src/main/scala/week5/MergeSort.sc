package week5

object MergeSort {
  
  def msort(xs: List[Int]): List[Int] = {
    def merge(ys: List[Int], zs: List[Int]): List[Int] = {
      (ys, zs) match {
        case (Nil, zs) => zs
        case (ys, Nil) => ys
        case (y :: ys1, z :: zs1) =>
          if (y <= z) y :: merge(ys1, zs)
          else z :: merge(zs1, ys)
      }
    }
    
    val n = xs.size / 2
    if (n == 0)
      xs
    else {
      val (firstHalf, secondHalf) = xs splitAt n
      merge(msort(firstHalf), msort(secondHalf))
    }
  }                                               //> msort: (xs: List[Int])List[Int]
  
  val nums = List(3, 7, 1, 2, 9, 5)               //> nums  : List[Int] = List(3, 7, 1, 2, 9, 5)
  
  msort(nums)                                     //> res0: List[Int] = List(1, 2, 3, 5, 7, 9)
}