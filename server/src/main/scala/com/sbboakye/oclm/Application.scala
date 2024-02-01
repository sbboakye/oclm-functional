package com.sbboakye.oclm

import cats.effect.{IO, IOApp}
import com.sbboakye.oclm.core.config.PostgresConfig
import com.sbboakye.oclm.core.config.syntax.*
import pureconfig.ConfigSource

object Application extends IOApp.Simple {

  override def run: IO[Unit] =
    ConfigSource.default.at("postgres").loadConfig[IO, PostgresConfig].flatMap { config =>
      IO.println(config)
    }
}
