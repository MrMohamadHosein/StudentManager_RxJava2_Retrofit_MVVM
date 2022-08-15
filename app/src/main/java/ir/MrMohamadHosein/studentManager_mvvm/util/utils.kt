package ir.MrMohamadHosein.studentManager_mvvm.util

import com.google.gson.JsonObject
import ir.MrMohamadHosein.studentManager_mvvm.model.Student

fun studentToJsonObject(student: Student) :JsonObject {

    val jsonObject = JsonObject()
    jsonObject.addProperty("name", student.name)
    jsonObject.addProperty("course", student.course)
    jsonObject.addProperty("score", student.score)
    return jsonObject

}