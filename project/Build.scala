
import sbt._
import Keys._

object BuildSettings {
  val buildOrganization = "org.doomsday"
  val buildVersion      = "0.1"
  val buildScalaVersion = "2.9.0-1"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion
  )
}

object Dependencies {
  val scalaTest = "org.scalatest" % "scalatest_2.9.0" % "1.4.1" % "test"
}

object SkeeveBuild extends Build {
  import Dependencies._
  import BuildSettings._

  val commonDeps = Seq(
    scalaTest
  )

  val commonSettings = buildSettings ++ Seq(libraryDependencies ++= commonDeps)

  lazy val skeeve = Project (
    "skeeve",
    file("."),
    settings = buildSettings
  ) aggregate (model)


  lazy val model = Project (
    "model",
    file("model"),
    settings = commonSettings
  )
}
