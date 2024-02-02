package com.sbboakye.oclm.core.domain

case class Sister(
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
