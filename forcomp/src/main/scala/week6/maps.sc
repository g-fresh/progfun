package week6

object maps {
  val romanNumeral = Map("I" -> 1, "V" -> 5, "X" -> 10)
                                                  //> romanNumeral  : scala.collection.immutable.Map[String,Int] = Map(I -> 1, V ->
                                                  //|  5, X -> 10)
  val capitalOfCountry = Map("US" -> "Washington", "Swizerland" -> "Bern")
                                                  //> capitalOfCountry  : scala.collection.immutable.Map[String,String] = Map(US -
                                                  //| > Washington, Swizerland -> Bern)
  
  capitalOfCountry("US")                          //> res0: String = Washington
  
  capitalOfCountry get "Andorra"                  //> res1: Option[String] = None
  capitalOfCountry get "US"                       //> res2: Option[String] = Some(Washington)
  
}