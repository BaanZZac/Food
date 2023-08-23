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
import androidx.compose.foundation.layout.size
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

class KoreanFoodActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoreanFoodMenu()
        }
    }
}

data class KoreanFood(
    val name: String,
    val imageRes: Int,
    val ingredient: String,
    val explanation: String
)

class KoreanFoodDatabase {
    private val foodList = listOf(
        KoreanFood("비빔밥", R.drawable.bibimbap, "쌀, 소고기, 당근, 시금치, 콩나물, 계란, 고추장, 참기름", "비빔밥 레시피 설명:\n" +
                "1. 먼저 밥을 삶아서 준비합니다. 밥은 조금 차갑게 식히는 것이 좋습니다.\n" +
                "2. 고기는 얇게 썬 후 볶거나 구워서 준비합니다. 소금과 간장으로 간을 조절합니다.\n" +
                "3. 야채들은 씻어서 먹기 좋은 크기로 썬 후 데쳐줍니다.\n" +
                "4. 계란은 푼 후 팬에 부쳐 계란후라이를 만들거나, 노른자를 보존하여 사용합니다.\n" +
                "5. 고추장을 화장실크기에 따라 적당한 양을 준비합니다. 다진 마늘, 참기름을 넣어 고루 섞어줍니다.\n" +
                "6. 준비된 밥과 야채, 고기를 볶아낸 후에 고추장 소스를 넣고 고루 섞어줍니다.\n" +
                "7. 비빔밥을 그릇에 담은 후 위에 계란후라이나 노른자를 올리고, 통깨를 뿌려줍니다.\n" +
                "8. 마지막으로 참기름을 더해주면 완성입니다."),
        KoreanFood("잡채", R.drawable.japchae, "당면, 소고기, 당근, 시금치, 양파, 버섯, 설탕, 간장, 소금", "잡채 레시피 설명:\n" +
                "1. 먼저 불린 당면을 물기를 제거하고 적당한 길이로 자릅니다.\n" +
                "2. 채소들을 얇게 썬 후, 당근은 핀셀로 채를 낼 수도 있습니다.\n" +
                "3. 소고기는 얇게 썰어 준비하고, 계란은 푼 후 팬에 부쳐 계란후라이를 만듭니다.\n" +
                "4. 팬에 식용유를 두르고 다진 마늘과 다진 생강을 볶아 향을 내줍니다.\n" +
                "5. 소고기를 넣어 익힌 후, 당면과 채소를 순서대로 넣어 볶아줍니다.\n" +
                "6. 설탕과 간장을 넣고 볶으면서 양념을 고루 섞어줍니다.\n" +
                "7. 재료들이 어느 정도 익으면 간을 맞추기 위해 소금을 조절합니다.\n" +
                "8. 잡채가 잘 익고 양념이 고루 섞인 후에는 그릇에 담고 계란후라이와 통깨를 올려줍니다.\n" +
                "9. 마지막으로 참기름을 뿌려주면 완성입니다."),
        KoreanFood("육개장", R.drawable.yukgaejang, "소고기, 무, 양파, 대파, 고추, 된장, 소금, 간장", "육개장 레시피 설명:\n" +
                "1. 사골이나 갈비뼈를 끓여 육수를 준비합니다. 필요한 경우 건재료와 함께 끓여 깊은 맛을 내도 좋습니다.\n" +
                "2. 쇠고기는 얇게 썬 후, 다진 마늘과 생강, 소금을 넣어 재워줍니다.\n" +
                "3. 무와 양파를 준비한 두꺼운 소매금으로 채 썰어 넣습니다.\n" +
                "4. 냄비에 된장과 고춧가루를 넣고 볶아줍니다. 고춧가루를 먼저 볶아 홍색으로 변하도록 해야 합니다.\n" +
                "5. 끓는 육수를 조금씩 넣어 가며 국물을 만들어줍니다.\n" +
                "6. 육수가 끓기 시작하면 쇠고기를 넣고 살짝 익혀줍니다.\n" +
                "7. 무와 양파를 넣고 중간 불에서 끓여줍니다.\n" +
                "8. 청양고추와 미나리를 넣고 간을 맞추기 위해 소금과 간장을 조절합니다.\n" +
                "9. 대파와 참기름을 더해 마무리합니다.")
    )

    fun getFoodList(): List<KoreanFood> {
        return foodList
    }
}

@Composable
fun KoreanFoodMenu() {
    val koreanFoodDatabase = KoreanFoodDatabase()
    val foodList = koreanFoodDatabase.getFoodList()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val backgroundImage = painterResource(id = R.drawable.koreanfoodbackgroundtwo)
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
                    KoreanFoodButton(food)
                }
            }
        )
    }
}

@Composable
fun KoreanFoodButton(food: KoreanFood) {
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
fun RecipeButton(food: KoreanFood) {
    Column(modifier = Modifier
        .width(300.dp)
        .height(450.dp)
        .background(color = Color.White)) {
        Text(text = "재료: ${food.ingredient}")
        Text(text = food.explanation)
    }
}

@Preview(showBackground = true)
@Composable
fun KoreanFoodMenuPreview() {
    KoreanFoodMenu()
}