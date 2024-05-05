package com.fallingwords.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/** The app level file of the project which creates dependencies using Hilt **/
@HiltAndroidApp
class FallingWordsApp : Application()