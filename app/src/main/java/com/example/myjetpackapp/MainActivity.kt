package com.example.myjetpackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}
@Composable
fun App (){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login"){

        //deefine node for open fragments
//        composable(route = "registration"){
//            RegistrationScreen{
//                navController.navigate("main/${it}")
//            }
//        }

        composable(route = "login"){
            LoginFragment(navController)
        }
        composable(route = "data/{email}/{password}"){
            val email = it.arguments?.getString("email")?:""
            val password = it.arguments?.getString("password")?:""
            DataFragment(email,password)
        }

//        composable(route = "main/{email}", arguments = listOf(
//            navArgument("email"){
//                type = NavType.StringType
//            }
//        )){
//            val email = it.arguments?.getString("email")
//            MainScreen(email)
//        }
    }


}


@Composable
fun RegistrationScreen(onClick: (email : String) -> Unit) {
    Text(text = "Registration",
        style = MaterialTheme.typography.displayLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.clickable {
            onClick("devd@gmail.com")
        }
        )
}

@Composable
fun LoginFragment(navController: NavHostController) {
    Text(text = "Login", style = MaterialTheme.typography.displayLarge, textAlign = TextAlign.Center)

   // val (email, setEmail) = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("")   }
    val passwordState = remember { mutableStateOf("")   }
    
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            TextField(
                value = emailState.value,
                onValueChange = {emailState.value = it},
                label = {Text("Email")},
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {})
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = passwordState.value,
                onValueChange = {passwordState.value = it},
                label = {Text("Password")},
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    //val action =
                })
            )

            Button(onClick = {
                println("Entered Email : $emailState")
                navController.navigate("data/${emailState.value}/${passwordState.value}")
            },
                modifier = Modifier.padding(0.dp,10.dp)
                ) {

                Text("Login")
            }
        }
    }
}

@Composable
fun MainScreen(email: String?) {
    Text(text = "Main : $email", style = MaterialTheme.typography.displayLarge, textAlign = TextAlign.Center)
}

@Composable
fun DataFragment(email: String, password: String) {
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Text(text = "Data Email : $email ",
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Data Pass : $password",
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center)

    }
}