package com.dhananjaysaini.calculator2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dhananjaysaini.calculator2.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //Numbers
        binding?.one?.setOnClickListener { appendOnExpression("1", true)}
        binding?.two?.setOnClickListener { appendOnExpression("2", true)}
        binding?.three?.setOnClickListener { appendOnExpression("3", true)}
        binding?.four?.setOnClickListener { appendOnExpression("4", true)}
        binding?.five?.setOnClickListener { appendOnExpression("5", true)}
        binding?.six?.setOnClickListener { appendOnExpression("6", true)}
        binding?.seven?.setOnClickListener { appendOnExpression("7", true)}
        binding?.eight?.setOnClickListener { appendOnExpression("8", true)}
        binding?.nine?.setOnClickListener { appendOnExpression("9", true)}
        binding?.zero?.setOnClickListener { appendOnExpression("0", true)}
        binding?.dot?.setOnClickListener { appendOnExpression(".", true)}

        //Operations

        binding?.equal?.setOnClickListener { appendOnExpression("=", true)}
        binding?.plus?.setOnClickListener { appendOnExpression("+", true)}
        binding?.minus?.setOnClickListener { appendOnExpression("-", true)}
        binding?.multiply?.setOnClickListener { appendOnExpression("*", true)}
        binding?.divide?.setOnClickListener { appendOnExpression("/", true)}
        binding?.open?.setOnClickListener { appendOnExpression("(", true)}
        binding?.close?.setOnClickListener { appendOnExpression(")", true)}

        binding?.clear?.setOnClickListener {
            binding?.expression?.text = ""
            binding?.result?.text = ""
        }

        binding?.back?.setOnClickListener {
            val string = binding?.expression?.text.toString()
            if (string.isNotEmpty()){
                binding?.expression?.text = string.substring(0, string.length-1 )

            }
            binding?.result?.text= ""
        }
              binding?.equal?.setOnClickListener{
                  try{
                      val  expression = ExpressionBuilder(binding?.expression?.text.toString()).build()
                      val result = expression.evaluate()
                      val longResult = result.toLong()
                      if(result == longResult.toDouble())
                          binding?.result?.text = longResult.toString()
                      else
                          binding?.result?.text = result.toString()
                  }
                  catch (e: Exception){
                     Log.d("Exception", " message : " + e.message )
                  }
              }
    }

    fun appendOnExpression(string: String, canClear: Boolean){

        if(binding?.result?.text?.isNotEmpty()!!){
            binding?.expression?.text = ""
        }


        if (canClear){
            binding?.result?.text = ""
            binding?.expression?.append(string)
         } else {
            binding?.expression?.append(binding?.result?.text)
            binding?.expression?.append(string)
            binding?.result?.text=""
         }
    }
}