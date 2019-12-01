package com.example.databindingintent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databindingintent.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myContact = Contact(name = "Alvin",phone = "0168994142")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.contact = myContact
        //setContentView(R.layout.activity_main)
        binding.buttonSave.setOnClickListener {with(binding){

            contact?.name = editText.text.toString()
            contact?.phone = editText2.text.toString()
            invalidateAll()
        }
        }
        binding.buttonSend.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(EXTRA_NAME, binding.contact?.name)
            intent.putExtra(EXTRA_PHONE,binding.contact?.phone)
            //startActivity(intent) //Do not expect result
            startActivityForResult(intent,REQUEST_CODE)//ExpectResult


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                val reply = data?.getStringExtra(EXTRA_REPLY)
                textViewReply.text = String.format("Reply: %s",reply)
            }
        }
            super.onActivityResult(requestCode, resultCode, data)
        }


    companion object{
        const val EXTRA_NAME = "package com.example.databindingintent.Name"
        const val EXTRA_PHONE = "package com.example.databindingintent.Phone"
        const val REQUEST_CODE = 1
        const val EXTRA_REPLY = "package com.example.databindingintent.REPLY"
    }



}//end of the class
