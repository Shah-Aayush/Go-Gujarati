package com.example.gogujarati;

public class Words {
    boolean hasAnimation = true;
    private String mDefaultTranslation,mGujaratiTranslation;
    private int mAnimationResourceId,mSpeechResourceId;

    public Words(String defaultWord, String gujaratiWord, int speechResourceId){
        mDefaultTranslation = defaultWord;
        mGujaratiTranslation = gujaratiWord;
        this.mSpeechResourceId = speechResourceId;
        hasAnimation = false;
    }

    public Words(String defaultWord, String gujaratiWord, int animationResourceId, int speechResourceId){
        mDefaultTranslation = defaultWord;
        mGujaratiTranslation = gujaratiWord;
        mAnimationResourceId = animationResourceId;
        this.mSpeechResourceId = speechResourceId;
    }

    public String getDefaultWord(){
        return mDefaultTranslation;
    }

    public String getGujaratiWord() {
        return mGujaratiTranslation;
    }

    public int getResourceId(){
        return mAnimationResourceId;
    }

    public int getSpeechResouceId(){
        return mSpeechResourceId;
    }
}
