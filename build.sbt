organization := "com.github.pcejrowski"
name := "grasca"
version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.1"
crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.1")

scalacOptions ++= Seq("-feature", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.3.0"
libraryDependencies += "org.json4s" %% "json4s-native" % "3.5.2"
libraryDependencies += "com.github.nikita-volkov" % "sext" % "0.2.4"

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

pomExtra :=
  <url>https://github.com/pcejrowski/grasca</url>
    <licenses>
      <license>
        <name>MIT license</name>
        <url>http://www.opensource.org/licenses/mit-license.php</url>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:pcejrowski/grasca.git</url>
      <connection>scm:git:git@github.com:pcejrowski/grasca.git</connection>
    </scm>
    <developers>
      <developer>
        <id>pcejrowski</id>
        <name>Paweł Cejrowski</name>
        <url>github.com/pcejrowski</url>
      </developer>
    </developers>