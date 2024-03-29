package com.sbboakye.oclm.core.domain

import java.util.UUID

trait Member {
  def uuid: UUID
  def firstName: String
  def lastName: String
  def otherNames: Option[String]
  def age: Option[Int]
  def gender: String
  def availability: Boolean
  def student: Boolean
  def pioneer: Boolean
  def baptized: Boolean
  def publisher: Boolean
  def ministerialServant: Boolean
  def elder: Boolean
}
