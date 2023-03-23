package id.ac.unpas.tokenlistrik.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.tokenlistrik.model.SetoranToken
import id.ac.unpas.tokenlistrik.persistences.SetoranTokenDao
import id.ac.unpas.tokenlistrik.ui.theme.Purple700
import id.ac.unpas.tokenlistrik.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanToken(setoranTokenDao: SetoranTokenDao) {
    val scope = rememberCoroutineScope()
    val tanggal = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val notoken = remember { mutableStateOf(TextFieldValue("")) }
    val nominal = remember { mutableStateOf(TextFieldValue("")) }
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Tanggal") },
            value = tanggal.value,
            onValueChange = {
                tanggal.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Berat") },
            value = notoken.value,
            onValueChange = {
                notoken.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
        )
        OutlinedTextField(
            label = { Text(text = "Berat") },
            value = nominal.value,
            onValueChange = {
                nominal.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val id = uuid4().toString()
                val item = SetoranToken(id, tanggal.value.text, nama.value.text,
                    notoken.value.text,nominal.value.text)
                scope.launch {
                    setoranTokenDao.insertAll(item)
                }

                tanggal.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                notoken.value = TextFieldValue("")
                nominal.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Save",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                tanggal.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                notoken.value = TextFieldValue("")
                nominal.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
