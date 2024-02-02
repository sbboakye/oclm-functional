package com.sbboakye.oclm

import cats.Id
import cats.effect.{IO, IOApp}
import doobie.*
import doobie.implicits.*
import doobie.postgres.*
import doobie.postgres.implicits.*
import doobie.hikari.HikariTransactor
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

import java.util.UUID
import com.sbboakye.oclm.core.db.DbTransactor.*
import com.sbboakye.oclm.core.db.Query
import com.sbboakye.oclm.core.domain.{Brother, Brothers, Sister}

object Application extends IOApp.Simple {
  given logger: Logger[IO] = Slf4jLogger.getLogger[IO]

  val brothersQuery: Query[IO, Brother] = Query[IO, Brother]
//  val sisterQuery: Query[IO, Sister]    = Query[IO, Sister]

  override def run: IO[Unit] =
    for {
      _        <- logger.info("and it begins")
      resource <- getTransactor[IO](namespace = "postgres")
//      result   <- brothersQuery.runQuery(resource, Brothers.make[IO].findAll).attempt
      result <- brothersQuery
        .runQuery(
          resource,
          Brothers.make[IO].findById(UUID.fromString("c5e71c9f-e85a-4c09-b545-6008771185ce"))
        )
        .attempt
      _ <- IO.println(result)
    } yield ()

}
