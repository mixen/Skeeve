package org.doomsday.skeeve.parsing

import util.parsing.combinator.syntactical.StandardTokenParsers
import org.doomsday.skeeve.model.{Const, Var}

object Parser extends StandardTokenParsers {

  val id = ident ^^ {p => Var[Any](p)}
  val intNode = numericLit ^^ {n => Const[Int](n.toInt)}
  
  val expr = id | intNode

  def parse(s:String) = {
    phrase(expr)(new lexical.Scanner(s))
  }

  def apply(s:String) = parse(s) match {
    case Success(tree, _) => tree
  }
}