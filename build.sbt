// Dependencies are needed for Scala Steward to check if there are newer versions
val zioVersion            = "2.0.19"
val zioHttpVersion        = "3.0.0-RC3"
val zioKafkaVersion       = "2.6.0"
val zioJsonVersion        = "0.6.2"
val zioPreludeVersion     = "1.0.0-RC21"
val zioConfigVersion      = "3.0.7"
val zioLoggingVersion     = "2.1.15"
val logbackClassicVersion = "1.4.11"
val quillVersion          = "4.8.0"
val postgresqlVersion     = "42.6.0"
val flywayVersion         = "10.0.1"
val chimneyVersion        = "0.8.2"
val testContainersVersion = "0.41.0"
val zioMockVersion        = "1.0.0-RC11"

// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.
lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(
    name           := "zio-scala-3-project.g8",
    Test / test    := {
      val _ = (Test / g8Test).toTask("").value
    },
    scriptedLaunchOpts ++= List(
      "-Xms1024m",
      "-Xmx1024m",
      "-XX:ReservedCodeCacheSize=128m",
      "-Xss2m",
      "-Dfile.encoding=UTF-8",
    ),
    resolvers += Resolver.url(
      "typesafe",
      url("https://repo.typesafe.com/typesafe/ivy-releases/"),
    )(Resolver.ivyStylePatterns),
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
      "ch.qos.logback" % "logback-classic"            % logbackClassicVersion,
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
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework")),
  )
