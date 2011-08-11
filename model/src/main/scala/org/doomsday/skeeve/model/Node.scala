package org.doomsday.skeeve.model

sealed abstract class Node[+T]

case class Func[Arg, +R](func:Arg => R, arg:Node[Arg]) extends Node[R]

case class Func2[Arg1, Arg2, +R](func:(Arg1, Arg2) => R, arg1:Node[Arg1], arg2:Node[Arg2]) extends Node[R]

case class Const[+T](value:T) extends Node[T]

case class Var[+T](name:String) extends Node[T]

object Node {
  implicit def val2Const[T](value:T) = Const(value)
}
