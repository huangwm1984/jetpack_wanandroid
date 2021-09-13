package com.android.jetpack_mvvm.bean

/**
 * 体系数据
 */
data class SystemInfo(
    var children: List<ProjectClassify> = listOf(),
    var courseId: Int = 0,
    var id: Int = 0,
    var name: String = "",
    var order: Int = 0,
    var parentChapterId: Int = 0,
    var userControlSetTop: Boolean = false,
    var visible: Int = 0
)
