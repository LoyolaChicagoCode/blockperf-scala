organization := "edu.luc.cs"

name := "blockperf"

version := "0.4.3"

scalaVersion := "2.11.7"

crossScalaVersions := Seq("2.10.6", "2.11.7")

licenses += ("GPL-3.0", url("http://www.gnu.org/licenses/gpl-3.0.en.html"))

scalacOptions ++= Seq(
  "-deprecation", "-feature", "-unchecked",
  "-language:higherKinds", "-language:implicitConversions"
)

resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  "com.novocode" % "junit-interface" % "latest.release" % "test",
  "com.squants"  %% "squants"  % "0.6.1-SNAPSHOT"
)

bintrayVcsUrl := Some("git@github.com:LoyolaChicagoCode/blockperf-scala.git")

