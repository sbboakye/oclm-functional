package com.sbboakye.oclm

import cats.effect.{IO, IOApp}

object Application extends IOApp.Simple {

  override def run: IO[Unit] = IO.println("Hello World!")
}