package code.with.cal.registerapp


import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {

    private lateinit var editTextSetFullName: EditText
    private lateinit var editTextSetUserName: EditText
    private lateinit var editTextSetEmail: EditText
    private lateinit var editTextSetPassword: EditText
    private lateinit var editTextSetReTypePassword: EditText
    private lateinit var radioGroupSetGender: RadioGroup
    private lateinit var buttonRegister: Button
    private lateinit var buttonShowInfo: Button
    private lateinit var editTextShowFullName: EditText
    private lateinit var editTextShowUserName: EditText
    private lateinit var editTextShowEmail: EditText
    private lateinit var editTextShowPassword: EditText
    private lateinit var editTextShowGender: EditText
    private lateinit var buttonHideInfo: Button
    private lateinit var groupShowInformation: androidx.constraintlayout.widget.Group

    private lateinit var sharedPreferences: SharedPreferences
    private val sharedPrefFile = "User-Save"

    private lateinit var gender: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        editTextSetFullName = findViewById(R.id.edittext_main_set_fullname)
        editTextSetUserName = findViewById(R.id.edittext_main_set_username)
        editTextSetEmail = findViewById(R.id.edittext_main_set_email)
        editTextSetPassword = findViewById(R.id.edittext_main_set_password)
        editTextSetReTypePassword = findViewById(R.id.edittext_main_set_retype_password)
        radioGroupSetGender = findViewById(R.id.radiogroup_main_choice_gender)
        buttonRegister = findViewById(R.id.button_main_register)
        buttonShowInfo = findViewById(R.id.button_main_showInfo)
        editTextShowFullName = findViewById(R.id.edittext_main_show_fullname)
        editTextShowUserName = findViewById(R.id.edittext_main_show_username)
        editTextShowEmail = findViewById(R.id.edittext_main_show_email)
        editTextShowPassword = findViewById(R.id.edittext_main_show_password)
        editTextShowGender = findViewById(R.id.edittext_main_show_gender)
        buttonHideInfo = findViewById(R.id.button_main_hideInfo)
        groupShowInformation = findViewById(R.id.group_show_information)

        setInput()

        buttonRegister.setOnClickListener { register() }

        buttonShowInfo.setOnClickListener { showInfo() }

        buttonHideInfo.setOnClickListener { hideInfo() }

    }

    private fun setInput() {
        val textViewSetFullName: TextView = findViewById(R.id.textview_main_set_fullname)
        val textViewSetUserName: TextView = findViewById(R.id.textview_main_set_username)
        val textViewSetEmail: TextView = findViewById(R.id.textview_main_set_email)
        val textViewSetPassword: TextView = findViewById(R.id.textview_main_set_password)
        val textViewSetReTypePassword: TextView =
            findViewById(R.id.textview_main_set_retype_password)

        editTextSetFullName.doAfterTextChanged {
            if (it?.length!! > 0) textViewSetFullName.visibility =
                View.VISIBLE else textViewSetFullName.visibility = View.INVISIBLE
        }
        editTextSetUserName.doAfterTextChanged {
            if (it?.length!! > 0) textViewSetUserName.visibility =
                View.VISIBLE else textViewSetUserName.visibility = View.INVISIBLE
        }
        editTextSetEmail.doAfterTextChanged {
            if (it?.length!! > 0) textViewSetEmail.visibility =
                View.VISIBLE else textViewSetEmail.visibility = View.INVISIBLE
        }
        editTextSetPassword.doAfterTextChanged {
            if (it?.length!! > 0) textViewSetPassword.visibility =
                View.VISIBLE else textViewSetPassword.visibility = View.INVISIBLE
        }
        editTextSetReTypePassword.doAfterTextChanged {
            if (it?.length!! > 0) textViewSetReTypePassword.visibility =
                View.VISIBLE else textViewSetReTypePassword.visibility = View.INVISIBLE
        }

        radioGroupSetGender.setOnCheckedChangeListener { _, checkedId ->
            val radioButtonSetGender: RadioButton = findViewById(checkedId)
            when (radioButtonSetGender.text) {
                "Female" -> gender = "Female"
                "Male" -> gender = "Male"
            }
        }
    }

    private fun register() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        if (editTextSetFullName.text.isNotEmpty()) editor.putString(
            "full_name_key",
            editTextSetFullName.text.toString()
        )
        else Toast.makeText(this, R.string.text_toast_fullname_is_empty, Toast.LENGTH_SHORT).show()

        if (editTextSetUserName.text.isNotEmpty()) editor.putString(
            "user_name_key",
            editTextSetUserName.text.toString()
        )
        else Toast.makeText(this, R.string.text_toast_username_is_empty, Toast.LENGTH_SHORT).show()


        if (editTextSetEmail.text.isNotEmpty()) editor.putString(
            "email_key",
            editTextSetEmail.text.toString()
        )
        else Toast.makeText(this, R.string.text_toast_email_is_empty, Toast.LENGTH_SHORT).show()

        if (editTextSetPassword.text.isEmpty()) Toast.makeText(
            this,
            R.string.text_toast_password_is_empty,
            Toast.LENGTH_SHORT
        ).show()

        if (editTextSetReTypePassword.text.isEmpty()) Toast.makeText(
            this,
            R.string.text_toast_retype_password_is_empty,
            Toast.LENGTH_SHORT
        ).show()

        if (editTextSetReTypePassword.text.isEmpty()) Toast.makeText(
            this,
            R.string.text_toast_retype_password_is_empty,
            Toast.LENGTH_SHORT
        ).show()

        if (editTextSetReTypePassword.text.toString() != editTextSetPassword.text.toString())
            Toast.makeText(
                this,
                R.string.text_toast_password_not_match_retype_password,
                Toast.LENGTH_SHORT
            ).show()
        else editor.putString("password_key", editTextSetPassword.text.toString())

        if (gender.isNotEmpty()) editor.putString("gender_key", gender)
        else Toast.makeText(this, R.string.text_toast_gender_is_not_choice, Toast.LENGTH_SHORT)
            .show()

        editor.apply()
        editor.commit()

    }

    private fun showInfo() {
        groupShowInformation.visibility = View.VISIBLE

        editTextShowFullName.setText(sharedPreferences.getString("full_name_key", "Fullname"))
        editTextShowUserName.setText(sharedPreferences.getString("user_name_key", "Username"))
        editTextShowEmail.setText(sharedPreferences.getString("email_key", "Email"))
        editTextShowPassword.setText(sharedPreferences.getString("password_key", "Password"))
        editTextShowGender.setText(sharedPreferences.getString("gender_key", "Gender"))

    }

    private fun hideInfo() {
        groupShowInformation.visibility = View.GONE

        editTextShowFullName.setText(R.string.text_register_set_fullname)
        editTextShowUserName.setText(R.string.text_register_set_username)
        editTextShowEmail.setText(R.string.text_register_set_email)
        editTextShowPassword.setText(R.string.text_register_set_password)
        editTextShowGender.setText(R.string.text_register_choice_gender)

    }

}