package com.tutorials.contentprovider

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import com.tutorials.contentprovider.ui.theme.ContentProviderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            resources
            ContentProviderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(contentResolver)
                }
            }
        }

    }
}

@Composable
fun Greeting(contentResolver: ContentResolver, modifier: Modifier = Modifier) {
    val langs = stringArrayResource(R.array.prog_languages)
    println("String Resource languages: ${langs[0]} ,${langs[1]}")

    val item1 = pluralStringResource(id = R.plurals.counter, count = 1, 1, 10)
    val item2 = pluralStringResource(id = R.plurals.counter, count = 2, 4, 10)
    val item3 = pluralStringResource(id = R.plurals.counter, count = 3, 4, 10)

    println("String Resource plurals: $item1, $item2, $item3")

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .fillMaxHeight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            insertData(contentResolver = contentResolver)
        }) {
            Text(
                text = "Insert Data"
            )
        }
        Button(onClick = { /*TODO*/ }) {
            Text(
                text = "Update Data"
            )
        }
    }

}

private fun insertData(contentResolver: ContentResolver) {
    val uri = Uri.parse("content://com.tutorials.contentprovider.SharedPrefContentProvider")
    val values = ContentValues()
    values.put("key", "Om Muruga")

    contentResolver.insert(uri, values)
}
