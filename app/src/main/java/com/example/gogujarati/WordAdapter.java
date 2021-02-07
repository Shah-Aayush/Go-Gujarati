package com.example.gogujarati;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.Lottie;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Words> {
    private int color_of_text_views;
    private MediaPlayer speakSound;

    private AudioManager mAudioManager;
    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                speakSound.pause();
                speakSound.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                speakSound.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                if(speakSound!=null){
                    speakSound.release();
                    speakSound = null;
                    mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
                }
            }
        }
    };

    public WordAdapter(Activity context, ArrayList<Words> wordsArrayList, int color_of_text_views) {
        super(context, 0, wordsArrayList);
        this.color_of_text_views = color_of_text_views;
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {       //checks if the existing view is being reused, other wise inflate the view
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //setting text_views_colors according to activity color.
        RelativeLayout text_views = (RelativeLayout) listItemView.findViewById(R.id.listing_text_views);
        text_views.setBackgroundResource(color_of_text_views);

        //setting text views of default text and translated text
        Words currentWords = getItem(position);
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWords.getDefaultWord());
        TextView gujaratiTextView = (TextView) listItemView.findViewById(R.id.gujarati_text_view);
        gujaratiTextView.setText(currentWords.getGujaratiWord());

        //playing animation on touch
        LottieAnimationView animationView = (LottieAnimationView) listItemView.findViewById(R.id.animation_view);

        if (currentWords.hasAnimation) {
            animationView.setAnimation(currentWords.getResourceId());
            animationView.playAnimation();
            animationView.setRepeatCount(0);

            listItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animationView.playAnimation();
                }
            });
            animationView.setVisibility(View.VISIBLE);
        } else {       //this is for phrases activity.
            animationView.setVisibility(View.GONE);
        }

        //playing speech animation on touch
        LottieAnimationView playButton = (LottieAnimationView) listItemView.findViewById(R.id.play_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.playAnimation();

                //release media-player resources for assigning new resource. [if previous resource is still playing and user start other mediaPlayer resource then we have to stop the older one.]
                if(speakSound!=null){
                    speakSound.release();
                    speakSound = null;
                    mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
                }

                //-------
                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    speakSound = MediaPlayer.create(v.getContext(), currentWords.getSpeechResouceId());

                    // Start the audio file
                    speakSound.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    speakSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {

                            //Pausing the animation as audio ends
                            playButton.pauseAnimation();
                            playButton.setProgress(0);

                            //releasing media player for better performance and space.
                            if(speakSound!=null){
                                speakSound.release();
                                speakSound = null;
                                mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
                            }
                        }
                    });
                }
                //-------


                //original code below [without use of audiomanager]
//                speakSound = MediaPlayer.create(v.getContext(), currentWords.getSpeechResouceId()); //include this when called in onClickListener
//                speakSound.start();
                playButton.playAnimation();

                //button animation starts when audio is playing and stops when audio-playback is finished.
//                speakSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//
//                        //Pausing the animation as audio ends
//                        playButton.pauseAnimation();
//                        playButton.setProgress(0);
//
//                        //releasing media player for better performance and space.
//                        if(speakSound!=null){
//                            speakSound.release();
//                            speakSound = null;
//                            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
//                        }
//                    }
//                });
            }
        });

        return listItemView;
    }


}
