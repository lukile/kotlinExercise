import java.util.*


data class KotlinPerson (val id: Long,
                         val title: String,
                         val firstName: String,
                         val surname: String,
                         val dateOfBirth: Calendar?) {

    override fun toString() = "$title $firstName $surname"

    var favoriteColor: String? = null

    fun getUpperCaseColor() : String {
         return favoriteColor ?: ""
//        Non compiling
//        return if(favoriteColor == null) "" else favoriteColor.toUpperCase()
    }

    companion object {
        fun getAge(dateOfBirth: Calendar?) : Int? {
            if (dateOfBirth == null) return null

            val today : Calendar = GregorianCalendar()

            val years : Int = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR)
            return if (dateOfBirth.get(Calendar.DAY_OF_YEAR) >= today.get(Calendar.YEAR)) years - 1 else years
        }
    }


    // This method check if age is null and return -1 in case of. Prevent change of age value during if process
    val age : Int?
        get() = getAge(dateOfBirth)

//    Non compiling method
//    val sageAge : Int
//        get() {
//            return if (age != null)
//                age
//            else
//                -1
//    val safeAge : Int
//        }
//        get() = age ?: -1

//    We can even use a local variable
//    val safeAge : Int
//        get() {
//            val localAge = age
//            return if(localAge != null) localAge else -1
//
//        }

    val safeAge : Int
        get() = age ?: -1

    fun getLastLetter(str: String) = str.takeLast(1)

//    Let function tells : if favoriteColor is not null execute
//    what is in let function - getLastLetter of favoriteColor -
//    else return empty string

    fun lastLetterColor() : String {
        return favoriteColor?.let { getLastLetter(it) } ?: ""
    }

    fun getColorType() : String {
        val color = getUpperCaseColor()

        return when (color) {
            "" -> "empty"
            "RED", "BLUE", "YELLOW" -> "rgb"
            else -> "other"
        }
    }

//    Standard
//    fun getColorType() : String {
//        val color = getUpperCaseColor()
//
//        return if (color == "")
//            "empty"
//        else if (color == "RED" || color == "BLUE" || color == "YELLOW")
//            "rgb"
//        else
//            "other"
//    }

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

    var olderPerson = if (john.safeAge > jane.safeAge) john else jane
    println("The older person is  $olderPerson")
}