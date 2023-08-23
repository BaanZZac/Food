package com.example.food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import java.time.format.TextStyle

class Ingredient : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestNavi()
        }
    }
}

data class RecipeInfo(val ingredients: String, val description: String)

val recipeMap = mapOf(
    "비빔밥" to RecipeInfo("쌀, 소고기, 당근, 시금치, 콩나물, 계란, 고추장, 참기름", "비빔밥 레시피 설명:\n" +
            "1. 먼저 밥을 삶아서 준비합니다. 밥은 조금 차갑게 식히는 것이 좋습니다.\n" +
            "2. 고기는 얇게 썬 후 볶거나 구워서 준비합니다. 소금과 간장으로 간을 조절합니다.\n" +
            "3. 야채들은 씻어서 먹기 좋은 크기로 썬 후 데쳐줍니다.\n" +
            "4. 계란은 푼 후 팬에 부쳐 계란후라이를 만들거나, 노른자를 보존하여 사용합니다.\n" +
            "5. 고추장을 화장실크기에 따라 적당한 양을 준비합니다. 다진 마늘, 참기름을 넣어 고루 섞어줍니다.\n" +
            "6. 준비된 밥과 야채, 고기를 볶아낸 후에 고추장 소스를 넣고 고루 섞어줍니다.\n" +
            "7. 비빔밥을 그릇에 담은 후 위에 계란후라이나 노른자를 올리고, 통깨를 뿌려줍니다.\n" +
            "8. 마지막으로 참기름을 더해주면 완성입니다."),

    "잡채" to RecipeInfo("당면, 소고기, 당근, 시금치, 양파, 버섯, 설탕, 간장, 소금", "잡채 레시피 설명:\n" +
            "1. 먼저 불린 당면을 물기를 제거하고 적당한 길이로 자릅니다.\n" +
            "2. 채소들을 얇게 썬 후, 당근은 핀셀로 채를 낼 수도 있습니다.\n" +
            "3. 소고기는 얇게 썰어 준비하고, 계란은 푼 후 팬에 부쳐 계란후라이를 만듭니다.\n" +
            "4. 팬에 식용유를 두르고 다진 마늘과 다진 생강을 볶아 향을 내줍니다.\n" +
            "5. 소고기를 넣어 익힌 후, 당면과 채소를 순서대로 넣어 볶아줍니다.\n" +
            "6. 설탕과 간장을 넣고 볶으면서 양념을 고루 섞어줍니다.\n" +
            "7. 재료들이 어느 정도 익으면 간을 맞추기 위해 소금을 조절합니다.\n" +
            "8. 잡채가 잘 익고 양념이 고루 섞인 후에는 그릇에 담고 계란후라이와 통깨를 올려줍니다.\n" +
            "9. 마지막으로 참기름을 뿌려주면 완성입니다."),

    "육개장" to RecipeInfo("소고기, 무, 양파, 대파, 고추, 된장, 소금, 간장", "육개장 레시피 설명:\n" +
            "1. 사골이나 갈비뼈를 끓여 육수를 준비합니다. 필요한 경우 건재료와 함께 끓여 깊은 맛을 내도 좋습니다.\n" +
            "2. 쇠고기는 얇게 썬 후, 다진 마늘과 생강, 소금을 넣어 재워줍니다.\n" +
            "3. 무와 양파를 준비한 두꺼운 소매금으로 채 썰어 넣습니다.\n" +
            "4. 냄비에 된장과 고춧가루를 넣고 볶아줍니다. 고춧가루를 먼저 볶아 홍색으로 변하도록 해야 합니다.\n" +
            "5. 끓는 육수를 조금씩 넣어 가며 국물을 만들어줍니다.\n" +
            "6. 육수가 끓기 시작하면 쇠고기를 넣고 살짝 익혀줍니다.\n" +
            "7. 무와 양파를 넣고 중간 불에서 끓여줍니다.\n" +
            "8. 청양고추와 미나리를 넣고 간을 맞추기 위해 소금과 간장을 조절합니다.\n" +
            "9. 대파와 참기름을 더해 마무리합니다."),

    "마파두부" to RecipeInfo("두부, 돼지고기 , 대파, 미나리, 고추장, 된장, 간장", "마파두부 레시피 설명:\n" +
            "1. 두부를 큼직하게 자르고, 미리 소금물에 10분 정도 담가서 물기를 빼줍니다.\n" +
            "2. 팬에 식용유를 두르고 다진 마늘과 생강을 볶아 향을 내줍니다.\n" +
            "3. 쇠고기나 돼지고기를 넣고 익혀줍니다.\n" +
            "4. 고추냉이를 잘게 다져 넣고 볶아줍니다.\n" +
            "5. 간장과 고추장을 섞어서 넣고 볶아줍니다.\n" +
            "6. 두부를 넣고 섞어주면서 볶아줍니다. 두부가 파송해지기 전까지 익혀주면 좋습니다.\n" +
            "7. 미나리를 넣고 고루 섞어주고, 청주나 미림을 넣어 맛을 조절합니다.\n" +
            "8. 대파를 뿌려 마무리합니다.\n" +
            "9. 마지막에 고추기름을 뿌려주면 완성입니다."),

    "고추잡채" to RecipeInfo("돼지고기, 당면, 양파, 당근, 피망, 대파, 간장, 설탕, 참기름", "고추잡채 레시피 설명:\n" +
            "1. 고추는 반으로 갈라 씨를 빼고 내용물을 제거한 후, 물에 담가서 약간 식힙니다.\n" +
            "2. 쇠고기나 돼지고기는 얇게 썬 후, 다진 마늘과 생강, 간장, 설탕, 참기름을 넣고 재워줍니다.\n" +
            "3. 야채들은 채썰어 준비합니다. 당근은 핀셀로 채를 낼 수 있습니다.\n" +
            "4. 냄비나 팬에 식용유를 두르고 다진 마늘과 생강을 볶아 향을 내줍니다.\n" +
            "5. 고기를 넣고 익힌 후, 야채를 넣어 볶아줍니다.\n" +
            "6. 간장, 설탕, 참기름을 섞어 양념을 만들고 고기와 야채에 골고루 섞어줍니다.\n" +
            "7. 고추를 물기를 제거하고 손질한 후, 준비한 고기와 야채를 고추 안으로 채워줍니다.\n" +
            "8. 팬에 식용유를 두르고 고추잡채를 익힙니다.\n" +
            "9. 고추잡채 위에 통깨를 뿌려서 완성합니다."),

    "자장면" to RecipeInfo("면, 양파, 고추, 돼지고기, 대파, 간장, 설탕, 참기름", "자장면 레시피 설명:\n" +
            "1. 면을 끓여서 준비합니다. 적당히 익혀서 너무 무르지 않게 건져둡니다.\n" +
            "2. 돼지고기나 쇠고기를 양념에 버무려서 재워줍니다.\n" +
            "3. 팬에 식용유를 두르고 다진 마늘과 생강을 볶아 향을 내줍니다.\n" +
            "4. 양파, 당근을 넣고 볶아줍니다.\n" +
            "5. 고기를 넣고 익힌 후, 간장과 설탕을 넣어 볶아줍니다.\n" +
            "6. 물을 조금씩 넣어가며 간을 맞춥니다. \n" +
            "7. 면을 팬에 넣고 볶아줍니다. 면에 양념이 골고루 묻도록 섞어줍니다.\n" +
            "8. 대파를 넣고 볶아준 후 참기름을 뿌려 마무리합니다.\n" +
            "9. 그릇에 담아 후추를 조금 뿌려준 후, 자장면을 즐깁니다."),

    "스테이크" to RecipeInfo("소고기, 감자, 양파, 버터, 마늘, 로즈마리", "스테이크 레시피 설명:\n" +
            "1. 고기 표면에 소금과 후추를 골고루 뿌립니다. 양면에 양념을 뿌리는 것을 잊지 마세요.\n" +
            "2. 팬을 중불로 예열하거나 그릴을 예열합니다. 팬을 사용할 경우, 식용유나 버터를 두르고 고기를 올려줍니다. 고기가 접촉한 면이 바삭하게 볶아질 때까지 조리합니다. (고기의 두께에 따라 조리 시간이 달라질 수 있습니다.)\n" +
            "3. 한 면을 조리한 후에는 고기를 뒤집어 반대 면도 같은 방법으로 조리합니다. 고기의 익음 정도를 확인하여 선호하는 익음 정도에 맞게 조리합니다.\n" +
            "4. 고기를 그릴에서 조리할 때는 간편하게 고기 양면을 그릴망 위에 올려두고 조리합니다.\n" +
            "5. 고기를 그릴에서 꺼내거나 팬에서 꺼낸 후, 데쳐놓고 2-3분 정도 잠깐 쉬게 해줍니다. 이렇게 하면 고기의 주스가 골고루 분포하여 맛과 풍미를 유지할 수 있습니다.\n" +
            "6. 잠시 쉬어간 고기를 접시에 옮기고 선택적으로 마늘, 로즈마리, 타임 등의 허브나 간장, 갈릭버터 등을 얹어 향과 맛을 더해줍니다."),

    "비프 스튜" to RecipeInfo("소고기, 감자, 당근, 양파, 버터, 밀가루, 로즈마리", "비프 스튜 레시피 설명:\n" +
            "1. 쇠고기를 큰 조각으로 자르고, 밀가루나 빵 가루로 뭍힙니다.\n" +
            "2. 팬에 식용유나 버터를 두르고 고기를 노릇하게 볶아줍니다. 고기의 색이 변하면 데칠 준비가 된 것입니다.\n" +
            "3. 데친 고기를 팬에서 꺼내고, 같은 팬에 다양한 야채들을 넣어 노릇하게 볶아줍니다.\n" +
            "4. 볶아낸 야채에 고기를 다시 넣고 육수나 물, 와인이나 비어를 부어줍니다.\n" +
            "5. 소금과 후추, 허브를 넣고 가장 작은 불로 줄여 끓여줍니다.\n" +
            "6. 뚜껑을 덮고 느리게 끓여줍니다. 고기와 야채가 부드러워질 때까지 1시간 이상 조리할 수 있습니다.\n" +
            "7. 스튜가 익으면 간을 맞추고, 필요하다면 추가적인 허브나 옵션 재료를 넣어줍니다.\n" +
            "8. 스튜를 그릇에 담고 겉면이 빵 가루로 덮이도록 조금씩 빚어줍니다."),

    "마카로니 앤 치즈" to RecipeInfo("마카로니, 치즈, 우유, 버터, 밀가루, 소금, 후추", "마카로니 앤 치즈설명 레시피 설명:\n" +
            "1. 물에 소금을 넣고 마카로니를 살짝 익게 끓여줍니다. 패키지에 표시된 시간보다 1-2분 짧게 끓이세요. (알뜰한 익음 정도가 좋습니다.)\n" +
            "2. 마카로니를 물에서 건져내고 찬물에 헹구어 식혀줍니다.\n" +
            "3. 소스를 만들기 위해 중간 불에서 버터를 녹여줍니다.\n" +
            "4. 밀가루를 넣고 버터와 잘 섞어줍니다. 밀가루가 익을 때까지 계속 볶아줍니다.\n" +
            "5. 우유를 조금씩 넣으면서 점점 소스가 뿌리게 만들어줍니다.\n" +
            "6. 우유가 끓으면 치즈를 넣고 녹을 때까지 저어줍니다.\n" +
            "7. 마카로니를 소스에 넣고 고루 섞어줍니다.\n" +
            "8. 소금과 후추로 간을 맞춰줍니다. 옵션으로 마스터드나 파우더드 머스타드 등을 넣어 풍미를 더해줄 수 있습니다.\n" +
            "9. 마지막으로 잘 섞은 마카로니 앤 치즈를 그릇에 담아줍니다."),

    "동회(덮밥류)" to RecipeInfo("회, 쌀, 다시다츠유, 양파, 대파, 계란", "동회(덮밥류) 레시피 설명:\n" +
            "1. 밥을 준비하고 그릇에 담아줍니다.\n" +
            "2. 신선한 생선 회를 썰어서 준비합니다.\n" +
            "3. 야채들도 적절한 크기로 썰어줍니다.\n" +
            "4. 간장 양념을 만들어 간을 맞춰줍니다.\n" +
            "5. 밥 위에 신선한 생선 회와 야채를 얹어줍니다.\n" +
            "6. 간장 양념을 조금씩 뿌려줍니다.\n" +
            "7. 계란을 후라이드나 계란말이로 만들어서 올려줍니다.\n" +
            "8. 와사비나 옵션 재료를 넣어서 맛을 더해줍니다."),

    "카츠동" to RecipeInfo("돼지고기, 쌀, 카츠소스, 양파, 대파, 밀가루, 계란, 소금, 후추", "카츠동 레시피 설명:\n" +
            "1. 돈까스를 양념하여 소금과 후추를 뿌려 준비합니다.\n" +
            "2. 돈까스를 밀가루, 달걀, 빵가루 순서로 튀김 옷을 입혀줍니다.\n" +
            "3. 팬에 식용유를 두르고 돈까스를 노릇하게 튀겨줍니다.\n" +
            "4. 돈까스를 기름에서 꺼내서 물기를 뺀 후, 돈까스 소스로 조리된 곳에 얹어줍니다.\n" +
            "5. 그릇에 밥을 담고, 돈까스를 올려서 얹어줍니다.\n" +
            "6. 카츠동 소스를 돈까스 위에 뿌려주고, 양상추나 다른 옵션 재료를 곁들여 줍니다."),

    "우동" to RecipeInfo("우동면, 다시다츠유, 대파, 어묵, 다시마", "우동 레시피 설명:\n" +
            "1. 다시마와 물 또는 다시마 다시물을 끓여 다시마 국물을 만듭니다.\n" +
            "2. 국물에 간장과 미리원을 넣어 양념한 후, 적절한 간을 맞춥니다.\n" +
            "3. 우동 면을 끓는 물에 살짝 익힌 후 찬물에 헹궈 준비합니다.\n" +
            "4. 그릇에 우동 면을 담고, 다시마 국물을 부어줍니다.\n" +
            "5. 면 위에 다양한 면에 들어갈 재료를 얹어줍니다.\n" +
            "6. 필요한 경우 어묵나 삶은 계란을 추가로 얹어줍니다."),

    // 레시피 추가
)

