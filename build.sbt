name := "save_me_dad_ultra"

version := "1.0"

scalaVersion := "2.11.4"

fork in Compile := true

unmanagedResourceDirectories in Compile += file("assets")

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % "2.11.4",
  "com.badlogicgames.gdx" % "gdx" % "1.4.1",
  "com.badlogicgames.gdx" % "gdx-backend-lwjgl" % "1.4.1" ,
  "com.badlogicgames.gdx" % "gdx-platform" % "1.4.1" classifier "natives-desktop",
  "com.badlogicgames.gdx" % "gdx-box2d-platform" % "1.4.1" classifier "natives-desktop",
  "com.badlogicgames.gdx" % "gdx-freetype-platform" % "1.4.1" classifier "natives-desktop"
)