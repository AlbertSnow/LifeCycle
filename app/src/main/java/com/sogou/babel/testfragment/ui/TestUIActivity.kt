package com.sogou.babel.testfragment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sogou.babel.testfragment.R

class TestUIActivity : AppCompatActivity() {

    lateinit var  mMainListView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_ui)
        initView()
    }

    private fun initView() {
        mMainListView = findViewById<RecyclerView>(R.id.test_recyclerview)
        val mainManager = GridLayoutManager(this, 3)
        mainManager.setOrientation(LinearLayoutManager.VERTICAL)

        mMainListView.setLayoutManager(mainManager)


        val mMainAdapter = FeedbackAdapter(
            listOf( "网页无法打开", "网页翻译失败", "译文翻译错误", "网页展示问题", "敏感信息或不适内容")
        )
        mMainListView.setAdapter(mMainAdapter)
    }

//    fun initData() {
//        subItem = ArrayList<>();
//        subItem.add(Arrays.asList(
//            "翻译不准", "音标问题", "发音错误", "展示异常"
//        ));
//        subItem.add(Arrays.asList(
//            "翻译不准确,不完整等", "等待时间太长", "显示内容错乱", "翻译语种缺失"
//        ));
//        subItem.add(Arrays.asList(
//            "网页无法打开", "网页翻译失败", "译文翻译错误", "网页展示问题", "敏感信息或不适内容"
//        ));
//        subItem.add(Arrays.asList(
//            "识别不准确,不完整等", "翻译不准", "语种缺失", "发音问题", "操作问题", "等待时间太长"
//        ));
//        subItem.add(new ArrayList<>(0));
//
//    }

}
