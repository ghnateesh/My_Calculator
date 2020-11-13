package sai2.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastnumeric: Boolean=false
    var lastdot: Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
        tvInput.append((view as Button).text)
        lastnumeric=true

    }
    fun onClear(view: View){
        tvInput.text=""
        lastnumeric=false
        lastdot=false
    }

    fun onDecimalPoint(view: View){
        if (lastnumeric && !lastdot){
            tvInput.append(".")
            lastnumeric=false
            lastdot=true
        }
    }

    fun onEqual(view: View){
        if (lastnumeric){
            var tvValue = tvInput.text.toString()
            var prefix = ""

            try{

                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text= removezero((one.toDouble() - two.toDouble()).toString())

                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text= removezero((one.toDouble() + two.toDouble()).toString())

                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text= removezero((one.toDouble() * two.toDouble()).toString())

                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text= removezero((one.toDouble() / two.toDouble()).toString())

                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }

    }

    private fun removezero(result: String):String{
        var value = result
        if(result.contains(".0"))
            value = result.substring(0,result.length-2)
        return  value

    }

    fun onOperator(view: View){
        if(lastnumeric && !isoperatoradded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastnumeric=false
            lastdot=false
        }
    }

    private fun isoperatoradded(value: String): Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("+") || value.contains("*") || value.contains("-")
        }
    }
}