package com.example.hippo.simpledemo.widget.fresco;

import android.content.Context;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class FrescoHttpsSupport {

    public ImagePipelineConfig getConfig(Context context) {

        return OkHttpImagePipelineConfigFactory
                .newBuilder(context, UnsafeOkHttpClient.getUnsafeOkHttpClient())
                .build();
    }
}
