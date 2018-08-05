package project


/*
* Trait replaced with function calls!*/

trait MyTransformer[-A, B] {

  def transform(a:A): B

}

//class StringToIntTransformer extends MyTransformer[String, Int]{
//
//  override def transform(a: String): Int = a.toInt
//}
