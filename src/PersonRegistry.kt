class PersonRegistry {
    private val peopleSet = mutableSetOf<Person>()

    fun addPerson(person: Person): Person { //todo: add only String instead of object?
        val existing = peopleSet.find { it == person }
        return if (existing != null) existing
        else { peopleSet.add(person); person }
    }
}