class PersonRegistry {
    private val peopleSet = mutableSetOf<Person>()

    fun addPerson(person: Person): Person {
        val existing = peopleSet.find { it == person }
        return if (existing != null) existing
        else { peopleSet.add(person); person }
    }

    fun findPersonByName(name: String): Person? = peopleSet.find { it.name.equals(name, true) }
}