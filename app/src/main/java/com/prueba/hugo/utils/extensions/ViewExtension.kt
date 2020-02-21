package com.prueba.hugo.utils.extensions

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.TypedValue
import android.view.View
import android.view.WindowInsets
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.updatePadding
import androidx.databinding.BindingAdapter
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.snackbar.Snackbar
import com.prueba.hugo.R
import com.prueba.hugo.utils.CustomProgressBar
import com.prueba.hugo.utils.CustomTypefaceSpan
import com.prueba.hugo.utils.ProgressBarAnimation
import com.prueba.hugo.utils.SafeClickListener


fun View?.snackbarConexion(mensaje: String) {
    val snackbar = Snackbar.make(this!!, mensaje, Snackbar.LENGTH_LONG)
    val snackBarView = snackbar.view
    snackBarView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.colorApp))
    val textView: TextView = snackBarView.findViewById(R.id.snackbar_text)
    textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wifi_off, 0, 0, 0)
    textView.compoundDrawablePadding = resources.getDimensionPixelOffset(R.dimen._10sdp)
    //textView.typeface = Typeface.createFromAsset(contexto.assets, "font/frutiger_lt_for_bns_bold.ttf")
    val myCustomFont: Typeface? = ResourcesCompat.getFont(this.context, R.font.roboto_bold)
    textView.typeface = myCustomFont
    textView.setTextColor(ContextCompat.getColor(this.context, R.color.colorBlanco))
    snackbar.show()
}

fun View?.snackNavigationBar(mensaje: String) {
    val snackbar = Snackbar.make(this!!, mensaje, Snackbar.LENGTH_LONG)
    val snackBarView = snackbar.view
    snackBarView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.colorApp))
    val textView: TextView = snackBarView.findViewById(R.id.snackbar_text)
    //textView.typeface = Typeface.createFromAsset(contexto.assets, "font/frutiger_lt_for_bns_bold.ttf")
    val myCustomFont: Typeface? = ResourcesCompat.getFont(this.context, R.font.roboto_bold)
    textView.typeface = myCustomFont
    textView.setTextColor(ContextCompat.getColor(this.context, R.color.colorBlanco))
    snackbar.anchorView = this
    snackbar.show()
}

fun BottomNavigationView?.configBottom() {
    //remover animacion de menu footer
    this?.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED

    //BottomNavigationViewHelper().disableShiftMode(navigation)
    val font: Typeface? = ResourcesCompat.getFont(this!!.context, R.font.roboto_medium)
    val typefaceSpan = CustomTypefaceSpan("", font!!)
    for (i in 0 until this.menu.size()) {
        //fuentes de menu
        val menuItem = this.menu.getItem(i)
        val spannableTitle = SpannableStringBuilder(menuItem.title)
        spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length, 0)
        menuItem.title = spannableTitle
        //iconos de menu
        val menuView = this.getChildAt(0) as BottomNavigationMenuView
        val iconView = menuView.getChildAt(i).findViewById<View>(R.id.icon)
        val layoutParams = iconView.layoutParams
        val displayMetrics = resources.displayMetrics
        layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25f, displayMetrics).toInt()
        layoutParams.width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25f, displayMetrics).toInt()
        iconView.layoutParams = layoutParams
    }
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener { view ->
        onSafeClick(view)
    }
    setOnClickListener(safeClickListener)
}

//View Visibility
fun View.Visible() {
    visibility = View.VISIBLE
}

fun View.Invisible() {
    visibility = View.INVISIBLE
}

fun View.Gone() {
    visibility = View.GONE
}

//navigations
fun View.gotoSpecificFragmentClear(action: Int){
    Navigation.findNavController(this)
        .navigate(action,
            null,
            NavOptions.Builder()
                .setPopUpTo(action,
                    true).build())
}

fun View.gotoNavigate(action: Int, arg: Bundle? = null) {
    Navigation.findNavController(this)
        .navigate(action, arg)
}
fun View.gotoBack() {
    Navigation.findNavController(this).popBackStack()
}

fun View.marginUpdate() {
    systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    setPadding(0, 0, 0, 0)
    doOnApplyWindowInsets { view, insets, padding ->
        view.updatePadding(bottom = padding.bottom)
        //insets
    }
}

fun View.doOnApplyWindowInsets(f: (View, WindowInsets, InitialPadding) -> Unit) {
    // Create a snapshot of the view's padding state
    val initialPadding = recordInitialPaddingForView(this)
    // Set an actual OnApplyWindowInsetsListener which proxies to the given
    // lambda, also passing in the original padding state
    setOnApplyWindowInsetsListener { v, insets ->
        f(v, insets, initialPadding)
        // Always return the insets, so that children can also use them
        insets
    }
    // request some insets
    requestApplyInsetsWhenAttached()
}

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        // We're already attached, just request as normal
        requestApplyInsets()
    } else {
        // We're not attached to the hierarchy, add a listener to
        // request when we are
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

private fun recordInitialPaddingForView(view: View) = InitialPadding(
    view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom
)

data class InitialPadding(
    val left: Int, val top: Int,
    val right: Int, val bottom: Int
)

@BindingAdapter("custom_progress")
fun CustomProgressBar.startAnimation(progress:Float = 50.0f) {
    val localProgressBarAnimation = ProgressBarAnimation(0.0f, progress,this)
    localProgressBarAnimation.interpolator = android.view.animation.OvershootInterpolator(0.5f)
    localProgressBarAnimation.duration = 4000L
    startAnimation(localProgressBarAnimation)
}