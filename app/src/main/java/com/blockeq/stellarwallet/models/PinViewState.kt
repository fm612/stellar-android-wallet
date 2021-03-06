package com.blockeq.stellarwallet.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

enum class PinType {
    CREATE, LOGIN, VIEW_PHRASE, VIEW_SEED, CHECK,
}

@Parcelize
class PinViewState (var type: PinType, var message: String, var pin: String, var mnemonic: String, var passphrase: String?): Parcelable
