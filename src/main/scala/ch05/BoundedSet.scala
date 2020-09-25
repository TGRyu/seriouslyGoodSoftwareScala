package ch05

import scala.collection.immutable.Queue

case class BoundedSet[A](data: Queue[A],capacity: Int){
  def add(elem: A): BoundedSet[A] = {
    if (elem == null) throw new NullPointerException()
    val index = data.indexOf(elem)
    val data1 = if (index == -1) data else{
      val (f, b) = data.splitAt(index)
      f ++ b.tail
    }
    val data2 = if (data1.size == capacity) data1.tail else data1
    val data3 = data2.enqueue(elem)
    BoundedSet(data3, capacity)
  }

  def contains(elem : A): Boolean = data.contains(elem)
}