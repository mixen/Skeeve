package org.doomsday.skeeve.evaluation

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import org.doomsday.skeeve.model.{Var, Func2}


class ContextEvaluatorTest extends FunSuite with ShouldMatchers {
  val plus: (Int, Int) => Int = _ + _
  val times: (Int, Int) => Int = _ * _

  test("normal evaluation") {
    val tree = Func2(plus, Func2(times, 2, 3), 5)
    ContextEvaluator eval (tree, Map()) should equal (Some(11))
  }

  test("evaluation with variables") {
    val tree = Func2(plus, Var[Int]("a"), Var[Int]("b"))

    ContextEvaluator eval (tree, Map("a" -> 17, "b" -> 25)) should equal (Some(42))
  }

  test("evaluation with incomplete context") {
    val tree = Func2[Int, Int, Int](_ + _, Var("a"), Var("b"))

    ContextEvaluator eval (tree, Map("a" -> 21)) should equal(None)
  }

  test("evaluation with type mismatch") {
    ContextEvaluator.eval(Var[List[String]]("a"), Map("a" -> List[Int](1))) should equal (None)
  }
}