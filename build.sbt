// Dependencies are needed for Scala Steward to check if there are newer versions
val zioVersion            = "2.1.7"
val zioHttpVersion        = "3.0.0-RC9"
val zioKafkaVersion       = "2.7.1"
val zioJsonVersion        = "0.7.2"
val zioPreludeVersion     = "1.0.0-RC29"
val zioConfigVersion      = "4.0.2"
val zioLoggingVersion     = "2.3.1"
val logbackClassicVersion = "1.5.7"
val quillVersion          = "4.8.4"
val postgresqlVersion     = "42.7.4"
val flywayVersion         = "10.17.2"
val chimneyVersion        = "1.4.0"
val testContainersVersion = "0.41.4"
val zioMockVersion        = "1.0.0-RC12"

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
