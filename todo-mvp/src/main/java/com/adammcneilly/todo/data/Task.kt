package com.adammcneilly.todo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(
        val description: String
) : Parcelable