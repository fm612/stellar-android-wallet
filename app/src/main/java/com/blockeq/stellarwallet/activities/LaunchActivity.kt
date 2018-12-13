package com.blockeq.stellarwallet.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.blockeq.stellarwallet.R
import com.blockeq.stellarwallet.models.MnemonicType
import kotlinx.android.synthetic.main.activity_login.*

class LaunchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupUI()
    }

    //region User Interface
    private fun setupUI() {

        createWalletButton.setOnClickListener {
            showCreateDialog()
        }

        recoverWalletButton.setOnClickListener {
            showRecoverDialog()
        }
    }

    private fun showCreateDialog() {
        val builder = AlertDialog.Builder(this@LaunchActivity)
        val walletLengthList = listOf(getString(R.string.create_word_option_1), getString(R.string.create_word_option_2)).toTypedArray()
        builder.setTitle(getString(R.string.create_wallet))
                .setItems(walletLengthList) { _, which ->
                    // The 'which' argument contains the index position
                    // of the selected item

                    val walletLength = if (which == 0) {
                        MnemonicType.WORD_12
                    } else {
                        MnemonicType.WORD_24
                    }

                    startActivity(MnemonicActivity.newCreateMnemonicIntent(this, walletLength))
                }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showRecoverDialog() {
        val builder = AlertDialog.Builder(this@LaunchActivity)
        val walletLengthList = listOf(getString(R.string.recover_from_phrase), getString(R.string.recover_from_seed)).toTypedArray()
        builder.setTitle(getString(R.string.recover_wallet))
                .setItems(walletLengthList) { _, which ->
                    // The 'which' argument contains the index position
                    // of the selected item

                    val isPhraseRecovery = (which == 0)

                    val intent = Intent(this, RecoverWalletActivity::class.java)
                    intent.putExtra("isPhraseRecovery", isPhraseRecovery)
                    startActivity(intent)
                }
        val dialog = builder.create()
        dialog.show()
    }

    //endregion
}
