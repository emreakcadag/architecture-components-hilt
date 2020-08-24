package com.emreakcadag.architecturecomponents_hilt.base.network.util

import android.app.AlertDialog
import com.emreakcadag.architecturecomponents_hilt.base.applicationlistener.BaseActivityRetriever
import com.emreakcadag.architecturecomponents_hilt.base.enum.ButtonActionType.*
import com.emreakcadag.architecturecomponents_hilt.base.extension.logDebug
import com.emreakcadag.architecturecomponents_hilt.base.extension.tryCatch
import com.emreakcadag.architecturecomponents_hilt.base.model.DialogBoxModel
import com.emreakcadag.architecturecomponents_hilt.base.navigator.Navigator
import javax.inject.Inject

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
class DialogBoxHandler
@Inject constructor(
    private val baseActivityRetriever: BaseActivityRetriever,
    private val navigator: Navigator
) {

    fun showDialogBox(dialogBoxModel: DialogBoxModel?) {
        baseActivityRetriever.activity?.runOnUiThread {
            val builder = baseActivityRetriever.activity.let {
                AlertDialog.Builder(it)
            }

            builder.apply {
                val button0 = dialogBoxModel?.buttonList?.getOrNull(0)
                val button1 = dialogBoxModel?.buttonList?.getOrNull(1)
                val button2 = dialogBoxModel?.buttonList?.getOrNull(2)

                setMessage(dialogBoxModel?.description)
                setTitle(dialogBoxModel?.title)

                setPositiveButton(button0?.text) { dialog, _ ->
                    if (dialogBoxModel?.isCancelable == true) dialog.dismiss()

                    when (button0?.actionType) {
                        GO_BACK -> Unit
                        DISMISS -> Unit
                        REQUEST -> Unit // TODO emreakcadag request implementation
                        NAVIGATE -> Unit  // todo emreakcadag navigator.openActivityWithCode()
                        DROP_SESSION -> Unit // todo emreakcadag
                        null -> Unit
                    }
                }

                setNegativeButton(button1?.text) { dialog, _ ->
                    if (dialogBoxModel?.isCancelable == true) dialog.cancel()
                }

                // setNeutralButton()

                setCancelable(dialogBoxModel?.isCancelable == true)
            }

            val dialog = builder.create()

            tryCatch({
                dialog.show()
            }, {
                logDebug(it, "ERROR dialog.show()", true)
            })
        }
    }
}