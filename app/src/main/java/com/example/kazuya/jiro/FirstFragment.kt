package com.example.kazuya.jiro

import android.opengl.Visibility
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import java.util.*


class FirstFragment : Fragment(), TextToSpeech.OnInitListener {
    private var tts : TextToSpeech? = null

    private var baseCount = 0
    private var baseVegCount = 0
    private var gerlicCount = 0
    private var porkCount = 0
    private var oilCount = 0
    private var eggCount = 0

    private var nudleText = ""
    private var vegText = ""
    private var gerlicText = ""
    private var oilText = ""

    private var speechText  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TextToSpeech初期化
        tts = TextToSpeech(context, this)
//        speech_ja.setOnClickListener(this)
//        speech_en.setOnClickListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_first, null)
        // ラーメン
        view.ramenButton.setOnClickListener {
            when (baseCount) {
                0 -> {
                    view.baseImage.setImageDrawable(resources.getDrawable(R.drawable.ramens))
                    baseCount ++
                    nudleText = "麺少なめ"
                }
                1 -> {
                    view.baseImage.setImageDrawable(resources.getDrawable(R.drawable.ramenm))
                    baseCount ++
                    nudleText = ""
                }
                2 -> {
                    view.baseImage.setImageDrawable(resources.getDrawable(R.drawable.ramenl))
                    baseCount = 0
                }
                else -> {baseCount = 0}

            }
            speechText = nudleText + vegText + gerlicText + oilText
            view.textView.text = speechText
        }

        // ヤサイ
        view.vegButton.setOnClickListener {
            when (baseVegCount) {
                0 -> {
                    view.baseVegImage.setImageDrawable(resources.getDrawable(R.drawable.vegm))
                    baseVegCount ++
                    vegText = "やさい"
                }
                1 -> {
                    view.baseVegImage.setImageDrawable(resources.getDrawable(R.drawable.vegl))
                    baseVegCount ++
                    vegText = "やさいおおめ"
                }
                2 -> {
                    view.baseVegImage.setImageDrawable(resources.getDrawable(R.drawable.vegs))
                    baseVegCount = 0
                    vegText = "やさいすくなめ"
                }
                else -> {baseVegCount = 0}
            }
            speechText = nudleText + vegText + gerlicText + oilText
            view.textView.text = speechText
        }

        // にんにく
        view.gerlicButton.setOnClickListener {
            when (gerlicCount) {
                0 -> {
                    view.garlicImage.visibility = View.VISIBLE
                    view.garlicImage.setImageDrawable(resources.getDrawable(R.drawable.garlics))
                    gerlicCount ++
                    gerlicText = "にんにくすくなめ"
                }
                1 -> {
                    view.garlicImage.setImageDrawable(resources.getDrawable(R.drawable.garlicm))
                    gerlicCount ++
                    gerlicText = "にんにく"
                }
                2 -> {
                    view.garlicImage.setImageDrawable(resources.getDrawable(R.drawable.garlicl))
                    gerlicCount ++
                    gerlicText = "にんにくおおめ"
                }
                3 -> {
                    view.garlicImage.setImageDrawable(resources.getDrawable(R.drawable.garlics))
                    view.garlicImage.visibility = View.GONE
                    gerlicCount = 0
                    gerlicText = ""
                }
                else -> {baseVegCount = 0}

            }
            speechText = nudleText + vegText + gerlicText + oilText
            view.textView.text = speechText
        }

        // ぶた
        view.porkButton.setOnClickListener {
            when (porkCount) {
                0 -> {
                    view.porkImage.setImageDrawable(resources.getDrawable(R.drawable.pork2))
                    porkCount ++
                }
                1 -> {
                    view.porkImage.setImageDrawable(resources.getDrawable(R.drawable.pork4))
                    porkCount ++
                }
                2 -> {
                    view.porkImage.setImageDrawable(resources.getDrawable(R.drawable.pork1))
                    porkCount = 0
                }
                else -> {porkCount = 0}

            }
            speechText = nudleText + vegText + gerlicText + oilText
            view.textView.text = speechText
        }

        // あぶら
        view.oilButton.setOnClickListener {
            when (oilCount) {
                0 -> {
                    view.oilImage.visibility = View.VISIBLE
                    oilCount = 1
                    oilText = "あぶら"
                }
                1 -> {
                    view.oilImage.visibility = View.GONE
                    oilCount = 0
                    oilText = ""
                }
                else -> {oilCount = 0}
            }
            speechText = nudleText + vegText + gerlicText + oilText
            view.textView.text = speechText
        }

        // たまご
        view.eggButton.setOnClickListener {
            when (eggCount) {
                0 -> {
                    view.eggImage.visibility = View.VISIBLE
                    eggCount = 1
                }
                1 -> {
                    view.eggImage.visibility = View.GONE
                    eggCount = 0
                }
                else -> {eggCount = 0}

            }
            speechText = nudleText + vegText + gerlicText + oilText
            view.textView.text = speechText
        }

        view.callButton.setOnClickListener {
            // 例: 麺少なめヤサイにんにくあぶら！
            speechText = nudleText + vegText + gerlicText + oilText
            speakText(speechText)
        }

        return view
    }

    private fun speakText(text: String) {
        tts!!.language = Locale.JAPANESE
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            Log.d("TTS", "TextToSpeechが初期化されました。")

            // 音声再生のイベントリスナを登録
            val listener : SpeechListener? = SpeechListener()
            tts!!.setOnUtteranceProgressListener(listener)
        } else {
            Log.e("TTS", "TextToSpeechの初期化に失敗しました。")
        }
    }
}