package project

/*
* create a simple linked list if scala with head, tail and add method
*/

abstract class MyList[+A] {

  def add[B >: A](element: B): MyList[B]

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B](myTransformer: MyTransformer[A, B]): MyList[B]

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]

  def ++[B>:A](list:MyList[B]):MyList[B]

}

object Empty extends MyList[Nothing] {

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def printElements: String = ""

  override def map[B](myTransformer: MyTransformer[Nothing, B]): MyList[Nothing] = Empty

  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  def ++[B>:Nothing](list:MyList[B]):MyList[B] = list
}


class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  def map[B](myTransformer: MyTransformer[A, B]): MyList[B] =
    new Cons(myTransformer.transform(h), t.map(myTransformer))


  def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  def ++[B>:A](list:MyList[B]):MyList[B] = new Cons(h, t ++ list)

}

