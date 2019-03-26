package com.edc.flutter.plugin.aliyun.log;

import com.aliyun.sls.android.sdk.ClientConfiguration;
import com.aliyun.sls.android.sdk.LOGClient;
import com.aliyun.sls.android.sdk.LogException;
import com.aliyun.sls.android.sdk.core.auth.StsTokenCredentialProvider;
import com.aliyun.sls.android.sdk.core.callback.CompletedCallback;
import com.aliyun.sls.android.sdk.model.Log;
import com.aliyun.sls.android.sdk.model.LogGroup;
import com.aliyun.sls.android.sdk.request.PostLogRequest;
import com.aliyun.sls.android.sdk.result.PostLogResult;

import java.util.List;

public class AliyunLog {

    private String mProject;
    private LOGClient mlogClient;

    public void create(InitParam param) {
        mProject = param.getProjectName();
        StsTokenCredentialProvider credentialProvider = new StsTokenCredentialProvider(
                param.getAccessKeyID(),
                param.getAccessKeySecret(),
                param.getToken() != null ? param.getToken() : "");
        ClientConfiguration conf = new ClientConfiguration();
        mlogClient = new LOGClient(param.getEndPoint(), credentialProvider, conf);
    }


    public void post(PostParam param) {
        try {
            LogGroup logGroup = new LogGroup(param.getTopic(), param.getSource());

            Log log = new Log();
            final List<KeyContent> keyContents = param.getKeyContents();
            if (keyContents != null && !keyContents.isEmpty()) {
                for (KeyContent kc : keyContents) {
                    log.PutContent(kc.getKey(), kc.getContent());
                }
            }
            logGroup.PutLog(log);
            PostLogRequest request = new PostLogRequest(mProject, param.getLogStore(), logGroup);
            if (mlogClient == null) {
                return;
            }
            mlogClient.asyncPostLog(request, new CompletedCallback<PostLogRequest, PostLogResult>() {
                @Override
                public void onSuccess(PostLogRequest request, PostLogResult result) {
                }

                @Override
                public void onFailure(PostLogRequest request, LogException exception) {
                }
            });
        } catch (Throwable e) {
            android.util.Log.e("AliyunLog", "#postAliyunLog", e);
        }
    }
}
