addSbtPlugin("com.github.sbt"            % "sbt-native-packager"    % "1.9.16")
addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat"           % "0.4.2")
addSbtPlugin("org.scalameta"             % "sbt-scalafmt"           % "2.5.0")
addSbtPlugin("org.jmotor.sbt"            % "sbt-dependency-updates" % "1.2.7")
addSbtPlugin("nl.gn0s1s"                 % "sbt-dotenv"             % "3.0.0")
addSbtPlugin("org.wartremover"           % "sbt-wartremover"        % "3.1.4")
addSbtPlugin("org.foundweekends.giter8" %% "sbt-giter8"             % "0.16.2")
libraryDependencies += "org.scala-sbt"  %% "scripted-plugin"        % sbtVersion.value
