package ir.MrMohamadHosein.studentManager_mvvm.model

import com.google.gson.JsonObject
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("/student")
    fun getAllStudents(): Single<List<Student>>

    @POST("/student")
    fun insertStudent(@Body body: JsonObject): Completable

    @PUT("/student/updating{id}")
    fun updateStudent( @Path("id") id:Long , @Body body :JsonObject ) :Completable

    @DELETE("/student/deleting{id}")
    fun deleteStudent( @Path("id") id:Long ) :Completable

}