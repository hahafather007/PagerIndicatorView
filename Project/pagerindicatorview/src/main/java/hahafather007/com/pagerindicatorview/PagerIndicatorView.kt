package hahafather007.com.pagerindicatorview

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout

class PagerIndicatorView : LinearLayout, ViewPager.OnPageChangeListener {
    private var views = emptyList<View>()
    private var pager: ViewPager? = null

    var pointWidth: Int
    var pointMargin: Int

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        gravity = Gravity.CENTER

        val array = getContext().obtainStyledAttributes(attrs, R.styleable.IndicatorLayout)
        pointWidth = array.getDimension(R.styleable.IndicatorLayout_pointWidth, 10f).toInt()
        pointMargin = array.getDimension(R.styleable.IndicatorLayout_pointMargin, 7f).toInt() / 2
        array.recycle()
    }

    private fun refreshViews(size: Int) {
        if (size > 1) {
            views.forEach { removeView(it) }
            views = (0 until size)
                    .map {
                        val view = View(context)
                        val params = LinearLayout.LayoutParams(pointWidth, pointWidth)
                        params.leftMargin = pointMargin
                        params.rightMargin = pointMargin
                        view.setBackgroundResource(R.drawable.indicator_drawable)
                        view.isSelected = it == 0
                        addView(view, params)
                        view
                    }
        }
    }


    /**
     *  外部只需调用该方法与指示器关联即可
     * */
    fun attachTo(pager: ViewPager) {
        pager.addOnPageChangeListener(this)

        refreshViews(pager.adapter?.count ?: 0)

        this.pager = pager
    }

    /**
     * 若ViewPager数据变化，调用refresh刷新指示器数据
     */
    fun refresh() {
        refreshViews(pager?.adapter?.count ?: 0)
    }

    fun setSelected(position: Int) {
        views.apply {
            if (isEmpty() && position > size - 1)
                return

            forEachIndexed { index, view ->
                view.isSelected = index == position
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        setSelected(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }
}
