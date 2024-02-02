package com.sbboakye.oclm.core.domain

import cats.effect.IO
import doobie.*
import doobie.hikari.HikariTransactor
import doobie.implicits.*
import doobie.postgres.*
import doobie.postgres.implicits.*
import com.sbboakye.oclm.core.db.Query
import com.sbboakye.oclm.core.db.DbTransactor.getTransactor
import doobie.util.compat.FactoryCompat
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

import java.util.UUID

case class Brother(
    uuid: UUID,
    firstName: String,
    lastName: String,
    otherNames: Option[String],
    age: Option[Int],
    gender: String,
    availability: Boolean,
    student: Boolean,
    pioneer: Boolean,
    baptized: Boolean,
    publisher: Boolean,
    ministerialServant: Boolean,
    elder: Boolean
) extends Member
