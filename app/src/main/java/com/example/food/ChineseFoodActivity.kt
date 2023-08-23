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

class ChineseFoodActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChineseFoodMenu()
        }
    }
}

data class ChineseFood(
    val name: String,
    val imageRes: Int,
    val ingredient: String,
    val explanation: String
)

class ChineseFoodDatabase {
    private val foodList = listOf(
        ChineseFood("마파두부", R.drawable.mafa, "두부, 돼지고기 , 대파, 미나리, 고추장, 된장, 간장", "마파두부 레시피 설명:\n" +
                "1. 두부를 큼직하게 자르고, 미리 소금물에 10분 정도 담가서 물기를 빼줍니다.\n" +
                "2. 팬에 식용유를 두르고 다진 마늘과 생강을 볶아 향을 내줍니다.\n" +
                "3. 쇠고기나 돼지고기를 넣고 익혀줍니다.\n" +
                "4. 고추냉이를 잘게 다져 넣고 볶아줍니다.\n" +
                "5. 간장과 고추장을 섞어서 넣고 볶아줍니다.\n" +
                "6. 두부를 넣고 섞어주면서 볶아줍니다. 두부가 파송해지기 전까지 익혀주면 좋습니다.\n" +
                "7. 미나리를 넣고 고루 섞어주고, 청주나 미림을 넣어 맛을 조절합니다.\n" +
                "8. 대파를 뿌려 마무리합니다.\n" +
                "9. 마지막에 고추기름을 뿌려주면 완성입니다."),
        ChineseFood("고추잡채", R.drawable.gochujapchae, "돼지고기, 당면, 양파, 당근, 피망, 대파, 간장, 설탕, 참기름", "고추잡채 레시피 설명:\n" +
                "1. 고추는 반으로 갈라 씨를 빼고 내용물을 제거한 후, 물에 담가서 약간 식힙니다.\n" +
                "2. 쇠고기나 돼지고기는 얇게 썬 후, 다진 마늘과 생강, 간장, 설탕, 참기름을 넣고 재워줍니다.\n" +
                "3. 야채들은 채썰어 준비합니다. 당근은 핀셀로 채를 낼 수 있습니다.\n" +
                "4. 냄비나 팬에 식용유를 두르고 다진 마늘과 생강을 볶아 향을 내줍니다.\n" +
                "5. 고기를 넣고 익힌 후, 야채를 넣어 볶아줍니다.\n" +
                "6. 간장, 설탕, 참기름을 섞어 양념을 만들고 고기와 야채에 골고루 섞어줍니다.\n" +
                "7. 고추를 물기를 제거하고 손질한 후, 준비한 고기와 야채를 고추 안으로 채워줍니다.\n" +
                "8. 팬에 식용유를 두르고 고추잡채를 익힙니다.\n" +
                "9. 고추잡채 위에 통깨를 뿌려서 완성합니다."),
        ChineseFood("자장면", R.drawable.jajangmyeon, "면, 양파, 고추, 돼지고기, 대파, 간장, 설탕, 참기름", "자장면 레시피 설명:\n" +
                "1. 면을 끓여서 준비합니다. 적당히 익혀서 너무 무르지 않게 건져둡니다.\n" +
                "2. 돼지고기나 쇠고기를 양념에 버무려서 재워줍니다.\n" +
                "3. 팬에 식용유를 두르고 다진 마늘과 생강을 볶아 향을 내줍니다.\n" +
                "4. 양파, 당근을 넣고 볶아줍니다.\n" +
                "5. 고기를 넣고 익힌 후, 간장과 설탕을 넣어 볶아줍니다.\n" +
                "6. 물을 조금씩 넣어가며 간을 맞춥니다. \n" +
                "7. 면을 팬에 넣고 볶아줍니다. 면에 양념이 골고루 묻도록 섞어줍니다.\n" +
                "8. 대파를 넣고 볶아준 후 참기름을 뿌려 마무리합니다.\n" +
                "9. 그릇에 담아 후추를 조금 뿌려준 후, 자장면을 즐깁니다.")
    )

    fun getFoodList(): List<ChineseFood> {
        return foodList
    }
}

@Composable
fun ChineseFoodMenu() {
    val chineseFoodDatabase = ChineseFoodDatabase()
    val foodList = chineseFoodDatabase.getFoodList()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val backgroundImage = painterResource(id = R.drawable.chinesefoodbackground)
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
                    ChineseFoodButton(food)
                }
            }
        )
    }
}

@Composable
fun ChineseFoodButton(food: ChineseFood) {
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
fun RecipeButton(food: ChineseFood) {
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
fun ChineseFoodPreview() {
    ChineseFoodMenu()
}