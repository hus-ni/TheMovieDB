package com.muhammadhusniabdillah.themoviedb.ui

import com.muhammadhusniabdillah.themoviedb.databinding.ActivityMainBinding
import com.muhammadhusniabdillah.themoviedb.ui.base.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate)