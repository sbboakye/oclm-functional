package com.sbboakye.oclm.core.db

import cats.effect.Resource
import cats.effect.kernel.MonadCancelThrow
import doobie.*
import doobie.hikari.HikariTransactor

class Query[F[_]: MonadCancelThrow, A: Read] {

  def runQuery[B[_]](
      resource: Resource[F, HikariTransactor[F]],
      queryFunction: HikariTransactor[F] => F[B[A]]
  ): F[B[A]] =
    resource.use(transactor => queryFunction(transactor))
}

object Query {
  def apply[F[_]: MonadCancelThrow, A: Read] = new Query[F, A]
}
