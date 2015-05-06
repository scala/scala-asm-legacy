scalaModuleSettings

name         := "scala-asm"

version      := "5.0.3-scala"

// Don't add `_<scala-version>` to the jar file name - it's a Java-only project, no Scala cross-versioning needed
crossPaths   := false

scalaModuleOsgiSettings

OsgiKeys.exportPackage := Seq(s"scala.tools.asm.*;version=${version.value}")
