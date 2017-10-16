package test.mmote.com.testapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test_enum.*
import org.jetbrains.anko.toast
import test.mmote.com.testenum.ACType

class TestEnumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_enum)
        button.setOnClickListener {
            test(ACType.getAcType("1"))
        }
        button2.setOnClickListener {
            test(ACType.getAcType("2"))
        }
    }

    fun test(type:ACType){
        when(type){
            ACType.USER_AGREE_COMPLETE->{
                toast("1")
            }
            ACType.USER_AGREE_SERVICE->{
                toast("2")
            }
        }
    }
}
