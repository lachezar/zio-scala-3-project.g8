val zioVersion            = "2.1.19"
val zioHttpVersion        = "3.0.1"
val zioKafkaVersion       = "2.8.2"
val zioJsonVersion        = "0.7.7"
val zioPreludeVersion     = "1.0.0-RC41"
val zioConfigVersion      = "4.0.4"
val zioLoggingVersion     = "2.5.0"
val logbackClassicVersion = "1.5.18"
val quillVersion          = "4.8.5"
val postgresqlVersion     = "42.7.7"
val flywayVersion         = "11.10.0"
val chimneyVersion        = "1.8.1"
val testContainersVersion = "0.43.0"
val zioMockVersion        = "1.0.0-RC12"

lazy val quillNamingStrategy =
  (project in file("quill"))
    .settings(
      name                                  := "zioapp-quill",
      libraryDependencies ++= "io.getquill" %% "quill-jdbc-zio" % quillVersion :: Nil,
    )

lazy val root = (project in file("."))
  .settings(
    inThisBuild(
      List(
        organization := "$organization$",
        scalaVersion := "$scala_version$",
      )
    ),
    name                    := "$name$",
    libraryDependencies ++= Seq(
      "io.getquill"   %% "quill-jdbc-zio"             % quillVersion excludeAll (
        ExclusionRule(organization = "org.scala-lang.modules")
      ),
      "org.postgresql" % "postgresql"                 % postgresqlVersion,
      "org.flywaydb"   % "flyway-core"                % flywayVersion,
      "org.flywaydb"   % "flyway-database-postgresql" % flywayVersion,
      "dev.zio"       %% "zio"                        % zioVersion,
      "dev.zio"       %% "zio-http"                   % zioHttpVersion,
      "dev.zio"       %% "zio-kafka"                  % zioKafkaVersion,
      "dev.zio"       %% "zio-config"                 % zioConfigVersion,
      "dev.zio"       %% "zio-config-typesafe"        % zioConfigVersion,
      "dev.zio"       %% "zio-config-magnolia"        % zioConfigVersion,
      "dev.zio"       %% "zio-json"                   % zioJsonVersion,
      "io.scalaland"  %% "chimney"                    % chimneyVersion,
      "dev.zio"       %% "zio-prelude"                % zioPreludeVersion,

      // logging
      "dev.zio"       %% "zio-logging"       % zioLoggingVersion,
      "dev.zio"       %% "zio-logging-slf4j" % zioLoggingVersion,
      "ch.qos.logback" % "logback-classic"   % logbackClassicVersion,

      // test
      "dev.zio"      %% "zio-test"                        % zioVersion            % Test,
      "dev.zio"      %% "zio-test-sbt"                    % zioVersion            % Test,
      "dev.zio"      %% "zio-test-junit"                  % zioVersion            % Test,
      "dev.zio"      %% "zio-mock"                        % zioMockVersion        % Test,
      "com.dimafeng" %% "testcontainers-scala-postgresql" % testContainersVersion % Test,
      "dev.zio"      %% "zio-test-magnolia"               % zioVersion            % Test,
    ),
    testFrameworks          := Seq(new TestFramework("zio.test.sbt.ZTestFramework")),
    // try using the `tpolecatScalacOptions` configuration key for any additional compiler flags
    Compile / doc / sources := Seq.empty,
  )
  .dependsOn(quillNamingStrategy)
  .enablePlugins(JavaAppPackaging, UniversalPlugin)

addCommandAlias("fmt", "all scalafmtSbt scalafmtAll")

wartremoverErrors ++= Warts.unsafe diff Seq(Wart.Any, Wart.DefaultArguments)
