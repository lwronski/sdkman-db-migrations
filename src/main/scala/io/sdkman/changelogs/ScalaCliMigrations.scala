package io.sdkman.changelogs

import com.github.mongobee.changeset.{ChangeLog, ChangeSet}
import com.mongodb.client.MongoDatabase

@ChangeLog(order = "062")
class ScalaCliMigrations {
  @ChangeSet(
    order = "001",
    id = "001_add_scala-cli_candidate",
    author = "lwronski"
  )
  def migration001(implicit db: MongoDatabase) =
    Candidate(
      candidate = "scala-cli",
      name = "scala-cli",
      description =
        """Scala-cli combines all the features you need to learn and use Scala in your (simple) projects. "
           |"Scala-cli is intuitive and interactive. """,
      websiteUrl = "https://github.com/VirtuslabRnD/scala-cli"
    ).insert()

  @ChangeSet(
    order = "003",
    id = "003-add_scala-cli_0.0.1_as_default",
    author = "lwrosnki"
  )
  def migration003(implicit db: MongoDatabase) =
    Map(
      MacOSX  -> "scala-cli-x86_64-apple-darwin.gz",
      Linux64 -> "scala-cli-x86_64-pc-linux-static.gz",
      Windows -> "scala-cli-x86_64-pc-win32.zip"
    ).map {
        case (platform, binary) =>
          Version(
            "scala-cli",
            "0.0.1",
            s"https://github.com/VirtuslabRnD/scala-cli/releases/download/v0.0.1/$binary",
            platform
          )
      }
      .toList
      .validate()
      .insert()
}
