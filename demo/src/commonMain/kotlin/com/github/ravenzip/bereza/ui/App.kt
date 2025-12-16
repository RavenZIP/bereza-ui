import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import components.textfield.singleLine.SingleLineTextField
import form.mutableFormControl

@Composable
fun App() {
    MaterialTheme {
        val scope = rememberCoroutineScope()
        val control = remember { mutableFormControl("", coroutineScope = scope) }

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> SingleLineTextField(control) }
    }
}
