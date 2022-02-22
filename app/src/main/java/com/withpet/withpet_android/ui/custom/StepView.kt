package com.withpet.withpet_android.ui.custom

import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.view.ViewCompat
import com.withpet.withpet_android.R
import kotlin.math.max
import kotlin.math.min

class StepView : View {
    private val startStep = 0
    private var stepList: List<String> = listOf()
    private var currentStep = startStep

    private var circleRadius = 0
    private var lineWidth = 0

    private var yetLineColor = 0
    private var doneLineColor = 0
    private var yetCircleColor = 0
    private var nowCircleColor = 0
    private var doneCircleColor = 0
    private var yetTitleColor = 0
    private var nowTitleColor = 0
    private var doneTitleColor = 0
    private var stepNumColor = 0

    private var stepNumSize = 0.0f
    private var titleTextSize = 0.0f
    private var textPadding = 0
    private var stepPadding = 0

    private lateinit var paint: Paint
    private lateinit var textPaint: TextPaint
    private lateinit var bounds: Rect

    private var circlesCenterX = intArrayOf()
    private var circlesCenterY = 0
    private var startLinesX = intArrayOf()
    private var endLinesX = intArrayOf()
    private var constraints = floatArrayOf()
    private var textLayouts = mutableListOf<StaticLayout?>()
    private var textY = 0

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initialize()
        setStyles(context, attrs, defStyleAttr)
    }

    private fun initialize() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textAlign = Paint.Align.CENTER
        textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        textPaint.textAlign = Paint.Align.CENTER
        bounds = Rect()
    }

    private fun setStyles(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArr = context.obtainStyledAttributes(
            attrs,
            R.styleable.StepView,
            defStyleAttr,
            R.style.StepView
        )

        circleRadius = typedArr.getDimensionPixelSize(R.styleable.StepView_circleRadius, 0)
        lineWidth = typedArr.getDimensionPixelSize(R.styleable.StepView_lineWidth, 0)

        yetLineColor = typedArr.getColor(R.styleable.StepView_lineYetColor, 0)
        doneLineColor = typedArr.getColor(R.styleable.StepView_lineDoneColor, 0)
        yetCircleColor = typedArr.getColor(R.styleable.StepView_circleYetColor, 0)
        nowCircleColor = typedArr.getColor(R.styleable.StepView_circleNowColor, 0)
        doneCircleColor = typedArr.getColor(R.styleable.StepView_circleDoneColor, 0)
        yetTitleColor = typedArr.getColor(R.styleable.StepView_titleYetColor, 0)
        nowTitleColor = typedArr.getColor(R.styleable.StepView_titleNowColor, 0)
        doneTitleColor = typedArr.getColor(R.styleable.StepView_titleDoneColor, 0)
        stepNumColor = typedArr.getColor(R.styleable.StepView_stepNumColor, 0)

        titleTextSize = typedArr.getDimension(R.styleable.StepView_titleTextSize, 0f)
        stepNumSize = typedArr.getDimension(R.styleable.StepView_stepNumSize, 0f)

        textPadding = typedArr.getDimensionPixelSize(R.styleable.StepView_textPadding, 0)
        stepPadding = typedArr.getDimensionPixelSize(R.styleable.StepView_stepPadding, 0)

        typedArr.getTextArray(R.styleable.StepView_steps).let { titleList ->
            if (titleList != null) stepList = titleList.map { it.toString() }
        }
        textPaint.textSize = titleTextSize

        typedArr.recycle()
    }

    fun setSteps(newSteps: List<String>) {
        stepList = newSteps
        requestLayout()
        goStep(startStep)
    }

    fun goStep(step: Int) {
        if (step >= startStep && step < stepList.size) {
            currentStep = step
            invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        if (stepList.isEmpty() || width == 0) {
            setMeasuredDimension(width, 0)
            return
        }
        measureConstraints(width)
        val height = calculateMeasureHeight(heightMeasureSpec)
        setMeasuredDimension(width, height)
        measureAttributes()
    }


    private fun measureConstraints(width: Int) {
        constraints = FloatArray(stepList.size)
        constraints[0] = width.toFloat() / stepList.size
        for (i in constraints.indices) {
            constraints[i] = constraints[0] * (i + 1)
        }

    }

    private fun calculateMeasureHeight(heightMeasureSpec: Int): Int {
        val specSize = MeasureSpec.getSize(heightMeasureSpec)
        val specMode = MeasureSpec.getMode(heightMeasureSpec)
        var desiredSize = paddingTop + paddingBottom + circleRadius * 2 + textPadding

        if (stepList.isNotEmpty()) desiredSize += measureStepsHeight()

        return when (specMode) {
            MeasureSpec.UNSPECIFIED -> desiredSize
            MeasureSpec.AT_MOST -> min(desiredSize, specSize)
            else -> specSize
        }
    }

    private fun measureStepsHeight(): Int {
        var maxHeight = 0
        textLayouts = MutableList(stepList.size) { null }
        textPaint.textSize = titleTextSize

        for (i in stepList.indices) {
            val text = stepList[i]
            val alignment = if (isRtl()) {
                Layout.Alignment.ALIGN_OPPOSITE
            } else {
                Layout.Alignment.ALIGN_NORMAL
            }

            var newLayout: StaticLayout
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.d("STEP_VIEW", "$measuredWidth")
                newLayout = StaticLayout.Builder
                    .obtain(text, 0, text.length, textPaint, measuredWidth / stepList.size)
                    .setAlignment(alignment)
                    .setLineSpacing(0.0f, 1.0f)
                    .setIncludePad(true)
                    .build()
                Log.d("STEP_VIEW", "lineCount: ${newLayout.lineCount}")
            } else {
                newLayout = StaticLayout(
                    text,
                    textPaint,
                    measuredWidth / stepList.size,
                    alignment,
                    1.0f,
                    0.0f,
                    true
                )
            }
            textLayouts[i] = newLayout
            maxHeight = max(maxHeight, newLayout.height)
        }
        return maxHeight
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun isRtl(): Boolean {
        return ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL
    }

    private fun measureAttributes() {
        circlesCenterY = getCircleY()
        circlesCenterX = getCirclePositions()
        textY = circlesCenterY + circleRadius + textPadding
        measureLines()
    }

    private fun getCircleY(): Int {
        val availableHeight = measuredHeight - paddingTop - paddingBottom
        val maxItemHeight = getMaxTextHeight() + circleRadius * 2 + textPadding
        val additionalPadding = (availableHeight - maxItemHeight) / 2
        return paddingTop + additionalPadding + circleRadius
    }

    private fun getMaxTextHeight(): Int {
        var maxTextHeight = 0
        textLayouts.forEach { textLayout ->
            maxTextHeight = max(textLayout!!.height, maxTextHeight)
        }
        return maxTextHeight
    }

    private fun getCirclePositions(): IntArray {
        val ret = IntArray(stepList.size)
        if (ret.isEmpty()) return ret

        ret[0] = getStartCirclePosition()

        if (ret.size == 1) return ret

        ret[stepList.size - 1] = getEndCirclePosition()

        if (ret.size < 3) return ret

        val spaceLeft = if (isRtl()) {
            ret[0] - ret[stepList.size - 1]
        } else {
            ret[stepList.size - 1] - ret[0]
        }

        val margin = spaceLeft / (stepList.size - 1)

        if (isRtl()) {
            for (i in 1 until stepList.size - 1) {
                ret[i] = ret[i - 1] - margin
            }
        } else {
            for (i in 1 until stepList.size - 1) {
                ret[i] = ret[i - 1] + margin
            }
        }
        return ret
    }

    private fun getStartCirclePosition() = if (isRtl()) {
        measuredWidth - paddingRight - max(getMaxTextWidth(textLayouts[0]!!) / 2, circleRadius)
    } else {
        paddingLeft + max(getMaxTextWidth(textLayouts[0]!!) / 2, circleRadius)
    }


    private fun getMaxTextWidth(staticLayout: StaticLayout): Int {
        var maxLineWidth = 0
        for (i in 0 until staticLayout.lineCount) {
            maxLineWidth = max(staticLayout.getLineWidth(i).toInt(), maxLineWidth)
        }
        return maxLineWidth
    }

    private fun getEndCirclePosition() = if (isRtl()) {
        paddingLeft + max(getMaxTextWidth(textLayouts.last()!!) / 2, circleRadius)
    } else {
        measuredWidth - paddingRight - max(getMaxTextWidth(textLayouts.last()!!) / 2, circleRadius)
    }

    private fun measureLines() {
        startLinesX = IntArray(stepList.size - 1)
        endLinesX = IntArray(stepList.size - 1)
        val padding = stepPadding + circleRadius

        for (i in 1 until stepList.size) {
            if (isRtl()) {
                startLinesX[i - 1] = circlesCenterX[i - 1] - padding
                endLinesX[i - 1] = circlesCenterX[i] + padding
            } else {
                startLinesX[i - 1] = circlesCenterX[i - 1] + padding
                endLinesX[i - 1] = circlesCenterX[i] - padding
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        Log.d("STEP_VIEW", "HERE")
        if (height == 0 || stepList.isEmpty()) return
        for (i in stepList.indices) {
            drawStep(canvas, i, circlesCenterX[i].toFloat(), circlesCenterY.toFloat())
        }
        for (i in startLinesX.indices) {
            if (i < currentStep) {
                drawLine(
                    canvas,
                    startLinesX[i].toFloat(),
                    endLinesX[i].toFloat(),
                    circlesCenterY.toFloat(),
                    true
                )
            } else {
                drawLine(
                    canvas,
                    startLinesX[i].toFloat(),
                    endLinesX[i].toFloat(),
                    circlesCenterY.toFloat(),
                    false
                )
            }
        }
    }

    private fun drawStep(canvas: Canvas?, step: Int, centerX: Float, centerY: Float) {
        val text = stepList[step]
        val isNow = (step == currentStep)
        val isDone = (step < currentStep)
        val stepNum = (step + 1).toString()

        when {
            isNow -> {
                paint.color = nowCircleColor
                canvas?.drawCircle(centerX, centerY, circleRadius.toFloat(), paint)

                paint.color = stepNumColor
                paint.textSize = stepNumSize
                drawNumber(canvas, stepNum, centerX, paint)

                textPaint.color = nowTitleColor
                textPaint.textSize = titleTextSize
                drawText(canvas, text, textY, step)
            }
            isDone -> {
                paint.color = doneCircleColor
                canvas?.drawCircle(centerX, centerY, circleRadius.toFloat(), paint)

                paint.color = stepNumColor
                paint.textSize = stepNumSize
                drawNumber(canvas, stepNum, centerX, paint)

                textPaint.color = doneTitleColor
                textPaint.textSize = titleTextSize
                drawText(canvas, text, textY, step)
            }
            else -> {
                paint.color = yetCircleColor
                canvas?.drawCircle(centerX, centerY, circleRadius.toFloat(), paint)

                paint.color = stepNumColor
                paint.textSize = stepNumSize
                drawNumber(canvas, stepNum, centerX, paint)

                textPaint.color = yetTitleColor
                textPaint.textSize = titleTextSize
                drawText(canvas, text, textY, step)
            }
        }
    }

    private fun drawNumber(canvas: Canvas?, stepNum: String, centerX: Float, numPaint: Paint) {
        numPaint.getTextBounds(stepNum, 0, stepNum.length, bounds)
        val y = circlesCenterY + bounds.height() / 2f - bounds.bottom
        canvas?.drawText(stepNum, centerX, y, numPaint)
    }

    private fun drawText(canvas: Canvas?, text: String, textY: Int, step: Int) {
        if (text.isEmpty()) return
        val textLayout = textLayouts[step]
        canvas?.save()
        canvas?.translate(circlesCenterX[step].toFloat(), textY.toFloat())
        textLayout!!.draw(canvas)
        canvas?.restore()
    }

    private fun drawLine(
        canvas: Canvas?,
        startX: Float,
        endX: Float,
        centerY: Float,
        isDone: Boolean
    ) {
        if (isDone) {
            paint.color = doneLineColor
            paint.strokeWidth = lineWidth.toFloat()
            canvas?.drawLine(startX, centerY, endX, centerY, paint)
        } else {
            paint.color = yetLineColor
            paint.strokeWidth = lineWidth.toFloat()
            canvas?.drawLine(startX, centerY, endX, centerY, paint)
        }
    }
}