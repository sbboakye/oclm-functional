package com.sbboakye.oclm.core.db

import cats.effect.Resource
import cats.effect.kernel.MonadCancelThrow
import doobie.hikari.HikariTransactor

class Query[F[_]: MonadCancelThrow, A] {

  def runQuery(
      resource: Resource[F, HikariTransactor[F]],
      queryFunction: HikariTransactor[F] => F[A]
  ): F[A] =
    resource.use(transactor => queryFunction(transactor))

}

object Query {

  def apply[F[_]: MonadCancelThrow, A] = new Query[F, A]

}
