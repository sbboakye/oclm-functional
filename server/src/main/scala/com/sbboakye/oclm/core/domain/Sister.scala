package com.sbboakye.oclm.core.domain

import java.util.UUID

case class Sister(
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
