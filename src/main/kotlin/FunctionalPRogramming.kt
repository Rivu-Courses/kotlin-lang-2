import arrow.core.Either
import arrow.core.Ior
import arrow.core.None
import arrow.core.Option
import arrow.core.right
import kotlin.random.Random

//What's FP?

//Function => Math
//f(x) = x*2

/* Concepts in FP
- Pure Function
    - Side Effects
- High Order Functions and Lambda
- Function Caching
- Immutability
- Function Composition
- Category Theory
- Map vs Flatmap
 */


//Pure Function
fun addPure(n1: Int, n2: Int): Int = n1+n2

fun add(n1: Int, n2: Int): Ior<Int, String> {
    val log = "input numbers $n1 $n2"
    return Ior.Both(n1+n2, log)
}

/*
1. If inputs remain same, output should be the exactly same
2. Should not change anything outside the function scope
3. Should not do anything other than computing result for the input
4. Function should be cachable
*/

// Immutability
val someVariable: Int = 3
val someVariable2: Int
    get() {
        val random = Random(10)
        return random.nextInt()
    }

data class SomeDataFP(
    val something: Int,
    val somethingElse: Int
)

fun demonstraitImmutability() {
    val something = SomeDataFP(12, 23)
    val somethingElse = something.copy(10)
}


// Function Composition
fun multiply(n1: Int, n2: Int): Int = n1*n2

fun addNMultiply(x: Int, y: Int, z: Int): Int = multiply(addPure(x,y), z)

//f((x,y),z) = f(f(x,y),z)

//Category Theory

fun getIdsOfEmplyees(dataList: List<Employee>): List<String> {
    return dataList.map {
        it.id
    }
}
fun getIdsOfEmployees(dataList: List<List<Employee>>): List<String> {
    return dataList.flatMap { input: List<Employee> ->
        input.map {
            it.id
        }
    }
}


fun main() {
    val dataList: List<List<Employee>> = listOf(
        //1
        listOf(
            Employee("1"),
            Employee("2"),
            Employee("3"),
            Employee("4"),
        ),
        //2
        listOf(
            Employee("x"),
            Employee("5"),
            Employee("y"),
            Employee("z"),
        )
    )

    val ids = getIdsOfEmployees(dataList)

    println(ids)

    val employee = Employee(
        id = "jdnjksnbdc",
        manager = Option(Employee(id = "sdccnkdsnck"))
    )

    val managerNameOption = getManagerNameOptional(employee)

    val fetchedEmployee = fetchEmployee()

    if (fetchedEmployee.isRight()) {
        //... Logic for Happy Case
    } else {
        //... Error Handling Logic Goes Here
    }

}

//Monad
//FP Structure, Error Handling, SideEffects.
// - Option
// - Either
// - Ior



/*
fun getManagerName(employee: Employee): String? {
    return if (employee.manager != null) employee.manager.firstName + " " + employee.manager.lastName else null
}*/

fun getManagerNameOptional(employee: Employee): Option<String> {
    return employee.manager.map {
        it.firstName + " " + it.lastName
    }
}


fun fetchEmployee(): Either<Throwable, Employee> {
    return Either.Right(Employee(""))
    return Either.Left(Error(""))
}