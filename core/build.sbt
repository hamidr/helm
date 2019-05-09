val circeVersion = "0.10.0"

libraryDependencies ++= Seq(
  "io.circe"                   %% "circe-core"        % circeVersion,
  "io.circe"                   %% "circe-generic"     % circeVersion,
  "io.circe"                   %% "circe-parser"      % circeVersion,
  "org.typelevel"              %% "cats-free"         % "1.3.0",
  "org.typelevel"              %% "cats-effect"       % "1.3.0"
)

addCompilerPlugin("org.spire-math" % "kind-projector" % "0.9.5" cross CrossVersion.binary)
