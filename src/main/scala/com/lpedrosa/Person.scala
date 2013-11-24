package com.lpedrosa

import scala.collection.mutable.{Map,HashMap}

case class Person(id: Option[Integer] = None, name: String, address: String)

class PersonRepository extends CRUDRepository[Person, Integer] {
  var persistenceUnit: Map[Integer, Person] = HashMap.empty

  override def createOrUpdate(obj: Person): Person = {
    val oldObj: Option[Person] = for {
      oldId <- obj.id
      p <- persistenceUnit.get(oldId)
    } yield p

    oldObj match {
      case Some(person) => { 
        persistenceUnit.put(person.id.get, obj)
        obj
      }
      case None => {
        val newId = persistenceUnit.size + 1
        val objUpdatedId = obj.copy(id = Some(newId))
        persistenceUnit.put(newId, objUpdatedId)
        objUpdatedId
      }
    }
  }

  override def delete(id: Integer): Boolean = persistenceUnit.remove(id).isDefined

  override def deleteAll: Boolean = {
    persistenceUnit.clear 
    persistenceUnit.size == 0
  }

  override def get(id: Integer): Option[Person] = persistenceUnit.get(id)

  override def getAll(): Option[List[Person]] = {
    val people = persistenceUnit.toList map { _._2 } 
    if(people.isEmpty) None else Some(people)
  }
}
