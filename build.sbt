name := """biciapps"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  //Jongo
  "org.jongo" % "jongo" % "1.2",
  "org.mongodb" % "mongo-java-driver" % "3.0.4",
  "com.feth" %% "play-authenticate" % "0.7.0"
)


libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

// Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
EclipseKeys.preTasks := Seq(compile in Compile)


fork in run := true