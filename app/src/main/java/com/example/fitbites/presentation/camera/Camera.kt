import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.util.concurrent.ExecutionException
import android.Manifest
import android.nfc.Tag
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fitbites.navigation.ROUTE_ANALYSIS
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import com.example.fitbites.presentation.camera.CameraViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CameraScreen(
    navController: NavController,
    ) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }
    val cameraViewModel: CameraViewModel = hiltViewModel(LocalContext.current as ComponentActivity)

//    LaunchedEffect(Unit) {
//        cameraViewModel.uploadStatus.collectLatest { status ->
//            Log.d("UploadStatus", "Current status: $status")
//            if (status == "Upload successful") {
//                navController.navigate(ROUTE_ANALYSIS)
//            }
//        }
//    }

    if (!hasCameraPermission(context)) {
        ActivityCompat.requestPermissions(
            context as ComponentActivity,
            arrayOf(Manifest.permission.CAMERA),
            0
        )
        return
    }

    val cameraProvider = try {
        cameraProviderFuture.get()
    } catch (e: ExecutionException) {
        Log.e("CameraScreen", "CameraProvider initialization failed", e)
        return
    } catch (e: InterruptedException) {
        Log.e("CameraScreen", "CameraProvider initialization was interrupted", e)
        return
    }

    DisposableEffect(Unit) {
        onDispose {
            releaseCamera(cameraProvider)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        val previewView = remember { PreviewView(context) }.apply {
            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
        }

        AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxSize()
        )

        setupCamera(
            cameraProvider = cameraProvider,
            lifecycleOwner = lifecycleOwner, // Pass lifecycleOwner
            previewView = previewView
        ) { capture ->
            imageCapture = capture
        }

        CaptureButton(onCaptureClick = {
            imageCapture?.let {
                capturePhoto(it, context) { photoFile ->
                    cameraViewModel.uploadPhoto(photoFile, context)
                    navController.navigate(ROUTE_ANALYSIS)
                }
            }
        })
    }
}


private fun releaseCamera(cameraProvider: ProcessCameraProvider) {
    try {
        cameraProvider.unbindAll()
    } catch (e: Exception) {
        Log.e("ReleaseCamera", "Failed to release camera", e)
    }
}


private fun setupCamera(
    cameraProvider: ProcessCameraProvider,
    lifecycleOwner: LifecycleOwner, // Pass lifecycleOwner instead of using LocalContext
    previewView: PreviewView,
    onImageCaptureReady: (ImageCapture) -> Unit
) {
    val preview = Preview.Builder().build()

    val capture = ImageCapture.Builder().build()

    preview.setSurfaceProvider(previewView.surfaceProvider)

    try {
        cameraProvider.unbindAll()

        // Use the provided lifecycleOwner instead of LocalContext
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            CameraSelector.DEFAULT_BACK_CAMERA,
            preview,
            capture
        )

        onImageCaptureReady(capture)

    } catch (e: Exception) {
        Log.e("setupCamera", "Use case binding failed", e)
    }
}


private fun capturePhoto(imageCapture: ImageCapture, context: Context, onPhotoCaptured: (File) -> Unit) {
    val outputFile = File(
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        "photo_${System.currentTimeMillis()}.jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(outputFile).build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                onPhotoCaptured(outputFile)
                Toast.makeText(context, "Photo saved: ${outputFile.absolutePath}", Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("CapturePhoto", "Image capture failed", exception)
                Toast.makeText(context, "Image capture failed: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        }
    )
}


private fun hasCameraPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED
}

@Composable
fun CaptureButton(onCaptureClick:() -> Unit) {
    FitbitesmobileTheme(dynamicColor = false) {
        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height((0.2f * LocalConfiguration.current.screenHeightDp).dp)
                    .background(Color.Transparent)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = (0.075 * LocalConfiguration.current.screenHeightDp).dp)
                    .size(70.dp)
                    .background(MaterialTheme.colorScheme.onPrimary, CircleShape)
                    .border(4.dp, Color.Green, CircleShape)
                    .clickable(onClick = onCaptureClick)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        bottom = (0.075 * LocalConfiguration.current.screenHeightDp).dp,
                        end = (0.1 * LocalConfiguration.current.screenWidthDp).dp
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable(onClick = {  })
                ) {
                    Icon(
                        imageVector = Icons.Default.AddPhotoAlternate,
                        contentDescription = "Upload Image",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Upload Image",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}


