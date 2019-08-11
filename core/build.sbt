val circeVersion = "0.11.1"

libraryDependencies ++= Seq(
  "io.circe"                   %% "circe-core"        % circeVersion,
  "io.circe"                   %% "circe-generic"     % circeVersion,
  "io.circe"                   %% "circe-parser"      % circeVersion,
  "org.scalacheck"             %% "scalacheck"        % "1.14.0" % "test",
  "org.scalatest"              %% "scalatest"         % "3.0.8"  % "test",
  "org.typelevel"              %% "cats-free"         % "1.3.0",
  "org.typelevel"              %% "cats-effect"       % "1.3.0"
)

addCompilerPlugin("org.spire-math" % "kind-projector" % "0.9.5" cross CrossVersion.binary)
