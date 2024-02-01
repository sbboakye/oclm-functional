package com.sbboakye.oclm.core.config

import pureconfig.ConfigReader
import pureconfig.generic.derivation.default.*

case class PostgresConfig(
    driver: String,
    url: String,
    user: String,
    password: String
) derives ConfigReader
