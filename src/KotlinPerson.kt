import java.util.*


data class KotlinPerson (val id: Long,
                         val title: String,
                         val firstName: String,
                         val surname: String,
                         val dateOfBirth: Calendar?) {

    override fun toString() = "$title $firstName $surname"

    companion object {
        fun getAge(dateOfBirth: Calendar?) : Int {
            if (dateOfBirth == null) return -1

            val today : Calendar = GregorianCalendar()

            val years : Int = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR)
            return if (dateOfBirth.get(Calendar.DAY_OF_YEAR) >= today.get(Calendar.YEAR))
                years - 1
            else
                years
        }
    }

    val age : Int
    get() = getAge(dateOfBirth)

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that : KotlinPerson = other as KotlinPerson
        return  id == that.id &&
                title == that.title &&
                firstName == that.firstName &&
                surname == that.surname
    }

    override fun hashCode() = Objects.hash(id, title, firstName, surname)
}

fun main(args: Array<String>) {
    val john = KotlinPerson(1L, "Mr", "John", "Blue", GregorianCalendar(1977, 9, 3))
    val jane = KotlinPerson(2L, "Mrs", "Jane", "Green", null)

    println("$john's age is ${john.age}")
    println("$jane's age is ${jane.age}")

    println("The age of someone born on 3rd May 1988 is ${KotlinPerson.getAge(GregorianCalendar(1988,5,3))}")
}