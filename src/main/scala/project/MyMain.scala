package project

object MyMain extends App {

  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val list3 = new Cons(1, new Cons(4, new Cons(5, Empty)))
  val list2 = new Cons(1.3, new Cons(2.4, new Cons(3.7, Empty)))

  println(list.tail.head)

  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)
  println(list2.toString)

  println(list.map(new Function1[Int, Int] {
    override def apply(a: Int): Int = a * 2
  }).toString)

  println("Lambda Version")

  println(list.map((a: Int) => a * 2).toString)

  println(list.filter(new Function1[Int, Boolean] {
    override def apply(input: Int): Boolean =  (input % 2 == 1)
  }
  ).toString)
  println("Lambda Version")

  println(list.filter((input: Int) => (input % 2 == 1)
  ).toString)

  println((list ++ list3).toString)

  println(list.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(a: Int): MyList[Int] = new Cons(a, new Cons(a + 1, Empty))
  }
  ).toString)

  println("Lambda Version")
println(list.flatMap((a:Int) =>  new Cons(a, new Cons(a + 1, Empty))).toString)

}

