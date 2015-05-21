scalaModuleSettings

name := "scala-asm"

enablePlugins(GitVersioning)
git.useGitDescribe := true
git.gitDescribedVersion := git.gitDescribedVersion.value.map(_ drop 1) // drop the `v` from the `git describe` string, https://github.com/sbt/sbt-git/issues/67

// Otherwise the artifact has a dependency on scala-library
autoScalaLibrary := false

// Don't add `_<scala-version>` to the jar file name - it's a Java-only project, no Scala cross-versioning needed
crossPaths := false

javacOptions ++= Seq("-g", "-source", "1.5", "-target", "1.6")

// javadoc fails if we pass all of the above
javacOptions in doc := Seq("-source", "1.5")

scalaModuleOsgiSettings

OsgiKeys.exportPackage := Seq(s"scala.tools.asm.*;version=${version.value}")
