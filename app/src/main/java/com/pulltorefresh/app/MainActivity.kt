package com.pulltorefresh.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private val moveImageView:MoveImageView by lazy {
        findViewById<MoveImageView>(R.id.iv_pull_to_refresh_bg)
    }
    private val refreshAnimIv:ImageView by lazy {
        findViewById<ImageView>(R.id.iv_pull_to_refresh_anim)
    }
    private lateinit var moveImageHelper:MoveImageHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moveImageHelper = MoveImageHelper(refreshAnimIv!!,moveImageView!!)
        moveImageHelper.start()
    }
}
