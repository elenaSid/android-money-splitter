package com.elena.moneysplitter.extras

import android.content.Context
import android.graphics.*
import android.net.Uri
import android.view.View
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import com.elena.moneysplitter.R
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDate

/**
 * @author elena
 */
object ShareHelper {

    fun getUri(context: Context, view: View): Uri {
        val rootBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val rootCanvas = Canvas(rootBitmap)
        rootCanvas.drawColor(Color.WHITE, PorterDuff.Mode.DST_ATOP)

        val contentBitmap = viewToBitmap(view)
        rootCanvas.drawBitmap(contentBitmap, 0f, 0f, null)
        drawLogo(context, rootCanvas, view.height)

        val file = saveFile(context, rootBitmap)
        if (!contentBitmap.isRecycled) {
            contentBitmap.recycle()
        }
        if (!rootBitmap.isRecycled) {
            rootBitmap.recycle()
        }

        return FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)
    }

    private fun viewToBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.layout(view.left, view.top, view.right, view.bottom)
        view.draw(canvas)
        return bitmap
    }

    private fun drawLogo(context: Context, canvas: Canvas, y: Int) {
        val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        val textSize = 14.toPx().toFloat()
        textPaint.color = Color.parseColor("#6636A4E9")
        textPaint.typeface = ResourcesCompat.getFont(context, R.font.lobster)
        textPaint.textAlign = Paint.Align.LEFT
        textPaint.textSize = textSize
        canvas.drawText(context.getString(R.string.app_name), textSize / 2, y - textSize, textPaint)
    }

    private fun saveFile(context: Context, bitmap: Bitmap): File {
        val date = LocalDate.now().toString().replace("-", "_")
        val filename = String.format("money_splitter_%s.png", date)
        val currentFile = File(context.externalCacheDirs[0], filename)

        val fileOutputStream = FileOutputStream(currentFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        fileOutputStream.flush()
        fileOutputStream.close()
        return currentFile
    }
}