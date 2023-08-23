package com.example.food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class WesternFoodActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WesternFoodMenu()
        }
    }
}

data class WesternFood(
    val name: String,
    val imageRes: Int,
    val ingredient: String,
    val explanation: String
)

class WesternFoodDatabase {
    private val foodList = listOf(
        WesternFood("스테이크", R.drawable.steak, "소고기, 감자, 양파, 버터, 마늘, 로즈마리", "스테이크 레시피 설명:\n" +
                "1. 고기 표면에 소금과 후추를 골고루 뿌립니다. 양면에 양념을 뿌리는 것을 잊지 마세요.\n" +
                "2. 팬을 중불로 예열하거나 그릴을 예열합니다. 팬을 사용할 경우, 식용유나 버터를 두르고 고기를 올려줍니다. 고기가 접촉한 면이 바삭하게 볶아질 때까지 조리합니다. (고기의 두께에 따라 조리 시간이 달라질 수 있습니다.)\n" +
                "3. 한 면을 조리한 후에는 고기를 뒤집어 반대 면도 같은 방법으로 조리합니다. 고기의 익음 정도를 확인하여 선호하는 익음 정도에 맞게 조리합니다.\n" +
                "4. 고기를 그릴에서 조리할 때는 간편하게 고기 양면을 그릴망 위에 올려두고 조리합니다.\n" +
                "5. 고기를 그릴에서 꺼내거나 팬에서 꺼낸 후, 데쳐놓고 2-3분 정도 잠깐 쉬게 해줍니다. 이렇게 하면 고기의 주스가 골고루 분포하여 맛과 풍미를 유지할 수 있습니다.\n" +
                "6. 잠시 쉬어간 고기를 접시에 옮기고 선택적으로 마늘, 로즈마리, 타임 등의 허브나 간장, 갈릭버터 등을 얹어 향과 맛을 더해줍니다."),
        WesternFood("비프 스튜", R.drawable.beefstew, "소고기, 감자, 당근, 양파, 버터, 밀가루, 로즈마리", "비프 스튜 레시피 설명:\n" +
                "1. 쇠고기를 큰 조각으로 자르고, 밀가루나 빵 가루로 뭍힙니다.\n" +
                "2. 팬에 식용유나 버터를 두르고 고기를 노릇하게 볶아줍니다. 고기의 색이 변하면 데칠 준비가 된 것입니다.\n" +
                "3. 데친 고기를 팬에서 꺼내고, 같은 팬에 다양한 야채들을 넣어 노릇하게 볶아줍니다.\n" +
                "4. 볶아낸 야채에 고기를 다시 넣고 육수나 물, 와인이나 비어를 부어줍니다.\n" +
                "5. 소금과 후추, 허브를 넣고 가장 작은 불로 줄여 끓여줍니다.\n" +
                "6. 뚜껑을 덮고 느리게 끓여줍니다. 고기와 야채가 부드러워질 때까지 1시간 이상 조리할 수 있습니다.\n" +
                "7. 스튜가 익으면 간을 맞추고, 필요하다면 추가적인 허브나 옵션 재료를 넣어줍니다.\n" +
                "8. 스튜를 그릇에 담고 겉면이 빵 가루로 덮이도록 조금씩 빚어줍니다."),
        WesternFood("마카로니 앤 치즈", R.drawable.macandcheese, "마카로니, 치즈, 우유, 버터, 밀가루, 소금, 후추", "마카로니 앤 치즈설명 레시피 설명:\n" +
                "1. 물에 소금을 넣고 마카로니를 살짝 익게 끓여줍니다. 패키지에 표시된 시간보다 1-2분 짧게 끓이세요. (알뜰한 익음 정도가 좋습니다.)\n" +
                "2. 마카로니를 물에서 건져내고 찬물에 헹구어 식혀줍니다.\n" +
                "3. 소스를 만들기 위해 중간 불에서 버터를 녹여줍니다.\n" +
                "4. 밀가루를 넣고 버터와 잘 섞어줍니다. 밀가루가 익을 때까지 계속 볶아줍니다.\n" +
                "5. 우유를 조금씩 넣으면서 점점 소스가 뿌리게 만들어줍니다.\n" +
                "6. 우유가 끓으면 치즈를 넣고 녹을 때까지 저어줍니다.\n" +
                "7. 마카로니를 소스에 넣고 고루 섞어줍니다.\n" +
                "8. 소금과 후추로 간을 맞춰줍니다. 옵션으로 마스터드나 파우더드 머스타드 등을 넣어 풍미를 더해줄 수 있습니다.\n" +
                "9. 마지막으로 잘 섞은 마카로니 앤 치즈를 그릇에 담아줍니다.")
    )

    fun getFoodList(): List<WesternFood> {
        return foodList
    }
}



@Composable
fun WesternFoodMenu() {
    val westernFoodDatabase = WesternFoodDatabase()
    val foodList = westernFoodDatabase.getFoodList()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val backgroundImage = painterResource(id = R.drawable.weternfoodbackground)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyRow(
            modifier = Modifier.width(300.dp).fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            content = {
                items(foodList) { food ->
                    WesternFoodButton(food)
                }
            }
        )
    }
}

@Composable
fun WesternFoodButton(food: WesternFood) {
    var recipeOpen by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 16.dp), // Add horizontal padding for better alignment
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box {

        }
        Image(
            painter = painterResource(id = food.imageRes),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // You can adjust the height as needed
        )
        Button(onClick = { recipeOpen = !recipeOpen },
            colors = ButtonDefaults.buttonColors(Color(0x66404040))) {
            Text(text = food.name)
        }
        if (recipeOpen == false) {
            RecipeButton(food)
        }
    }
}

@Composable
fun RecipeButton(food: WesternFood) {
    Column (modifier = Modifier
        .width(300.dp)
        .height(450.dp)
        .background(color = Color.White)){
        Text(text = "재료: ${food.ingredient}")
        Text(text = food.explanation)
    }
}

@Preview(showBackground = true)
@Composable
fun WesternFoodPreview() {
    WesternFoodMenu()
}