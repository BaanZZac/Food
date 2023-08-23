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

class JapaneseFoodActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JapaneseFoodMenu()
        }
    }
}

data class JapaneseFood(
    val name: String,
    val imageRes: Int,
    val ingredient: String,
    val explanation: String
)

class JapaneseFoodDatabase {
    private val foodList = listOf(
        JapaneseFood("동회(덮밥류)", R.drawable.sashimiricebowl, "회, 쌀, 다시다츠유, 양파, 대파, 계란", "동회(덮밥류) 레시피 설명:\n" +
                "1. 밥을 준비하고 그릇에 담아줍니다.\n" +
                "2. 신선한 생선 회를 썰어서 준비합니다.\n" +
                "3. 야채들도 적절한 크기로 썰어줍니다.\n" +
                "4. 간장 양념을 만들어 간을 맞춰줍니다.\n" +
                "5. 밥 위에 신선한 생선 회와 야채를 얹어줍니다.\n" +
                "6. 간장 양념을 조금씩 뿌려줍니다.\n" +
                "7. 계란을 후라이드나 계란말이로 만들어서 올려줍니다.\n" +
                "8. 와사비나 옵션 재료를 넣어서 맛을 더해줍니다."),
        JapaneseFood("카츠동", R.drawable.katsudon, "돼지고기, 쌀, 카츠소스, 양파, 대파, 밀가루, 계란, 소금, 후추", "카츠동 레시피 설명:\n" +
                "1. 돈까스를 양념하여 소금과 후추를 뿌려 준비합니다.\n" +
                "2. 돈까스를 밀가루, 달걀, 빵가루 순서로 튀김 옷을 입혀줍니다.\n" +
                "3. 팬에 식용유를 두르고 돈까스를 노릇하게 튀겨줍니다.\n" +
                "4. 돈까스를 기름에서 꺼내서 물기를 뺀 후, 돈까스 소스로 조리된 곳에 얹어줍니다.\n" +
                "5. 그릇에 밥을 담고, 돈까스를 올려서 얹어줍니다.\n" +
                "6. 카츠동 소스를 돈까스 위에 뿌려주고, 양상추나 다른 옵션 재료를 곁들여 줍니다."),
        JapaneseFood("우동", R.drawable.udon, "우동면, 다시다츠유, 대파, 어묵, 다시마", "우동 레시피 설명:\n" +
                "1. 다시마와 물 또는 다시마 다시물을 끓여 다시마 국물을 만듭니다.\n" +
                "2. 국물에 간장과 미리원을 넣어 양념한 후, 적절한 간을 맞춥니다.\n" +
                "3. 우동 면을 끓는 물에 살짝 익힌 후 찬물에 헹궈 준비합니다.\n" +
                "4. 그릇에 우동 면을 담고, 다시마 국물을 부어줍니다.\n" +
                "5. 면 위에 다양한 면에 들어갈 재료를 얹어줍니다.\n" +
                "6. 필요한 경우 어묵나 삶은 계란을 추가로 얹어줍니다.")
    )

    fun getFoodList(): List<JapaneseFood> {
        return foodList
    }
}

@Composable
fun JapaneseFoodMenu() {
    val japaneseFoodDatabase = JapaneseFoodDatabase()
    val foodList = japaneseFoodDatabase.getFoodList()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val backgroundImage = painterResource(id = R.drawable.fresh)
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
                    JapaneseFoodButton(food)
                }
            }
        )
    }
}

@Composable
fun JapaneseFoodButton(food: JapaneseFood) {
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
fun RecipeButton(food: JapaneseFood) {
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
fun JapaneseFoodPreview() {
    JapaneseFoodMenu()
}