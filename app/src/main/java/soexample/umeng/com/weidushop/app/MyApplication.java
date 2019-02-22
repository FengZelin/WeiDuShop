package soexample.umeng.com.weidushop.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

/**
 * date:2018/12/29
 * author:冯泽林(asus)
 * function:
 */
public class MyApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        initFresco(this);
        initContext(this);
        Fresco.initialize(this);
    }

    private void initContext(Context context) {
        mContext = context;
    }

    private void initFresco(Context context) {
        Fresco.initialize(context, ImagePipelineConfig.newBuilder(MyApplication.this)
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder(this)
                                .setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory() + "fresco"))
                                .build()
                )
                .build()
        );
    }
}
