package io.taptap.money

import android.text.Editable
import android.text.TextWatcher

class MoneyTextWatcher(
    private val areNegativeNumbersEnabled: Boolean = false
) : TextWatcher {

    private val REG_EXP by lazy {
        if (areNegativeNumbersEnabled) {
            Regex("^\$|^[-]?([0-9]{1,3}(?:,?[0-9]{3})*(\\.)?([0-9]{1,2})?)?\$")
        } else {
            Regex("^\$|^([0-9]{1,3}(?:,?[0-9]{3})*(\\.)?([0-9]{1,2})?)?\$")
        }
    }
    /**
     * Indicates the change was caused by ourselves.
     */
    private var isSelfChange = false

    private var beforeText: CharSequence = ""

    override fun beforeTextChanged(before: CharSequence, start: Int, count: Int, after: Int) {
        if (isSelfChange) {
            return
        }
    }

    override fun onTextChanged(after: CharSequence, start: Int, before: Int, count: Int) {
        if (isSelfChange) {
            return
        }
    }

    override fun afterTextChanged(s: Editable) {
        if (isSelfChange) {
            return
        }

        isSelfChange = true
        if (!s.matches(REG_EXP)) {
            s.insert(beforeText)
            isSelfChange = false
            beforeText = s.toString()
            return
        }
        beforeText = s.toString()
        isSelfChange = false
    }
}

private fun Editable.insert(str: CharSequence) {
    delete(0, length)
    append(str)
}
