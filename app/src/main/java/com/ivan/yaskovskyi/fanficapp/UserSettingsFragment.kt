package com.ivan.yaskovskyi.fanficapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class UserSettingsFragment : Fragment() {
    companion object {

        fun newInstance(): UserSettingsFragment {
            return UserSettingsFragment()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_settings, container, false)
    }
}