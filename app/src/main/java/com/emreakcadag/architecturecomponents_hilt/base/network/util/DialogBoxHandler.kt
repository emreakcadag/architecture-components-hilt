package com.emreakcadag.architecturecomponents_hilt.base.network.util

import android.app.AlertDialog
import com.emreakcadag.architecturecomponents_hilt.base.applicationlistener.BaseActivityRetriever
import com.emreakcadag.architecturecomponents_hilt.base.model.DialogBoxModel

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
class DialogBoxHandler(private val baseActivityRetriever: BaseActivityRetriever) {

    fun showDialogBox(dialogBoxModel: DialogBoxModel?) {
        baseActivityRetriever.activity?.runOnUiThread {
            val builder: AlertDialog.Builder? = baseActivityRetriever.activity.let {
                AlertDialog.Builder(it)
            }

            builder?.apply {
                setMessage(dialogBoxModel?.description)
                setTitle(dialogBoxModel?.title)

                setPositiveButton(dialogBoxModel?.buttonList?.getOrNull(0)?.text) { dialog, _ ->
                    dialog.dismiss()

                }

                setNegativeButton(dialogBoxModel?.buttonList?.getOrNull(1)?.text) { dialog, _ ->
                    dialog.cancel()
                }

                setCancelable(dialogBoxModel?.isCancelable == true)
            }

            val dialog: AlertDialog? = builder?.create()
            dialog?.show()
        }
    }
}