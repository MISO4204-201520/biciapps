name := """biciapps"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  //Jongo
  "uk.co.panaxiom" %% "play-jongo" % "0.8.0-jongo1.0"
)


libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
