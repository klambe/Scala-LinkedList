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

}

object Empty extends MyList[Nothing] {

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def printElements: String = ""
}


class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
}

