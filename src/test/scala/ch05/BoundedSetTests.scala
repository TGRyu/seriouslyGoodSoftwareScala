package ch05

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.immutable.Queue

class BoundedSetTests extends AnyFunSuite{
  test("testSingleElement" ){
    val set = BoundedSet[Int](Queue.empty, 3)
    val set1 = set.add(1)
    assert(set1.contains(1) == true)
  }

  test("testRepeatedElement" ){
    val set = BoundedSet[Int](Queue.empty, 3)
    val set1 = set.add(1)
    val set2 = set1.add(1)
    val set3 = set2.add(1)
    val set4 = set3.add(1)
    assert(set4.contains(1) == true)
  }

  test("testOverflowKeepsSecond" ){
    val set = BoundedSet[Int](Queue.empty, 3)
    val set1 = set.add(1)
    val set2 = set1.add(2)
    val set3 = set2.add(3)
    val set4 = set3.add(4)

    assert(set4.contains(2) == true)
  }

  test("testOverflowRemovesOldest" ){
    val set = BoundedSet[Int](Queue.empty, 3)
    val set1 = set.add(1)
    val set2 = set1.add(2)
    val set3 = set2.add(3)
    val set4 = set3.add(4)

    assert(set4.contains(1) == false)
  }

  test("testOverflowKeepsNewest" ){
    val set = BoundedSet[Int](Queue.empty, 3)
    val set1 = set.add(1)
    val set2 = set1.add(2)
    val set3 = set2.add(3)
    val set4 = set3.add(4)

    assert(set4.contains(4) == true)
  }

  test("testRenewal" ){
    val set = BoundedSet[Int](Queue.empty, 3)
    val set1 = set.add(1)
    val set2 = set1.add(2)
    val set3 = set2.add(1)
    val set4 = set3.add(3)
    val set5 = set4.add(4)

    assert(set5.contains(1) == true)
  }


}