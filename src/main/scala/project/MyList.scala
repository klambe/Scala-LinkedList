package project

/*
* create a simple linked list if scala with head, tail and add method
* Add generic support for all data types
* use Higher order functions
*/

abstract class MyList[+A] {

  def add[B >: A](element: B): MyList[B]

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B](myTransformer: A => B): MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]

}

case object Empty extends MyList[Nothing] {

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def printElements: String = ""

  override def map[B](myTransformer: Nothing => B): MyList[Nothing] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}


case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  def map[B](myTransformer: A => B): MyList[B] =
    new Cons(myTransformer(h), t.map(myTransformer))


  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

}

