import ReleaseTransformations._
organization := "com.github.pcejrowski"
name := "grasca"

scalaVersion := "2.12.1"
crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.1")

scalacOptions ++= Seq("-feature", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.3.0"
libraryDependencies += "org.json4s" %% "json4s-native" % "3.5.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "com.github.tomakehurst" % "wiremock" % "2.6.0" % "test"

useGpg := true
publishMavenStyle := true
publishArtifact in Test := false
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}
homepage := Some(url("https://github.com/pcejrowski/grasca"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/pcejrowski/grasca"),
    "scm:git:git@github.com:pcejrowski/grasca.git"
  )
)
developers := List(
  Developer(
    id = "pcejrowski",
    name = "Paweł Cejrowski",
    email = "pcejrowski@gmail.com",
    url = url("http://github.com/pcejrowski")
  )
)
licenses := Seq("MIT license" -> url("http://www.opensource.org/licenses/mit-license.php"))

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _), enableCrossBuild = true),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _), enableCrossBuild = true),
  pushChanges
)