package com.lpedrosa

trait CRUDRepository[T, U] {
  def createOrUpdate(obj: T): T
  def delete(id: U): Boolean
  def deleteAll: Boolean
  def get(id: U): Option[T]
  def getAll(): Option[List[T]]
}
