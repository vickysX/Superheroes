package com.example.superheroes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.JetpackHeroes
import com.example.superheroes.model.Superhero
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SuperheroesApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SuperheroesApp() {
    Scaffold(
        topBar = {
            SuperheroTopBar()
        }
    ) {
        HeroesList(list = JetpackHeroes.heroes)
    }
}

@Composable
fun SuperheroTopBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun HeroesList(
    modifier: Modifier = Modifier,
    list: List<Superhero>
) {
    LazyColumn(
        modifier = modifier
            //.padding(16.dp)
            .background(
                color = MaterialTheme.colors.background
            ),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) {
            SuperheroItem(hero = it)
        }
    }
}

@Composable
fun SuperheroItem(
    modifier: Modifier = Modifier,
    hero : Superhero
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            /*.padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )*/
            //.height(72.dp)
            .clip(shape = MaterialTheme.shapes.medium),
        elevation = 2.dp
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                SuperheroInfo(
                    name = hero.name,
                    powers = hero.powers,
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(16.dp))
                SuperheroImage(
                    image = hero.image,
                    description = hero.name
                )
            }
        }
    }
}

@Composable
fun SuperheroInfo(
    modifier: Modifier = Modifier,
    @StringRes name : Int,
    @StringRes powers : Int
) {
    Column(
        modifier = modifier
            //.fillMaxWidth(1f)
            .padding(
                end = 16.dp
            )
    ) {
        Text(
            text = stringResource(id = name),
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Left
        )
        Text(
            text = stringResource(id = powers),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Left,
            overflow = TextOverflow.Clip
        )
    }
}

@Composable
fun SuperheroImage(
    modifier: Modifier = Modifier,
    @DrawableRes image : Int,
    @StringRes description : Int
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = stringResource(id = description),
        modifier = Modifier
            .height(72.dp)
            .width(72.dp)
            .clip(
                shape = MaterialTheme.shapes.small
            ),
        contentScale = ContentScale.FillBounds
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LightThemePreview() {
    SuperheroesTheme(darkTheme = false) {
        SuperheroesApp()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DarkThemePreview() {
    SuperheroesTheme(darkTheme = true) {
        SuperheroesApp()
    }
}