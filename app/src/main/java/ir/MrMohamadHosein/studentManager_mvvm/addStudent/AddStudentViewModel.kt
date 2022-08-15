package ir.MrMohamadHosein.studentManager_mvvm.addStudent

import io.reactivex.Completable
import ir.MrMohamadHosein.studentManager_mvvm.model.MainRepository
import ir.MrMohamadHosein.studentManager_mvvm.model.Student

class AddStudentViewModel
    (private val mainRepository: MainRepository) {

    fun insertNewStudent(student: Student) :Completable {
        return mainRepository.insertStudent(student)
    }

    fun updateStudent(student: Student) :Completable {
        return mainRepository.updateStudent(student)
    }

}