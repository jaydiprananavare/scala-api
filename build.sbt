name := """scala-api"""
organization := "com.ing.api"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.27.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.ing.api.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.ing.api.binders._"
