package com.huimin.iflylib;

import android.content.Context;

import com.huimin.iflylib.listener.IRecognizerListener;
import com.huimin.iflylib.listener.ISpeakLisstener;
import com.huimin.iflylib.listener.SpeakListener;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.thirdparty.T;

/**
 * Created by kermitye
 * Date: 2018/5/23 14:50
 * Desc: 讯飞语音管理类
 */
public class IflySpeechManager {

    private IflySpeechManager() {

    }

    private static class SingletonHolder {
        public static final IflySpeechManager INSTANCE = new IflySpeechManager();
    }

    public static IflySpeechManager getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public void init(Context context) {
        SpeechUtility.createUtility(context, "appid=5aeff60f");
    }

    public void initStart(Context context) {
        RecognizerHelper.getInstance().init(context);
        TtsHelper.getInstance().init(context);
    }

    //=====================语音听写=================================

    public void setRecognizerListener(IRecognizerListener listener) {
        RecognizerHelper.getInstance().setIRecognizerListener(listener);
    }


    public void startReco() {
        RecognizerHelper.getInstance().startRecognizer();
    }

    public void stopReco() {
        RecognizerHelper.getInstance().stopRecognizer();
    }

    public void destoryReco() {
        RecognizerHelper.getInstance().destory();
    }


    //======================语音合成=======================================

    public void setSpeakListener(final ISpeakLisstener listener) {
        TtsHelper.getInstance().setSpeakListener(new SpeakListener() {
            @Override
            public void onSpeakBegin(String text) {
                listener.onSpeakBegin(text);
            }

            @Override
            public void onSpeakOver(String msg) {
                listener.onSpeakOver(msg);
            }

            @Override
            public void onInterrupted() {
                listener.onInterrupted();
            }
        });
    }

    public void startSpeak(String text, final ISpeakLisstener listener) {
        TtsHelper.getInstance().startSpeak(text, new SpeakListener() {
            @Override
            public void onSpeakBegin(String text) {
                listener.onSpeakBegin(text);
            }

            @Override
            public void onSpeakOver(String msg) {
                listener.onSpeakOver(msg);
            }

            @Override
            public void onInterrupted() {
                listener.onInterrupted();
            }
        });
    }

    public void startSpeak(String text) {
        TtsHelper.getInstance().startSpeak(text);
    }

    public void destorySpeak() {
        TtsHelper.getInstance().destory();
    }



    public void release() {
        destoryReco();
        destorySpeak();
    }



}
