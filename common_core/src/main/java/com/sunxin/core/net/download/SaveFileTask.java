package com.sunxin.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.sunxin.core.app.Globle;
import com.sunxin.core.net.callback.IRequest;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.core.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * @author sunxin
 * @date 2018/10/28 3:37 PM
 * @desc 异步保存文件
 */
public class SaveFileTask extends AsyncTask<Object,Void,File> {

    private final ISuccess mSuccess;
    private final IRequest mRequest;

    public SaveFileTask(ISuccess success, IRequest request) {
        mSuccess = success;
        mRequest = request;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final String name = (String) params[2];
        final ResponseBody responseBody = (ResponseBody) params[4];
        InputStream inputStream = responseBody.byteStream();

        if (TextUtils.isEmpty(downloadDir)){
            downloadDir="down_load_dir";
        }

        if (TextUtils.isEmpty(extension)){

        }

        if (name == null){
            return FileUtil.writeToDisk(inputStream,downloadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtil.writeToDisk(inputStream,downloadDir,name);
        }

    }


    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (mSuccess != null) {
            mSuccess.onSuccess(file.getPath());
        }

        if (mRequest != null) {
            mRequest.onRequestEnd();
        }

        autoInstall(file );
    }


    /**
     * 自动安装apk
     * @param file
     */
    private void autoInstall(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Globle.getApplicationContext().startActivity(install);
        }
    }

}
