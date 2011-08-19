package org.doomsday.skeeve.parsing

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import org.doomsday.skeeve.model.{Const, Var}

class ParserTest extends FunSuite with ShouldMatchers {
  test("basic parsing") {
    Parser("asd") should equal (Var("asd"))
    Parser("42") should equal (Const(42))
  }
}