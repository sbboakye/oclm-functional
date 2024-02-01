package com.sbboakye.oclm.core.db

import cats.*
import cats.syntax.all.*
import cats.effect.*

import doobie.*
import doobie.implicits.*
import doobie.hikari.HikariTransactor

import pureconfig.ConfigSource

import com.sbboakye.oclm.core.config.PostgresConfig
import com.sbboakye.oclm.core.config.syntax.*

object DbTransactor {

  def getTransactor[F[_]](namespace: String)(using
      F: Async[F]
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
            connectEC = ec
          )
        } yield xa
        F.pure(dbTransactor)
      }
    ioTransactor
}
