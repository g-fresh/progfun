package week6

object queries {

  val books = Set(
    new Book(title = "Java Puzzlers", authors = List("Bloch")),
    new Book(title = "Effective Java", authors = List("Bloch")),
    new Book(title = "Programming in Scala", authors = List("Odersky", "Venners"))
  )                                               //> books  : scala.collection.immutable.Set[week6.Book] = Set(Book(Java Puzzlers
                                                  //| ,List(Bloch)), Book(Effective Java,List(Bloch)), Book(Programming in Scala,L
                                                  //| ist(Odersky, Venners)))
  
  for (b <- books; a <- b.authors if a startsWith "Odersky")
  yield b.title                                   //> res0: scala.collection.immutable.Set[String] = Set(Programming in Scala)
  
  books.flatMap(book =>
      book.authors.map(author => (book.title, author))).filter(titleAndAuthor =>
          titleAndAuthor._2.startsWith("Odersky"))//> res1: scala.collection.immutable.Set[(String, String)] = Set((Programming in
                                                  //|  Scala,Odersky))
}

case class Book(title: String, authors: List[String])