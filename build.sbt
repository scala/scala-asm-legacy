scalaModuleSettings

name         := "scala-asm"

version      := "5.0.3-scala-2"

// Otherwise the artifact has a dependency on scala-library
autoScalaLibrary := false

// Don't add `_<scala-version>` to the jar file name - it's a Java-only project, no Scala cross-versioning needed
crossPaths   := false

scalaModuleOsgiSettings

OsgiKeys.exportPackage := Seq(s"scala.tools.asm.*;version=${version.value}")
