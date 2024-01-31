package com.sbboakye.oclm.core.config

import cats.MonadThrow
import pureconfig.{ConfigReader, ConfigSource}
import cats.syntax.all.*
import pureconfig.error.ConfigReaderException

import scala.reflect.ClassTag

object syntax {

  extension (source: ConfigSource)
    def loadConfig[F[_], A](using
        reader: ConfigReader[A],
        F: MonadThrow[F],
        tag: ClassTag[A]
    ): F[A] =
      F.pure(source.load[A]).flatMap {
        case Left(errors) => F.raiseError[A](ConfigReaderException(errors))
        case Right(value) => F.pure(value)
      }
}
