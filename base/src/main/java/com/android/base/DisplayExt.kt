package com.android.base

import android.content.Context

/**
 * 将px值转换为dip或dp值，保证尺寸大小不变
 *
 * @param pxValue
 * @param scale
 * （DisplayMetrics类中属性density）
 * @return
 */
fun px2dip(context: Context, pxValue: Float): Int {
    val scale: Float = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

/**
 * 将dip或dp值转换为px值，保证尺寸大小不变
 *
 * @param dipValue
 * @param scale
 * （DisplayMetrics类中属性density）
 * @return
 */
fun dip2px(context: Context, dipValue: Float): Int {
    val scale: Float = context.resources.displayMetrics.density
    return (dipValue * scale + 0.5f).toInt()
}

/**
 * 将px值转换为sp值，保证文字大小不变
 *
 * @param pxValue
 * @param fontScale
 * （DisplayMetrics类中属性scaledDensity）
 * @return
 */
fun px2sp(context: Context, pxValue: Float): Int {
    val fontScale: Float = context.resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5f).toInt()
}

/**
 * 将sp值转换为px值，保证文字大小不变
 *
 * @param spValue
 * @param fontScale
 * （DisplayMetrics类中属性scaledDensity）
 * @return
 */
fun sp2px(context: Context, spValue: Float): Int {
    val fontScale: Float = context.resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}