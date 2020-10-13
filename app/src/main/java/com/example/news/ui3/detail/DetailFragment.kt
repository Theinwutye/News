package com.example.news.ui3.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.news.R
import kotlinx.android.synthetic.main.fragment_detail.*

//7
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView.settings.apply {
            loadsImagesAutomatically =true
            javaScriptEnabled=true
            domStorageEnabled=true
            supportZoom()
            displayZoomControls=true

        }
        webView.webViewClient = WebViewClient()

        //navigation argument
        //webView.loadUrl(args.url)
    }
}