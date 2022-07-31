package com.iprayforgod.components.subComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iprayforgod.onboarding_domain.models.OnBoardingPageData
import com.iprayforgod.onboarding_presentation.R

@Composable
fun PagerData(data: OnBoardingPageData) {
    CurrPagerDataContent(data)
}

@Composable
fun CurrPagerDataContent(data: OnBoardingPageData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_icon),
            modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight(0.7f),
            contentDescription = data.description
        )
        Text(
            text = data.title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = data.description,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrevPagerDataContent() {
    CurrPagerDataContent(
        OnBoardingPageData(
            image = R.drawable.ic_launcher_icon, title = "Demo Title", description = "Demo Description"
        )
    )
}
