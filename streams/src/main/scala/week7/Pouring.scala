package week7

class Pouring(capacity: Vector[Int]) {

// States
  type State = Vector[Int]
  
  val initialState = capacity map (x => 0)

// Moves
  trait Move {
    def change(state: State): State
  }
  
  case class Empty(glass: Int) extends Move {
    override def change(state: State) =
      state updated (glass, 0)
  }
  
  case class Fill(glass: Int) extends Move {
    override def change(state: State) =
      state updated (glass, capacity(glass))
  }
  
  case class Pour(from: Int, to: Int) extends Move {
    override def change(state: State) = {
      val amountInFrom = state(from)
      val amountInTo = state(to)
      val remainingCapacity = capacity(to) - amountInTo
      val amountToPour = Math.min(amountInFrom, remainingCapacity)
      state updated (from, amountInFrom - amountToPour) updated (to, amountInTo + amountToPour)
    }
  }
  
  val glasses = 0 until capacity.length
  
  val moves = 
    (for (glass <- glasses) yield Empty(glass)) ++
    (for (glass <- glasses) yield Fill(glass)) ++
    (for (from <- glasses; to <- glasses; if from != to) yield Pour(from, to))
    
// Paths
    
  class Path(history: List[Move], val endState: State) {
    def extend(move: Move) = new Path(move :: history, move change endState)
    override def toString = (history.reverse mkString " ") + "--> " + endState
  }  
  
  val initialPath = new Path(Nil, initialState)
  
  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty
    else {
      val more = for {
        path <- paths
        next <- moves map path.extend
        if ! (explored contains next.endState)
      } yield next 
      paths #:: from(more, explored ++ (more map (_.endState)))
    }
  
  val pathSets = from(Set(initialPath), Set(initialState))
  
  def solutions(target: Int): Stream[Path] =
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains target
    } yield path
}