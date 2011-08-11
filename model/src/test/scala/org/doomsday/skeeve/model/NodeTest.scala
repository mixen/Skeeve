package org.doomsday.skeeve.model

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class NodeTest extends FunSuite with ShouldMatchers {
  test("tree creation") {
    val left = Const(3)
    val right = Const(5)
    val function:(Int, Int) => Int = _ + _

    val tree = Func2(function, left, right)

    println(tree)
  }

  test("implicit constant nodes") {
    val f:Int => Int = _ + 1

    Func(f, 42) should equal (Func(f, Const(42)))
  }
}