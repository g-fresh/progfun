package week6

object wordcode {
  
  val dictionary = List("tea", "ate", "eat", "fun", "set")
                                                  //> dictionary  : List[String] = List(tea, ate, eat, fun, set)
  
  type Word = String
  type Occurrences = List[(Char, Int)]
  
  def occurrences(w: Word): Occurrences = {
    val word = w.toLowerCase
    val charGroups = word groupBy (char => char)
    val occurences = (charGroups mapValues (charGroup => charGroup.size)).toList
    occurences sortWith (_._1 < _._1)
  }                                               //> occurrences: (w: week6.wordcode.Word)week6.wordcode.Occurrences
  
  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] =
    dictionary groupBy (word => occurrences(word))//> dictionaryByOccurrences: => Map[week6.wordcode.Occurrences,List[week6.wordco
                                                  //| de.Word]]
    
  println(dictionaryByOccurrences)                //> Map(List((f,1), (n,1), (u,1)) -> List(fun), List((e,1), (s,1), (t,1)) -> Lis
                                                  //| t(set), List((a,1), (e,1), (t,1)) -> List(tea, ate, eat))
  
}