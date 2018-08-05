package project

/*
* Trait replaced with function calls!*/

trait MyPredicate[-T] {
  def test(input: T): Boolean

}

////class EvenPredicate extends MyPredicate[Int] {
////  override def test(input: Int): Boolean = if (input % 2 == 0) true else false
////
////}
