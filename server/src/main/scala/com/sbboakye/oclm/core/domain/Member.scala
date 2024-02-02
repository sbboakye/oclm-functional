package com.sbboakye.oclm.core.domain

trait Member {
  def firstName: String
  def lastName: String
  def otherNames: Option[String]
  def age: Integer
  def gender: Option[String]
  def availability: String
  def student: Boolean
  def pioneer: Boolean
  def baptized: Boolean
  def publisher: Boolean
  def ministerialServant: Boolean
  def elder: Boolean
}
