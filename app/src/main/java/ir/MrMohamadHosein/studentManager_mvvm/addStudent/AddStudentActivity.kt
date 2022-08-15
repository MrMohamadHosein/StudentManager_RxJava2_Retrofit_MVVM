package ir.MrMohamadHosein.studentManager_mvvm.addStudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.JsonObject
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ir.MrMohamadHosein.studentManager_mvvm.model.ApiService
import ir.MrMohamadHosein.studentManager_mvvm.model.Student
import ir.MrMohamadHosein.studentManager_mvvm.databinding.ActivityMain2Binding
import ir.MrMohamadHosein.studentManager_mvvm.model.MainRepository
import ir.MrMohamadHosein.studentManager_mvvm.util.asyncRequest
import ir.MrMohamadHosein.studentManager_mvvm.util.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddStudentActivity : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var addStudentViewModel: AddStudentViewModel
    private val compositeDisposable = CompositeDisposable()
    var isInserting = true
    var isFiiled = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMain2)
        addStudentViewModel = AddStudentViewModel(MainRepository())

        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.edtFirstName.requestFocus()




        val testMode = intent.getParcelableExtra<Student>("student")
        isInserting = (testMode == null)
        if (!isInserting) {
            logicUpdateStudent()
        }

        binding.btnDone.setOnClickListener {

            if (isInserting) {
                addNewStudent()
            } else {
                updateStudent(testMode!!)
            }

        }


    }
    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun logicUpdateStudent() {
        binding.btnDone.text = "update"

        val dataFromIntent = intent.getParcelableExtra<Student>("student")!!
        binding.edtScore.setText(dataFromIntent.score.toString())
        binding.edtCourse.setText(dataFromIntent.course)

        val splitedName = dataFromIntent.name.split(" ")
        binding.edtFirstName.setText(splitedName[0])
        binding.edtLastName.setText(splitedName[(splitedName.size - 1)])
    }
    private fun updateStudent(student: Student) {
        val firstName = binding.edtFirstName.text.toString()
        val lastName = binding.edtLastName.text.toString()
        val score = binding.edtScore.text.toString()
        val course = binding.edtCourse.text.toString()

        edttxtfill()

        if (
            isFiiled
        ) {

            addStudentViewModel
                .updateStudent(
                    Student(student.id,firstName + " " + lastName, course, score.toInt())
                )
                .asyncRequest()
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onComplete() {
                        showToast("student updated :)")
                        onBackPressed()
                    }

                    override fun onError(e: Throwable) {
                        showToast("error -> " + e.message ?: "null")
                    }
                })

        } else {
            showToast("لطفا اطلاعات را کامل وارد کنید")
        }
    }
    private fun addNewStudent() {
        val firstName = binding.edtFirstName.text.toString()
        val lastName = binding.edtLastName.text.toString()
        val score = binding.edtScore.text.toString()
        val course = binding.edtCourse.text.toString()

        edttxtfill()

        if (
            isFiiled
        ) {

            addStudentViewModel
                .insertNewStudent(
                    Student(-1,firstName + " " + lastName, course, score.toInt())
                )
                .asyncRequest()
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onComplete() {
                        showToast("student inserted :)")
                        onBackPressed()
                    }

                    override fun onError(e: Throwable) {
                        showToast("error -> " + e.message ?: "null")
                    }

                })


        } else {
            showToast("لطفا اطلاعات را کامل وارد کنید")
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }

        return true
    }


    private fun edttxtfill() {

        val firstName = binding.edtFirstName.text.toString()
        val lastName = binding.edtLastName.text.toString()
        val score = binding.edtScore.text.toString()
        val course = binding.edtCourse.text.toString()

        isFiiled = firstName.isNotEmpty() &&
                lastName.isNotEmpty() &&
                course.isNotEmpty() &&
                score.isNotEmpty()

    }

}