package com.sbboakye.oclm

import cats.effect.kernel.Resource
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
import com.sbboakye.oclm.core.db.Query.*

object Application extends IOApp.Simple {
  given logger: Logger[IO] = Slf4jLogger.getLogger[IO]

  def getIds(transactor: HikariTransactor[IO]): IO[List[UUID]] =
    sql"select member_id from member"
      .queryWithLabel[UUID]("select only member ids")
      .to[List]
      .transact(transactor)

  override def run: IO[Unit] =
    for {
      _        <- logger.info("and it begins")
      resource <- getTransactor[IO](namespace = "postgres")
      result   <- runQuery(resource, getIds).attempt
      _        <- IO.println(result)
    } yield ()

}
