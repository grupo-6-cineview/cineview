package com.github.grupo6cineview.cineview.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData


abstract class BaseFragment: Fragment() {

    abstract var command: MutableLiveData<Command>
}