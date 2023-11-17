import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import com.erdemserhat.conceptor.data.database.repository.posts.Posts

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
        fun savePost(post: Posts)
        fun detachView()
        fun hasStoragePermission(): Boolean


    }
}
