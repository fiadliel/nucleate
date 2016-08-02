import scalariform.formatter.preferences._

scalaVersion := "2.11.8"

name := "nucleate"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.1",
  "org.scalaz" %% "scalaz-core" % "7.2.4",
  "com.github.mpilquist" %% "simulacrum" % "0.8.0"
)

