package com.android.jetpack_mvvm.bean

/**
 * 文章
 */
data class Article(
    var apkLink: String = "",
    var audit: Int = 0,
    var author: String = "",
    var canEdit: Boolean = false,
    var chapterId: Int = 0,
    var chapterName: String = "",
    var collect: Boolean = false,
    var courseId: Int = 0,
    var desc: String = "",
    var descMd: String = "",
    var envelopePic: String = "",
    var fresh: Boolean = false,
    var host: String = "",
    var id: Int = 0,
    var link: String = "",
    var niceDate: String = "",
    var niceShareDate: String = "",
    var origin: String = "",
    var prefix: String = "",
    var projectLink: String = "",
    var publishTime: Long = 0,
    var realSuperChapterId: Int = 0,
    var selfVisible: Int = 0,
    var shareDate: Long = 0,
    var shareUser: String = "",
    var superChapterId: Int = 0,
    var superChapterName: String = "",
    var tags: List<Tag> = listOf(),
    var title: String = "",
    var userId: Int = 0,
    var type: Int = 0,
    var visible: Int = 0,
    var zan: Int = 0
)
