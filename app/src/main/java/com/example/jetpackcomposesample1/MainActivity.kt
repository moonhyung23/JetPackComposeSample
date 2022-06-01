package com.example.jetpackcomposesample1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposesample1.ui.theme.JetPackComposeSample1Theme

data class Message(val author: String, val body: String)


class MainActivity : ComponentActivity() {
    val messsages = mutableListOf<Message>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messsages.add(Message("a1",
            "Hey, take a look at Jetpack Compose, it's great!take a look at Jetpack Compose, it's great!take a look at Jetpack Compose, it's great!"))
        messsages.add(Message("a2", "b2"))
        messsages.add(Message("a3", "b3"))
        messsages.add(Message("a4", "b4"))
        messsages.add(Message("a5", "b5"))
        setContent {
            JetPackComposeSample1Theme {
                // A surface container using the 'background' color from the theme
                //백그라운드 색 지정
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
//                    Greeting("message")
                    Conversation(messages = messsages)
//                    ImageCard(Message("Message", "JetPack Compose"))
//                    MessageCard(Message("Message", "JetPack Compose"))
                }
            }
        }


    }


}

@Composable
fun Greeting(name: String) {
    Surface(color = MaterialTheme.colors.secondary) {
        Text(text = "Hello $name!", modifier = Modifier.padding(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun MessagePreview() {
    MessageCard(msg = Message("Colleague", "Hey take a look at JetPack Compose."))
}


@Composable
fun MessageCard(msg: Message) {
    Column {
        Text(msg.author)
        Text(msg.body)
    }
}

@Composable
fun ImageCard(msg: Message) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "content profile picture"
        )

        Column {
            Text(text = msg.author)
            Text(text = msg.body)
        }
    }
}

//MessageCard
@Composable
fun ImageCard2(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Contact Profile picture",
            modifier = Modifier
                .size(40.dp)
                //이미지 동그랗게 하기
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        //확장 혹은 접었을 때 변수를 저장해줌.
        //UI 상태를 저장
        //MutableStateOf에 전달된 값의 변경사항을 추적할 수 있다.

        var isExpanded by remember { mutableStateOf(false) }

        //Image와 Column간의 간격
//        Spacer(modifier = Modifier.width(8.dp))

        //컬럼간 간격
        Column(modifier = Modifier.clickable {
            isExpanded = !isExpanded
        }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }


        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    //List의 요소 하나하나 UI에 그리기
    LazyColumn {
        items(messages) { message ->
            //Message
            ImageCard2(msg = message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewImageCard2() {
    JetPackComposeSample1Theme {
        ImageCard2(Message("aaa", "bbbb"))
    }
}


/*
@Preview
@Composable
fun PreviewConversation() {
    JetPackComposeSample1Theme {
        Conversation(messages = messsages)
    }
}
*/


/*
@Preview
@Composable
fun PreviewMessageCard() {
    Greeting("Android")
}
@Preview(showBackground = true, name = "Text preview1")
@Composable
fun DefaultPreview() {
    JetPackComposeSample1Theme {
        Greeting("Android")
    }
}

@Preview(showBackground = true, name = "Text preview2")
@Composable
fun DefaultPreview2() {
    JetPackComposeSample1Theme {
        Greeting(name = "Android22")
    }
}*/
