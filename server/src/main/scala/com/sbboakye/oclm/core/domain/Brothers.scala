package com.sbboakye.oclm.core.domain

import cats.effect.MonadCancelThrow
import doobie.*
import doobie.hikari.HikariTransactor
import doobie.implicits.*
import doobie.postgres.*
import doobie.postgres.implicits.*
import doobie.util.compat.FactoryCompat

import java.util.UUID

trait Brothers[F[_]] {

  def findById(memberId: UUID)(transactor: HikariTransactor[F]): F[Option[Brother]]

  def findAll(transactor: HikariTransactor[F]): F[List[Brother]]

}

object Brothers {

  def make[F[_]: MonadCancelThrow]: Brothers[F] =
    new Brothers[F]:
      override def findById(memberId: UUID)(transactor: HikariTransactor[F]): F[Option[Brother]] =
        val select = fr"SELECT * FROM member"
        val where  = fr"WHERE member_id = ${memberId} AND gender = 'Male'"
        val label  = "get brother by his id"

        val query = select ++ where
        query
          .queryWithLabel[Brother](label)
          .option
          .transact(transactor)

      override def findAll(transactor: HikariTransactor[F]): F[List[Brother]] =
        val select = fr"SELECT * FROM member"
        val where  = fr"WHERE gender = 'Male'"
        val label  = "get all brothers"

        val query = select ++ where

        query
          .queryWithLabel[Brother](label)
          .to[List]
          .transact(transactor)
}
