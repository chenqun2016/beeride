package com.chenchen.bee_rider.ui.setting

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.databinding.FragmentCommonWebBinding
import com.gyf.immersionbar.ImmersionBar
/**
 - @Description: H5页面
 - @Author: bxy
 - @Time:  2022/1/16 下午6:52
 */
class CommonWebFragment : BaseFragment<FragmentCommonWebBinding>() {
    var innertitle = false
    private var i = 0

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCommonWebBinding {
        return FragmentCommonWebBinding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        initImmersionBar()
        //        是否使用内部标题
        innertitle = requireArguments().getBoolean("innertitle", true)
        //-1时不能在页面内跳转
        i = requireArguments().getInt("type", 0)
        initWebView()
        //.普通url
        val url = requireArguments().getString("url")
        if (!url.isNullOrEmpty()) {
           binding.webview.loadUrl(url)
        } else {
            val data = requireArguments().getString("data")
            if(!data.isNullOrEmpty()) {
                binding.webview.loadDataWithBaseURL(null, data, "text/html", "utf-8", null)
            }
        }
    }

    private fun initImmersionBar() {
        val mImmersionBar = ImmersionBar.with(this)
        mImmersionBar.statusBarDarkFont(true, 0.2f)
        mImmersionBar.init()
    }

    private val mWebChromeClient: WebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            // 进度发生变化
            binding.progressbar.progress = newProgress
        }

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val settings: WebSettings =  binding.webview.settings
        if (requireArguments().getBoolean("scale", false)) {
            settings.builtInZoomControls = true // 显示缩放按钮(wap网页不支持)
        }
        settings.useWideViewPort = true // 支持双击缩放(wap网页不支持)
        settings.loadWithOverviewMode = true
        settings.savePassword = false
        settings.javaScriptEnabled = true // 支持js功能
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.domStorageEnabled = true //H5使用了在浏览器本地存储功能就必须加这句
        binding.webview.webViewClient = object : WebViewClient() {
            // 网页加载结束
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                binding.progressbar.visibility = View.GONE
            }

            override fun onReceivedError(
                view: WebView, errorCode: Int,
                description: String, failingUrl: String
            ) {
                binding.progressbar.visibility = View.GONE
                super.onReceivedError(view, errorCode, description, failingUrl)
            }

            // 所有链接跳转会走此方法
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                d("url-----", url)
                if (i != -1) {
                    if (!TextUtils.isEmpty(url) && url.contains(".pdf")) {
    //                        PDFUtils.showPDFAuto(CommonWebActivity.this, url, Constants.PDF_Common);
                    } else if (!TextUtils.isEmpty(url) && url.contains("tel:")) {
    //                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getResources().getString(R.string.telphone)));
    //                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //                        startActivity(intent);
                    } else {
                        view.loadUrl(url) // 在跳转链接时强制在当前webview中加载
                    }
                }
                //此方法还有其他应用场景, 比如写一个超链接<a href="tel:110">联系我们</a>, 当点击该超链接时,可以在此方法中获取链接地址tel:110, 解析该地址,获取电话号码, 然后跳转到本地打电话页面, 而不是加载网页, 从而实现了webView和本地代码的交互
                return true
            } //            @Override
            //            public void onReceivedSslError(WebView view,
            //                                           SslErrorHandler handler, SslError error) {
            //                handler.cancel();
            //            }
        }
        binding.webview.webChromeClient = mWebChromeClient
    }



/*     fun onBackPressed() {
        if (null != binding.webview && binding.webview.canGoBack()) {
            binding.webview.goBack()
        } else {
            super.onBackPressed()
        }
    }*/

    

}