package ir.MrMohamadHosein.studentManager_mvvm.mainScreen

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import ir.MrMohamadHosein.studentManager_mvvm.model.MainRepository
import ir.MrMohamadHosein.studentManager_mvvm.model.Student
import java.util.concurrent.TimeUnit

class MainScreenViewModel(
    private val mainRepository: MainRepository
) {
    val progressBarSubject = BehaviorSubject.create<Boolean>()

    fun getAllStudents() :Single<List<Student>> {
        progressBarSubject.onNext(true)

        return mainRepository
            .getAllStudents()
            //.delay(2 , TimeUnit.SECONDS)
            .doFinally {
                progressBarSubject.onNext(false)
            }

    }

    fun removeStudent(student: Student) :Completable {
        return mainRepository.removeStudent(student)
    }



}