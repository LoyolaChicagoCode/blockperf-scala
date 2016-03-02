organization := "edu.luc.cs"

name := "blockperf-scala"

version := "0.2"

scalaVersion := "2.11.7"

crossScalaVersions := Seq("2.10.6", "2.11.7")

licenses += ("GPL-3.0", url("http://www.gnu.org/licenses/gpl-3.0.en.html"))

scalacOptions ++= Seq(
  "-deprecation", "-feature", "-unchecked",
  "-language:higherKinds", "-language:implicitConversions"
)

libraryDependencies ++= Seq(
  "com.novocode" % "junit-interface" % "latest.release" % "test"
)

bintrayVcsUrl := Some("git@github.com:LoyolaChicagoCode/blockperf-scala.git")

