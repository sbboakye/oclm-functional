package com.sbboakye.oclm.core.db

import cats.effect.Resource
import cats.effect.kernel.MonadCancelThrow
import doobie.Read
import doobie.hikari.HikariTransactor
import doobie.implicits.*
import doobie.util.compat.FactoryCompat

class Query[F[_]: MonadCancelThrow, A: Read] {

  def runQuery(
      resource: Resource[F, HikariTransactor[F]],
      queryFunction: HikariTransactor[F] => F[A]
  ): F[A] =
    resource.use(transactor => queryFunction(transactor))

  def getBrothers[B[_]](transactor: HikariTransactor[F])(using f: FactoryCompat[A, B[A]]): F[B[A]] =
    sql"SELECT * FROM MEMBER"
      .queryWithLabel[A]("select only brothers with all information")
      .to[B]
      .transact(transactor)

}

object Query {

  def apply[F[_]: MonadCancelThrow, A: Read] = new Query[F, A]

}
