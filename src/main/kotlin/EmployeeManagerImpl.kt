import Designation.Executive
import Designation.Manager

class EmployeeManagerImpl : EmployeeManager {

    private val employees: MutableMap<String, Employee> = mutableMapOf()

    override fun addEmployee(employee: Employee) {
        employees[employee.id] = employee
    }

    override fun getAllEmployees(): List<Employee> {
        return employees.values.toList()
    }

    override fun getEmployee(id: String): Employee? {
        return employees.getOrDefault(id, null)
    }

    override fun getEmployeesByDesignation(designation: Designation): List<Employee> {
        return employees.values.filter {
            when (designation) {
                is Manager -> it.designNation is Manager
                is Executive -> it.designNation is Executive
            }
        }
    }
}