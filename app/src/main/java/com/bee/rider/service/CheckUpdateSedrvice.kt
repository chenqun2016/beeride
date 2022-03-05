package com.bee.rider.service

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.core.app.JobIntentService
import com.bee.rider.utils.DeviceUtils
import com.blankj.utilcode.util.AppUtils
import java.io.File

/**
 * 创建时间：2022/1/8
 *
 * @Author： 陈陈陈
 * 功能描述：
 */
object CheckUpdateService : JobIntentService() {
    var downloadManager: DownloadManager? = null
    var mTaskId: Long = 0

    /**
     * 这个Service 唯一的id
     */
    val JOB_ID = 10111011

    /**
     * Convenience method for enqueuing work in to this service.
     */
    fun enqueueWork(context: Context, work: Intent) {
        enqueueWork(context, CheckUpdateService::class.java, JOB_ID, work)
    }


    override fun onHandleWork(intent: Intent) {
        val url = intent.getStringExtra("url")
        url?.let { downloadAPK(it) }
    }

    open fun downloadAPK(url: String) {
        DeviceUtils.clearApk(applicationContext, "beeUser.apk")
        val request = DownloadManager.Request(Uri.parse(url))
        val mimeTypeMap = MimeTypeMap.getSingleton()
        val mimeString =
            mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url))
        request.setMimeType(mimeString)
        request.setDestinationInExternalFilesDir(
            applicationContext,
            Environment.DIRECTORY_DOWNLOADS,
            "beeUser.apk"
        )
       val downloadManager:DownloadManager = applicationContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        this.downloadManager = downloadManager
        mTaskId = downloadManager.enqueue(request)
        applicationContext.registerReceiver(
            receiver,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
    }

    val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // TODO: 2021/9/12 暂时关闭  后期打开
            //  checkDownloadStatus();
        }
    }

    //检查下载状态
    open fun checkDownloadStatus() {
        val query = DownloadManager.Query()
        query.setFilterById(mTaskId) //筛选下载任务，传入任务ID，可变参数
        val c = downloadManager?.query(query)
        if (c != null) {
            if (c.moveToFirst()) {
                val status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))
                when (status) {
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        Log.i("CheckUpdateService","下载成功")
                        val file: File = File(
                            applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                            "beeRider.apk"
                        )
                        AppUtils.installApp(file)
                    }
                    DownloadManager.STATUS_FAILED ->  Log.i("CheckUpdateService","下载失败")
                }
            }
        }
    }
}