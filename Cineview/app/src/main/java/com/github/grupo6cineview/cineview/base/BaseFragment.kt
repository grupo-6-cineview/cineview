package com.github.grupo6cineview.cineview.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.github.grupo6cineview.cineview.extensions.Command


abstract class BaseFragment: Fragment() {

    abstract var command: MutableLiveData<Command>

}