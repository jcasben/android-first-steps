package com.jcasben.jetpackcomponentscatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jcasben.jetpackcomponentscatalog.model.CheckBoxInfo
import com.jcasben.jetpackcomponentscatalog.ui.theme.JetpackComponentsCatalogTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            JetpackComponentsCatalogTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen2) {
                        composable<Screen1> {
                            Screen1(navController)
                        }
                        composable<Screen2> {
                            Screen2(navController)
                        }
                        composable<Screen3> {
                            Screen3(navController)
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object Screen1

@Serializable
data class Screen2(
    val name: String,
    val age: Int
)

@Serializable
object Screen3

@Composable
fun MyText() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "This is a Text")
        Text(text = "This is a Text", color = Color.Blue)
        Text(text = "This is a Text", fontWeight = FontWeight.ExtraBold)
        Text(text = "This is a Text", style = TextStyle(fontFamily = FontFamily.Cursive))
        Text(
            text = "This is a Text", style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(text = "This is a Text", style = TextStyle(textDecoration = TextDecoration.Underline))
        Text(
            text = "This is a Text", style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(TextDecoration.Underline, TextDecoration.LineThrough)
                )
            )
        )
        Text(text = "This is a Text", fontSize = 30.sp)
    }
}

@Composable
fun MyTextField() {
    var myText by remember { mutableStateOf("") }
    TextField(
        value = myText,
        onValueChange = { myText = it },
    )
}

@Composable
fun MyAdvancedTextField() {
    var myText by remember { mutableStateOf("") }
    TextField(value = myText, onValueChange = {
        myText = if (it.contains("a")) {
            it.replace("a", "")
        } else {
            it
        }
    }, label = { Text(text = "Introduce your name") })
}

@Composable
fun MyOutlinedTextField() {
    var myText by remember { mutableStateOf("") }
    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it },
        modifier = Modifier.padding(24.dp),
        label = { Text(text = "Introduce your name") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Magenta, unfocusedBorderColor = Color.Green
        )
    )
}

@Composable
fun MyStatelessComposable(name: String, onValueChange: (String) -> Unit) {
    TextField(value = name, onValueChange = { onValueChange(it) })
}

@Composable
fun MyButton() {
    var enabled by rememberSaveable { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { enabled = false }, enabled = enabled, colors = ButtonDefaults.buttonColors(
                contentColor = Color.White, containerColor = Color.Blue
            ), border = BorderStroke(5.dp, Color.Magenta)
        ) {
            Text(text = "Hello")
        }

        OutlinedButton(
            onClick = { },
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text(text = "Hello")
        }

        TextButton(onClick = {}) {
            Text(text = "Hello")
        }
    }
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "example"
    )
}

@Preview
@Composable
fun MyAdvancedImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "example",
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, color = Color.Blue, shape = CircleShape)
    )
}

@Preview
@Composable
fun MyIcon() {
    Icon(imageVector = Icons.Rounded.Star, contentDescription = "Star Icon", tint = Color.Blue)
}

@Composable
fun MyProgressBar() {
    var showLoading by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Red)
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 8.dp), color = Color.Blue, trackColor = Color.Cyan
            )
        }
        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Load")
        }
    }
}

@Composable
fun MyAdvancedProgressBar() {
    var progress by rememberSaveable { mutableFloatStateOf(0.5f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(progress = { progress })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { progress -= 0.1f }, modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(text = "Remove progress")
            }
            Button(onClick = { progress += 0.1f }, modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(text = "Add progress")
            }
        }
    }
}

@Composable
fun MySwitch() {
    var state by rememberSaveable { mutableStateOf(false) }
    Switch(
        checked = state, onCheckedChange = { state = !state }, colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Blue,
            checkedThumbColor = Color.Cyan,
            uncheckedTrackColor = Color.Magenta,
            checkedTrackColor = Color.Green
        )
    )
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable { mutableStateOf(false) }
    Checkbox(
        checked = state, onCheckedChange = { state = !state }, colors = CheckboxDefaults.colors(
            uncheckedColor = Color.Magenta, checkedColor = Color.Blue, checkmarkColor = Color.Yellow
        )
    )
}

@Composable
fun MyTextCheckBox() {
    var state by rememberSaveable { mutableStateOf(false) }
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Text(text = "Check")
    }
}

@Composable
fun MyTextCheckBoxHoisting(checkBoxInfo: CheckBoxInfo) {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checkBoxInfo.selected,
            onCheckedChange = { checkBoxInfo.onCheckedChange(!checkBoxInfo.selected) })
        Text(text = checkBoxInfo.title)
    }
}

@Composable
fun getOptions(titles: List<String>): List<CheckBoxInfo> {
    return titles.map {
        var status by rememberSaveable { mutableStateOf(false) }
        CheckBoxInfo(
            title = it,
            selected = status,
            onCheckedChange = { newStatus -> status = newStatus })
    }
}

@Composable
fun MyTriStateCheckbox() {
    var state by rememberSaveable { mutableStateOf(ToggleableState.Off) }
    TriStateCheckbox(state = state, onClick = {
        state = when (state) {
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun MyRadioButton() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = false, onClick = { }, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Cyan, unselectedColor = Color.Magenta
            )
        )
        Text(text = "Option 1")
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = name == "Option 1", onClick = { onItemSelected("Option 1") })
        Text(text = "Option 1")
    }
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = name == "Option 2", onClick = { onItemSelected("Option 2") })
        Text(text = "Option 2")
    }
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = name == "Option 3", onClick = { onItemSelected("Option 3") })
        Text(text = "Option 3")
    }
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = name == "Option 4", onClick = { onItemSelected("Option 4") })
        Text(text = "Option 4")
    }
}

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = Color.Blue,
            contentColor = Color.Magenta
        ),
        border = BorderStroke(4.dp, Color.Yellow)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Example 1")
            Text(text = "Example 2")
            Text(text = "Example 3")
        }
    }
}

@Composable
fun MyBadgeBox() {
    BadgedBox(badge = {
        Badge(
            containerColor = Color.Red,
            contentColor = Color.White
        ) { Text(text = "200") }
    }, modifier = Modifier.padding(20.dp)) {
        Icon(imageVector = Icons.Rounded.Star, contentDescription = "")
    }
}

@Composable
fun MyDivider() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )
}

@Composable
fun MyDropDownMenu() {
    var selectedText by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val desserts = listOf("Ice Cream", "Yogurt", "Fruit", "Mochi")

    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { dessert ->
                DropdownMenuItem(text = { Text(text = dessert) }, onClick = {
                    expanded = false
                    selectedText = dessert
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComponentsCatalogTheme {
        MyBadgeBox()
    }
}