import arrow.core.None
import arrow.core.Option

interface EmployeeManager {
    fun addEmployee(employee: Employee)
    fun getAllEmployees(): List<Employee>
    fun getEmployee(id: String): Employee?
    fun getEmployeesByDesignation(designation: Designation): List<Employee>
}

data class Employee(
    val id: String,
    val firstName: String = "",
    val lastName: String = "",
    val designNation: Designation = Designation.Executive,
    val manager: Option<Employee> = None,
)

sealed class Designation {
    object Executive: Designation()
    data class Manager(
        val reportees: List<String>
    ): Designation()
}
