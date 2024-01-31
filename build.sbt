ThisBuild / version := "0.0.1"

lazy val scala3Version = "3.3.1"
lazy val org           = "com.sbboakye"

lazy val catsVersion        = "2.10.0"
lazy val catsEffectsVersion = "3.5.3"
lazy val logs4catsVersion   = "2.6.0"
lazy val pureconfigVersion  = "0.17.5"
lazy val doobieVersion      = "1.0.0-RC4"

lazy val server = (project in file("server"))
  .settings(
    name         := "server",
    scalaVersion := scala3Version,
    organization := org,
    libraryDependencies ++= Seq(
      "org.typelevel"         %% "cats-core"       % catsVersion,
      "org.typelevel"         %% "cats-effect"     % catsEffectsVersion,
      "org.tpolecat"          %% "doobie-core"     % doobieVersion,
      "org.tpolecat"          %% "doobie-hikari"   % doobieVersion,
      "org.tpolecat"          %% "doobie-postgres" % doobieVersion,
      "org.typelevel"         %% "log4cats-slf4j"  % logs4catsVersion,
      "com.github.pureconfig" %% "pureconfig-core" % pureconfigVersion
    ),
    Compile / mainClass := Some("com.sbboakye.oclm.Application")
  )
