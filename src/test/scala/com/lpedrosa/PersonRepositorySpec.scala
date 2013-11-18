package com.lpedrosa

import org.specs2.mutable._
import org.specs2.specification.Scope


class PersonRepositorySpec extends Specification { 

  "An empty PersonRepository" should {
  
    "return None when fetching an id" in new emptyRepository {
      val anId = 4
      personRepository.get(anId) must beNone
    }

    "return None when fetching all records" in new emptyRepository {
      personRepository.getAll() must beNone
    }

    "return false when deleting an id" in new emptyRepository {
      val anId = 4
      personRepository.delete(anId) must beFalse
    }

    "return the same person when inserted into it" in new emptyRepository {
      val person = Person(name="Bob", address="Anywhere Rd")
      personRepository.createOrUpdate(person) must beLike { case Person(id,name,address) => 
        name must beEqualTo(person.name) and (address must beEqualTo(person.address)) and (id must beSome[Integer])
      }
    }
  }

  trait emptyRepository extends Scope {
    lazy val personRepository = new PersonRepository
  }
  
  // this is actually working for options
  /*
      personRepository.createOrUpdate(person) must beSome[Person].which( p => 
        p.name must beEqualTo(person.name) and (p.address must beEqualTo(person.address))
      )
  */
}
