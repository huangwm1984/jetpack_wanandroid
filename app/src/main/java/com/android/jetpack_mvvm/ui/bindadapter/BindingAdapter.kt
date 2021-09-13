package me.hgj.jetpackmvvm.demo.data.bindadapter

import android.os.SystemClock
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.base.toHtml
import com.android.jetpack_mvvm.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


object BindingAdapter {

    @BindingAdapter(value = ["setTopViewGone"])
    @JvmStatic
    fun setTopViewGone(view: View,  flag: Int) {
        if (flag != 1) {
            view.visibility = View.GONE
        }
    }

    @BindingAdapter(value = ["setViewGone"])
    @JvmStatic
    fun setViewGone(view: View,  isGone: Boolean) {
        view.visibility = if (isGone) View.GONE else View.VISIBLE
    }

    @BindingAdapter(value = ["formatHtml"])
    @JvmStatic
    fun formatHtml(textView: TextView, str: String) {
        textView.text = str.toHtml()
    }


//    @BindingAdapter(value = ["showPwd"])
//    @JvmStatic
//    fun showPwd(view: EditText, boolean: Boolean) {
//        if (boolean) {
//            view.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
//        } else {
//            view.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
//        }
//        view.setSelection(view.textString().length)
//    }

    @BindingAdapter(value = ["imageUrl"])
    @JvmStatic
    fun imageUrl(view: ImageView, url: String) {
        Glide.with(view.context.applicationContext)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(view)
    }

    @BindingAdapter(value = ["circleImageUrl"])
    @JvmStatic
    fun circleImageUrl(view: ImageView, url: String) {
        Glide.with(view.context.applicationContext)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(view)
    }



    @BindingAdapter(value = ["afterTextChanged"])
    @JvmStatic
    fun EditText.afterTextChanged(action: (String) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                action(s.toString())
            }
        })
    }

    @BindingAdapter(value = ["noRepeatClick"])
    @JvmStatic
    fun setOnClick(view: View, clickListener: () -> Unit) {
        val mHits = LongArray(2)
        view.setOnClickListener {
            System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
            mHits[mHits.size - 1] = SystemClock.uptimeMillis()
            if (mHits[0] < SystemClock.uptimeMillis() - 500) {
                clickListener.invoke()
            }
        }
    }


}