@Composable
fun TestNavi() {
    val navController = rememberNavController()
    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = "profile") {
            composable("profile") { ProfileScreen(navController = navController) }
            composable(
                "Recommendation/{selectedIngredients}",
                arguments = listOf(navArgument("selectedIngredients") { type = NavType.StringType })
            ) { backStackEntry ->
                val selectedIngredients = backStackEntry.arguments?.getString("selectedIngredients")
                RecommendationScreen(
                    navController = navController,
                    selectedIngredients = selectedIngredients?.split(",") ?: emptyList()
                )
            }

            for ((recipe, _) in recipeMap) {
                composable(route = recipe) { backStackEntry ->
                    val missingIngredients =
                        backStackEntry.arguments?.getString("missingIngredients")?.split(",")
                            ?: emptyList()
                    val selectedIngredients =
                        navController.previousBackStackEntry?.arguments?.getString("selectedIngredients")
                            ?.split(",") ?: emptyList()
                    RecipeScreen(
                        recipeInfo = recipeMap[recipe]!!,
                        selectedIngredients = selectedIngredients - missingIngredients
                    )
                }
            }
        }
    }
}


@Composable
fun ProfileScreen(navController: NavController) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        val ingredientCategories = mapOf(
            "채소" to listOf("당근", "시금치","콩나물", "호박", "양파", "버섯", "무", "대파", "고추", "미나리", "감자", "피망", "마늘","다시마"),
            "육류 및 콩류" to listOf("돼지고기", "소고기", "계란", "어묵", "회", "두부"),
            "양념 및 향신료" to listOf("소금", "후추","참기름", "간장", "고추장", "된장", "다시다츠유", "고추냉이", "설탕", "후추", "로즈마리", "카츠소스"),
            "유제품" to listOf("우유", "요거트", "치즈", "버터"),
            "곡류" to listOf("쌀", "밀가루", "오트밀", "당면", "면", "우동면", "마카로니")
        )

        var selectedIngredients by remember { mutableStateOf(emptyList<String>()) }

        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            // 배경 이미지 추가
            Image(
                painter = painterResource(id = R.drawable.ingredientbackground),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier.fillMaxSize(),

            ) {
                Box(
                    modifier = Modifier.padding(16.dp),
                    contentAlignment = Alignment.TopCenter


                ) {
                    Text(
                        text = "내가 가지고 있는 재료 선택",
                        color = Color.White,
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 33.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                for ((category, ingredients) in ingredientCategories) {
                    Column {
                        Text(
                            text = category,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier.padding(16.dp),
                            color = Color.White
                        )
                        LazyRow {
                            items(ingredients) { ingredient ->
                                IngredientButton(
                                    selectedIngredients = selectedIngredients,
                                    ingredient = ingredient
                                ) { clickedIngredient ->
                                    selectedIngredients =
                                        if (selectedIngredients.contains(clickedIngredient)) {
                                            selectedIngredients - clickedIngredient
                                        } else {
                                            selectedIngredients + clickedIngredient
                                        }
                                }
                            }
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                Button(
                    onClick = {
                        if (selectedIngredients.isNotEmpty()) {
                            val selectedIngredientsString = selectedIngredients.joinToString(",")
                            navController.navigate("Recommendation/$selectedIngredientsString")
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp)

                ) {
                    Text("선택완료")
                }
            }
            }
        }
    }
}

@Composable
fun IngredientButton(
    selectedIngredients: List<String>,
    ingredient: String,
    onIngredientClick: (String) -> Unit
) {
    val isSelected = selectedIngredients.contains(ingredient)

    Button(
        onClick = {
            onIngredientClick(ingredient)
        },
        colors = ButtonDefaults.buttonColors(if (isSelected) Color.Green else Color(0xbb404040))
    ) {
        Text(text = ingredient)
    }
}

@Composable
fun RecommendationScreen(navController: NavController, selectedIngredients: List<String>) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 배경 이미지 추가
        Image(
            painter = painterResource(id = R.drawable.ingredientbackground), // 배경 이미지 리소스 ID로 변경
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "추천 메뉴",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            for ((recipe, recipeInfo) in recipeMap) {
                val recipeIngredientsList = recipeInfo.ingredients.split(", ")
                val intersection = recipeIngredientsList.intersect(selectedIngredients).toList()
                if (intersection.size >= recipeIngredientsList.size * 0.5) {
                    val missingIngredients = recipeIngredientsList - intersection

                    Button(
                        onClick = {
                            navController.navigate(
                                route = "$recipe?missingIngredients=${
                                    missingIngredients.joinToString(",")
                                }"
                            )
                        },
                        modifier = Modifier.padding(bottom = 8.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xbb404040))
                    ) {
                        Text(
                            text = recipe,
                            style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp)
                        )
                    }
                }
            }

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "뒤로 가기",
                    style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}


@Composable
fun RecipeScreen(recipeInfo: RecipeInfo, selectedIngredients: List<String>) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 배경 이미지 추가
        Image(
            painter = painterResource(id = R.drawable.ingredientbackground), // 배경 이미지 리소스 ID로 변경
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "재료: ${recipeInfo.ingredients}",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color.White
            )


            Text(
                text = "가지고 있지 않은 재료:",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.White
            )

            val requiredIngredients = recipeInfo.ingredients.split(", ")
            val missingIngredients = requiredIngredients.filter { it !in selectedIngredients }
            for (ingredient in missingIngredients) {
                Text(
                    text = ingredient,
                    style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
                    color = Color.White
                )

            }
            Text(
                text = recipeInfo.description,
                style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color.White
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TestNaviPreview() {
    TestNavi()
}