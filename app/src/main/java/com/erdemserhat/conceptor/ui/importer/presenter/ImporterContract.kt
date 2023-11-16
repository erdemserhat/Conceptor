import android.content.Context
import android.content.Intent
import android.graphics.Bitmap

interface ImporterContract {

    interface View {
        fun showSelectedImage(bitmap: Bitmap?)

        fun showPermissionNeededMessage()
        fun showPermissionSnackbar(permission: String)
        fun openToGallery()
        fun requestStoragePermission()

        fun getViewContext(): Context
    }

    interface Presenter {

        fun handleImageSelection(data: Intent?)
        fun handlePermissionResult(isGranted: Boolean)
        fun attachView(view: View)
        fun detachView()
        fun hasStoragePermission(): Boolean


    }
}
