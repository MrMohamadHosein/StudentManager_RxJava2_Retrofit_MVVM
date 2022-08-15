package ir.MrMohamadHosein.studentManager_mvvm.model

import io.reactivex.Completable
import io.reactivex.Single
import ir.MrMohamadHosein.studentManager_mvvm.util.BASE_URL
import ir.MrMohamadHosein.studentManager_mvvm.util.studentToJsonObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainRepository {
    private val apiService: ApiService

    init {

        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

    }

    fun getAllStudents(): Single<List<Student>> {
        return apiService.getAllStudents()
    }

    fun insertStudent(student: Student): Completable {
        return apiService.insertStudent(studentToJsonObject(student))
    }

    fun updateStudent(student: Student): Completable {
        return apiService.updateStudent(student.id, studentToJsonObject(student))
    }

    fun removeStudent(student: Student): Completable {
        return apiService.deleteStudent(student.id)
    }


    /* U CAN catch Data with DAO room here besides retrofit */

}