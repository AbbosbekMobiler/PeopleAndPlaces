package com.example.peopleandplaces

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peopleandplaces.ui.theme.PeopleAndPlacesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeopleAndPlacesTheme(darkTheme = false) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PeoplePlaces1()
                }
            }
        }
    }
}

private lateinit var peoplePlacesData : ArrayList<PeoplePlaces1>

@Composable
fun PeoplePlaces1() {
    var listPrepared by remember {
        mutableStateOf(false)
    }

    if (listPrepared){
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(peoplePlacesData){itemObject ->
                PeoplePlacesItemStyle(peoplePlaces1 = itemObject)
            }
        }
    }
    LaunchedEffect(Unit){
        withContext(Dispatchers.Default){
            prepareDataPeoplePlaces1()
            listPrepared = true
        }
    }
}

@Composable
fun PeoplePlacesItemStyle(
    peoplePlaces1 : PeoplePlaces1,
    context: Context = LocalContext.current
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, top = 16.dp),
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Box(modifier = Modifier
            .clickable(onClick = {
                Toast
                    .makeText(context, peoplePlaces1.name, Toast.LENGTH_SHORT)
                    .show()
            })
            .clip(RoundedCornerShape(12.dp))
        ){
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = peoplePlaces1.image),
                    contentDescription = peoplePlaces1.name,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(48.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(text = peoplePlaces1.name, color = Color.Black, fontSize = 18.sp)
                    Text(text = peoplePlaces1.location, color = Color.LightGray, fontSize = 12.sp)
                }
            }
        }
    }
}

data class PeoplePlaces1(val image : Int,val name : String,val location : String)

fun prepareDataPeoplePlaces1(){
    peoplePlacesData = ArrayList()

    peoplePlacesData.add(
        PeoplePlaces1(R.drawable.ronaldo,"Ronaldo","Portugaliya")
    )
    peoplePlacesData.add(
        PeoplePlaces1(R.drawable.messi,"Messi","Argentiniya")
    )

    peoplePlacesData.add(
        PeoplePlaces1(R.drawable.haaland,"Haaland","Norvegiya")
    )
    peoplePlacesData.add(
        PeoplePlaces1(R.drawable.neymar,"Neymar","Braziliya")
    )
    peoplePlacesData.add(
        PeoplePlaces1(R.drawable.mbappe,"Mbappe","Fransiya")
    )
    peoplePlacesData.add(
        PeoplePlaces1(R.drawable.benzema,"Benzema","Fransiya")
    )
    peoplePlacesData.add(
        PeoplePlaces1(R.drawable.debrunye,"Kevin DeBrunye","Belgiya")
    )
    peoplePlacesData.add(
        PeoplePlaces1(R.drawable.mane,"Muhammad Salah","Misr")
    )
    peoplePlacesData.add(
        PeoplePlaces1(R.drawable.mane,"Sadio Mane","Senegal")
    )
}