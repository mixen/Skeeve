package org.doomsday.skeeve.evaluation

import org.doomsday.skeeve.model._

class ContextEvaluator {
  def eval[T](node:Node[T], context:Map[String, Any]):Option[T] = node match {
    case Const(value) => Some(value)

    case Var(name) => context.get(name) match {
      case Some(value) => Some(value)
      case _ => None
    }

    case Func(f, arg) => eval(arg, context) match {
      case Some(value) => Some(f(value))
      case None => None
    }
      
    case Func2(f, arg1, arg2) => (eval(arg1, context), eval(arg2, context)) match {
      case (Some(value1), Some(value2)) => Some(f(value1, value2))
      case _ => None
    }
  }
}