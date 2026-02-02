addSbtPlugin("com.github.sbt"            % "sbt-native-packager"    % "1.11.7")
addSbtPlugin("org.typelevel"             % "sbt-tpolecat"           % "0.5.2")
addSbtPlugin("org.scalameta"             % "sbt-scalafmt"           % "2.5.6")
addSbtPlugin("org.jmotor.sbt"            % "sbt-dependency-updates" % "1.2.9")
addSbtPlugin("nl.gn0s1s"                 % "sbt-dotenv"             % "3.2.0")
addSbtPlugin("org.wartremover"           % "sbt-wartremover"        % "3.5.5")
addSbtPlugin("org.foundweekends.giter8" %% "sbt-giter8"             % "0.18.0")
libraryDependencies += "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value
