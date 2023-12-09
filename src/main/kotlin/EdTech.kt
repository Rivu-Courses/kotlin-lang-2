data class Teacher(
    override val name: String,
    override val id: String,
    override val classes: List<String>
): Person

data class Student(
    override val name: String,
    override val id: String,
    override val classes: List<String>
): Person

interface Person {
    val name: String
    val id: String
    val classes: List<String>
}

interface PersonManager<out Xyz: Person> {
    fun getEveryone(): List<Xyz>
    fun getPerson(id: String): Xyz?
    fun getEveryoneByClass(classes: String): List<Xyz>
}

class TeacherManager: PersonManager<Teacher> {


    override fun getEveryone(): List<Teacher> {
        TODO("Not yet implemented")
    }

    override fun getPerson(id: String): Teacher? {
        TODO("Not yet implemented")
    }

    override fun getEveryoneByClass(classes: String): List<Teacher> {
        TODO("Not yet implemented")
    }

}

class StudentManager: PersonManager<Student> {

    override fun getEveryone(): List<Student> {
        TODO("Not yet implemented")
    }

    override fun getPerson(id: String): Student? {
        TODO("Not yet implemented")
    }

    override fun getEveryoneByClass(classes: String): List<Student> {
        TODO("Not yet implemented")
    }

}

class PersonManagerImpl: PersonManager<Person> {
    /*override fun addEmployee(person: Person) {
        TODO("Not yet implemented")
    }*/

    override fun getEveryone(): List<Person> {
        TODO("Not yet implemented")
    }

    override fun getPerson(id: String): Person? {
        TODO("Not yet implemented")
    }

    override fun getEveryoneByClass(classes: String): List<Person> {
        TODO("Not yet implemented")
    }

}

class Comparable<in P: Person> {
    fun compare(p1: P, p2: P): Int = p1.id.compareTo(p2.id)
}

class EdTech {
    val comparable: Comparable<Teacher> = Comparable<Person>()

    val manager: PersonManager<Person> = TeacherManager()

    val people = manager.getEveryone()



    fun getIds(people: List<Person>) : List<String> {
        return people.map {
            it.id
        }.sorted()
    }

    fun doSomething() {
        val sequence: Sequence<Int> = (1..100).asSequence()

        val evenNumbers = sequence.filter {
            it % 2 == 0
        }.sorted()



    }
}

inline fun <reified T> getClass(parameter: T) {
    parameter!!::class.java
}