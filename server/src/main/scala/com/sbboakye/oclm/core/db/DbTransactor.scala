package com.sbboakye.oclm.core.db

import cats.*
import cats.syntax.all.*
import cats.effect.*

import doobie.*
import doobie.implicits.*
import doobie.hikari.HikariTransactor
import doobie.util.log.{ExecFailure, ProcessingFailure, Success}

import pureconfig.ConfigSource

import org.typelevel.log4cats.Logger

import com.sbboakye.oclm.core.config.PostgresConfig
import com.sbboakye.oclm.core.config.syntax.*

object DbTransactor {

  def printSQLLogHandler[F[_]](using logger: Logger[F]): LogHandler[F] = {
    case Success(sql, args, label, exec, processing) =>
      logger.info(s"$sql, $args, $label, $exec, $processing")
    case ExecFailure(sql, args, label, exec, failure) =>
      logger.error(s"$sql, $args, $label, $exec, $failure")
    case ProcessingFailure(sql, args, label, exec, processing, failure) =>
      logger.error(s"$sql, $args, $label, $exec, $processing, $failure")
  }

  def getTransactor[F[_]](namespace: String)(using
      F: Async[F],
      logger: Logger[F]
  ): F[Resource[F, HikariTransactor[F]]] =
    val ioTransactor =
      ConfigSource.default.at(namespace).loadConfig[F, PostgresConfig].flatMap { config =>
        val dbTransactor: Resource[F, HikariTransactor[F]] = for {
          ec <- ExecutionContexts.fixedThreadPool[F](32)
          xa <- HikariTransactor.newHikariTransactor[F](
            driverClassName = config.driver,
            url = config.url,
            user = config.user,
            pass = config.password,
            connectEC = ec,
            logHandler = Some(printSQLLogHandler[F])
          )
        } yield xa
        F.pure(dbTransactor)
      }
    ioTransactor
}
