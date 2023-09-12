package com.example.rudonews.data.dataSource.auth

import com.example.rudonews.domain.entity.Departament
import com.example.rudonews.domain.entity.User


interface DataSourceInterface{
    fun getMockDepartments(): List<Departament>

}

class MockDataSource() : DataSourceInterface {

    override fun getMockDepartments(): List<Departament> {
        println("getting departments from mock")

        val departments = mutableListOf<Departament>()

        departments.add(Departament(1, "Android"))
        departments.add(Departament(2, "IOS"))
        departments.add(Departament(3, "Angular"))
        departments.add(Departament(4, "Flutter"))
        departments.add(Departament(5, "Ionic"))
        departments.add(Departament(6, "Back"))
        departments.add(Departament(7, "Frontend"))
        departments.add(Departament(8, "Backend"))
        departments.add(Departament(9, "Web Development"))
        departments.add(Departament(10, "Mobile Development"))
        departments.add(Departament(11, "Database"))
        departments.add(Departament(12, "UI/UX Design"))
        departments.add(Departament(13, "DevOps"))
        departments.add(Departament(14, "Cloud Computing"))
        departments.add(Departament(15, "Data Science"))
        departments.add(Departament(16, "Machine Learning"))
        departments.add(Departament(17, "Artificial Intelligence"))
        departments.add(Departament(18, "Cybersecurity"))
        departments.add(Departament(19, "Game Development"))
        departments.add(Departament(20, "Software Testing"))


        return departments
    }

}
