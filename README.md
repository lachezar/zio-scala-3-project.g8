# zio-scala-3-project.g8

A [Giter8](http://www.foundweekends.org/giter8/) template for Scala 3 + ZIO 2 web service applications.

## Notable dependencies

- Chimney (transformations between domain / dto / dao objects)
- Flyway (database migrations)
- zio-http
- zio-json
- zio-kafka
- zio-prelude (validation of inputs)
- zio-quill (type safe sql queries)

## SBT Plugins

- sbt-dependency-updates (check for new versions of the project dependencies)
- sbt-dotenv (load configuration from a `.env` file automatically when running `sbt run`)
- sbt-native-packager (package the compiled code / add required scripts to run it)
- sbt-scalafmt (code formatter)
- sbt-tpolecat (good scala compiler options defaults)
- sbt-wartremover (linter)

## Usage

```shell script
sbt new lachezar/zio-scala-3-project.g8
```

## Template license

Written in 2023 by Lachezar Yankov.

To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.
