package com.sbboakye.oclm.core.domain

import java.util.UUID

case class Sister(
    uuid: UUID,
    firstName: String,
    lastName: String,
    otherNames: Option[String],
    age: Integer,
    gender: Option[String],
    availability: String,
    student: Boolean,
    pioneer: Boolean,
    baptized: Boolean,
    publisher: Boolean,
    ministerialServant: Boolean,
    elder: Boolean
) extends Member
