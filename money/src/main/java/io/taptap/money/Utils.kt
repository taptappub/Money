package io.taptap.money

import android.text.method.DigitsKeyListener
import android.widget.EditText

fun EditText.setMoneyTextWatcher() {
    addTextChangedListener(MoneyTextWatcher())
    keyListener = DigitsKeyListener.getInstance("-0123456789.");
    setOnFocusChangeListener { v, hasFocus ->
        if (!hasFocus) {
            val et = v as EditText
            val s = et.text.toString().toMoney()
            et.setText(s)
        }
    }
}

fun String.toMoney(): String {
    var str = this
    if (this.isEmpty()) {
        str = "0"
    }
    if (str.length == 1 && str[0] == '-') {
        str = "0"
    }
    str = str.removeZerosFromStart()
    when (str.indexOf('.')) {
        -1 -> str = "$str.00"
        str.length - 2 -> str += "0"
        str.length - 1 -> str += "00"
        else -> str
    }
    return str
}

fun String.removeZerosFromStart(): String {
    return toBigDecimal().toString()
}