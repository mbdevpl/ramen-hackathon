package com.example.kazuya.jiro

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_first.view.*
import java.util.*


class FirstFragment : Fragment(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null

    private var baseCount = 0
    private var baseVegCount = 0
    private var garlicCount = 0
    private var porkCount = 0
    private var oilCount = 0
    private var eggCount = 0

    private var noodleText = ""
    private var vegText = ""
    private var garlicText = ""
    private var oilText = ""

    private var speechText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TextToSpeech init
        tts = TextToSpeech(context, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_first, null)
        // Ramen
        view.ramenButton.setOnClickListener {
            when (baseCount) {
                0 -> {
                    view.baseImage.setImageDrawable(resources.getDrawable(R.drawable.ramens))
                    baseCount++
                    noodleText = "麺少なめ"
                }
                1 -> {
                    view.baseImage.setImageDrawable(resources.getDrawable(R.drawable.ramenm))
                    baseCount++
                    noodleText = ""
                }
                2 -> {
                    view.baseImage.setImageDrawable(resources.getDrawable(R.drawable.ramenl))
                    baseCount = 0
                }
                else -> {
                    baseCount = 0
                }

            }
            speechText = noodleText + vegText + garlicText + oilText
            view.textView.text = speechText
        }

        // Vegetable
        view.vegButton.setOnClickListener {
            when (baseVegCount) {
                0 -> {
                    view.baseVegImage.setImageDrawable(resources.getDrawable(R.drawable.vegm))
                    baseVegCount++
                    vegText = "ヤサイ"
                }
                1 -> {
                    view.baseVegImage.setImageDrawable(resources.getDrawable(R.drawable.vegl))
                    baseVegCount++
                    vegText = "ヤサイ多め"
                }
                2 -> {
                    view.baseVegImage.setImageDrawable(resources.getDrawable(R.drawable.vegs))
                    baseVegCount = 0
                    vegText = "ヤサイ少なめ"
                }
                else -> {
                    baseVegCount = 0
                }
            }
            speechText = noodleText + vegText + garlicText + oilText
            view.textView.text = speechText
        }

        // Garlic
        view.gerlicButton.setOnClickListener {
            when (garlicCount) {
                0 -> {
                    view.garlicImage.visibility = View.VISIBLE
                    view.garlicImage.setImageDrawable(resources.getDrawable(R.drawable.garlics))
                    garlicCount++
                    garlicText = "ニンニク少なめ"
                }
                1 -> {
                    view.garlicImage.setImageDrawable(resources.getDrawable(R.drawable.garlicm))
                    garlicCount++
                    garlicText = "ニンニク"
                }
                2 -> {
                    view.garlicImage.setImageDrawable(resources.getDrawable(R.drawable.garlicl))
                    garlicCount++
                    garlicText = "ニンニク多め"
                }
                3 -> {
                    view.garlicImage.setImageDrawable(resources.getDrawable(R.drawable.garlics))
                    view.garlicImage.visibility = View.GONE
                    garlicCount = 0
                    garlicText = ""
                }
                else -> {
                    baseVegCount = 0
                }

            }
            speechText = noodleText + vegText + garlicText + oilText
            view.textView.text = speechText
        }

        // Pork
        view.porkButton.setOnClickListener {
            when (porkCount) {
                0 -> {
                    view.porkImage.setImageDrawable(resources.getDrawable(R.drawable.pork2))
                    porkCount++
                }
                1 -> {
                    view.porkImage.setImageDrawable(resources.getDrawable(R.drawable.pork4))
                    porkCount++
                }
                2 -> {
                    view.porkImage.setImageDrawable(resources.getDrawable(R.drawable.pork1))
                    porkCount = 0
                }
                else -> {
                    porkCount = 0
                }

            }
            speechText = noodleText + vegText + garlicText + oilText
            view.textView.text = speechText
        }

        // Fat
        view.oilButton.setOnClickListener {
            when (oilCount) {
                0 -> {
                    view.oilImage.visibility = View.VISIBLE
                    oilCount = 1
                    oilText = "アブラ"
                }
                1 -> {
                    view.oilImage.visibility = View.GONE
                    oilCount = 0
                    oilText = ""
                }
                else -> {
                    oilCount = 0
                }
            }
            speechText = noodleText + vegText + garlicText + oilText
            view.textView.text = speechText
        }

        // Egg
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
                else -> {
                    eggCount = 0
                }

            }
            speechText = noodleText + vegText + garlicText + oilText
            view.textView.text = speechText
        }

        view.callButton.setOnClickListener {
            // ex: men sukuname yasai ome abura (this is Jiro Ramen's Call)
            speechText = "$noodleText$vegText$garlicText$oilText でお願いします"
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
            Log.d("TTS", "TextToSpeech Init")

            val listener: SpeechListener? = SpeechListener()
            tts!!.setOnUtteranceProgressListener(listener)
        } else {
            Log.e("TTS", "TextToSpeech Init Failed")
        }
    }
}