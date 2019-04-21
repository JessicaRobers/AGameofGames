package com.example.android.agameofgames;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;

/* referencing code from:
https://github.com/firebase/snippets-android/blob/db90a3562b8b6d6e53ca903961201474544ad50c/storage/app/src/main/java/com/google/firebase/referencecode/storage/FirebaseUIActivity.java
and
https://firebase.google.com/docs/storage/android/download-files
 */

@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        // register FirebaseImageLoader to handle StorageReference
        registry.append(StorageReference.class, InputStream.class, new FirebaseImageLoader.Factory());
    }
}
