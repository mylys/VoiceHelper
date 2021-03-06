package com.huimin.iflylib.listener;

import android.os.Bundle;

import com.iflytek.cloud.SpeechError;

/**
 * Created by kermitye
 * Date: 2018/5/23 14:59
 * Desc: 语音听写回调
 */
public interface IRecognizerListener {
    void onVolumeChanged(int volume);

    void onResult(String result);

    void onError(String msg);
}
