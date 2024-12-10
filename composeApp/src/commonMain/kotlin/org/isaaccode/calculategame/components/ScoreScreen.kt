package org.isaaccode.calculategame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ScoreScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = "3 * 2 = ?",
            color = Color.White,
            fontSize = 32.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Button(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 20.dp)
                    .background(Color.Green),
                onClick = { },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Color.DarkGray)
            ) {
                Text(
                    text = "6",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
                    textAlign = TextAlign.Center
                )
            }
            Button(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 20.dp)
                    .background(Color.Green),
                onClick = { },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Color.DarkGray)
            ) {
                Text(
                    text = "8",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
                    textAlign = TextAlign.Center
                )
            }
            Button(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 20.dp)
                    .background(Color.Green),
                onClick = { },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Color.DarkGray)
            ) {
                Text(
                    text = "6",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
                    textAlign = TextAlign.Center
                )
            }
        }


    }
}