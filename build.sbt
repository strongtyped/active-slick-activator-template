name := """slick-active-record"""

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "com.typesafe.play" 		%% 	"play-slick" 	% "0.8.0",
  "com.typesafe.slick"      %%  "slick"         % "2.1.0",
  "io.strongtyped"			%% 	"active-slick"	% "0.2.2"
)