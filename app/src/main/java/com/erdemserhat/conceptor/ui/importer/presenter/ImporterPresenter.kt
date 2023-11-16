import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class ImporterPresenter : ImporterContract.Presenter {

    private var view: ImporterContract.View? = null

    override fun handleImageUpload(view: ImporterContract.View) {
        // Resim yükleme işlemini gerçekleştir
        // uploadImage fonksiyonunu çağırabilirsiniz.
    }

    override fun handleImageSelection(data: Intent?) {
        val selectedBitmap: Bitmap
        val imageData = data?.data
        if (imageData != null) {
            try {
                selectedBitmap = if (Build.VERSION.SDK_INT >= 28) {
                    val source = view?.getViewContext()?.contentResolver?.let {
                        ImageDecoder.createSource(
                            it, imageData
                        )
                    }
                    ImageDecoder.decodeBitmap(source!!)
                } else {
                    MediaStore.Images.Media.getBitmap(
                        view?.getViewContext()?.contentResolver,
                        imageData
                    )
                }
                selectedBitmap?.let {
                    view?.showSelectedImage(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }


    }

    override fun handlePermissionResult(isGranted: Boolean) {
        if (isGranted) {
            view?.showSelectedImage(null)
        } else {
            view?.showPermissionNeededMessage()
        }
    }

    override fun attachView(view: ImporterContract.View) {
        this.view = view
    }

    override fun detachView() {
        TODO("Not yet implemented")
    }

    override fun hasStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            view?.let {
                ContextCompat.checkSelfPermission(
                    it.getViewContext(),
                    android.Manifest.permission.READ_MEDIA_IMAGES
                )
            } == PackageManager.PERMISSION_GRANTED
        } else {
            view?.let {
                ContextCompat.checkSelfPermission(
                    it.getViewContext(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            } == PackageManager.PERMISSION_GRANTED
        }
    }
}
