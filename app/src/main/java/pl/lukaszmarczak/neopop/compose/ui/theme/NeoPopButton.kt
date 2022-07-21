package pl.lukaszmarczak.neopop.compose.ui.theme

import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.dp

@Composable
fun NeoPopButton(
    buttonColor: Color,
    edgesColors: Pair<Color, Color>,
    modifier: Modifier = Modifier,
    buttonContent: @Composable BoxScope.() -> Unit
) {
    val depthSize = Size(35f, 30f)
    val state = remember { mutableStateOf(false) }
    val pressingProgress: Float by animateFloatAsState(
        targetValue = if (state.value) 1f else 0f,
        animationSpec = tween()
    )
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .background(Color.Transparent)
            .neoPopEdges(edgesColors, depthSize, pressingProgress)
            .neoPopArea(buttonColor, depthSize, pressingProgress)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    state.value = state.value.not()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(
                    x = ((depthSize.width / 2) * pressingProgress).dp,
                    y = ((depthSize.height / 2) * pressingProgress).dp
                ),
            contentAlignment = Alignment.Center,
            content = buttonContent
        )
    }
}

fun Modifier.neoPopArea(
    buttonColor: Color,
    depthSize: Size,
    @FloatRange(from = 0.0, to = 1.0) depth: Float
) = then(
    object : DrawModifier {
        private val path = Path()
        override fun ContentDrawScope.draw() {
            val (width, height) = size
            val xOffset = depthSize.width * depth
            val yOffset = depthSize.height * depth

            drawPath(
                color = buttonColor,
                path = path.apply {
                    reset()
                    moveTo(xOffset, yOffset)
                    lineTo(width + xOffset, yOffset)
                    lineTo(width + xOffset, height + yOffset)
                    lineTo(xOffset, height + yOffset)
                    close()
                }
            )
            drawContent()
        }
    }
)

fun Modifier.neoPopEdges(
    edgesColors: Pair<Color, Color>,
    depthSize: Size,
    pressed: Float
) = then(
    object : DrawModifier {
        private val horizontalPath = Path()
        private val verticalPath = Path()

        override fun ContentDrawScope.draw() {
            val (width, height) = size
            val absoluteOffsetX = depthSize.width
            val absoluteOffsetY = depthSize.height
            val xOffset = absoluteOffsetX * pressed
            val yOffset = absoluteOffsetY * pressed
            drawPath(
                color = edgesColors.first,
                path = verticalPath.apply {
                    reset()
                    moveTo(width + absoluteOffsetX, height + absoluteOffsetY)
                    lineTo(width, height)
                    lineTo(width, yOffset)
                    lineTo(width + absoluteOffsetX, absoluteOffsetY)
                    close()
                }
            )
            drawPath(
                color = edgesColors.second,
                path = horizontalPath.apply {
                    reset()
                    moveTo(width + absoluteOffsetX, height + absoluteOffsetY)
                    lineTo(width, height)
                    lineTo(xOffset, height)
                    lineTo(absoluteOffsetX, height + absoluteOffsetY)
                    close()
                }
            )
            drawContent()
        }
    }
)