package pl.lukaszmarczak.neopop.compose.ui.theme

import android.graphics.Paint
import android.graphics.Typeface
import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.nativeCanvas

@Composable
fun NeoPopButton(
    buttonColor: Color,
    buttonText: String,
    edgesColors: Pair<Color, Color>,
    modifier: Modifier = Modifier,
) {
    val state = remember { mutableStateOf(false) }
    val pressingProgress: Float by animateFloatAsState(
        targetValue = if (state.value) 1f else 0f,
        animationSpec = tween()
    )
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .background(Color.Transparent)
            .neoPopEdges(edgesColors, pressingProgress)
            .neoPopArea(buttonColor, buttonText, pressingProgress)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    state.value = state.value.not()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
    }
}

fun Modifier.neoPopArea(
    buttonColor: Color,
    buttonText: String,
    @FloatRange(from = 0.0, to = 1.0) depth: Float
) = then(
    object : DrawModifier {
        val paint = Paint().apply {
            textSize = 40f
            typeface = Typeface.DEFAULT_BOLD
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.CENTER
        }

        override fun ContentDrawScope.draw() {
            val (width, height) = size
            val xOffset = 30f * depth
            val yOffset = 30f * depth

            drawPath(
                color = buttonColor,
                path = androidx.compose.ui.graphics.Path().apply {
                    moveTo(xOffset, yOffset)
                    lineTo(width + xOffset, yOffset)
                    lineTo(width + xOffset, height + yOffset)
                    lineTo(xOffset, height + yOffset)
                    close()
                }
            )
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    buttonText,
                    (size.width + xOffset) / 2,
                    ((size.height + yOffset) / 2) + 20,
                    paint
                )
            }
            drawContent()
        }
    }
)

fun Modifier.neoPopEdges(edgesColors: Pair<Color, Color>, pressed: Float) = then(
    object : DrawModifier {

        override fun ContentDrawScope.draw() {
            val (width, height) = size
            val absoluteOffsetX = 30f
            val absoluteOffsetY = 30f
            val xOffset = 30f * (pressed)
            val yOffset = 30f * (pressed)
            drawPath(
                color = edgesColors.first,
                path = androidx.compose.ui.graphics.Path().apply {
                    moveTo(width + absoluteOffsetX, height + absoluteOffsetY)
                    lineTo(width, height)
                    lineTo(width, yOffset)
                    lineTo(width + absoluteOffsetX, absoluteOffsetY + yOffset)
                    close()
                }
            )
            drawPath(
                color = edgesColors.second,
                path = androidx.compose.ui.graphics.Path().apply {
                    moveTo(width + absoluteOffsetX, height + absoluteOffsetY)
                    lineTo(width, height)
                    lineTo(xOffset, height)
                    lineTo(xOffset + absoluteOffsetX, height + absoluteOffsetY)
                    close()
                }
            )
            drawContent()
        }
    }
)