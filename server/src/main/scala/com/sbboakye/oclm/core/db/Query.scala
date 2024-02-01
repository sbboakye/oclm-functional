package com.sbboakye.oclm.core.db

import cats.effect.Resource
import cats.effect.kernel.MonadCancelThrow
import doobie.hikari.HikariTransactor

object Query {

  def runQuery[F[_], A](
      resource: Resource[F, HikariTransactor[F]],
      queryFunction: HikariTransactor[F] => F[A]
  )(using MonadCancelThrow[F]): F[A] =
    resource.use(transactor => queryFunction(transactor))

}
