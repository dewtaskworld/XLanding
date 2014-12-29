package com.taskworld.xlanding.paging;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.taskworld.xlanding.R;

/**
 * Created by Johnny Dew on 12/24/2014 AD.
 */
public class ScreenSlidePageFragment extends Fragment {

    public static final String ARG_PAGE = "page";


    private int mPageNumber;

    private RelativeLayout relContent;
    private ImageView imvIcon;
    private TextView txtTitle;

    private int mColorId;
    private Drawable mIconDrawable;

    private float mSwipeOffset;


    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_page, container, false);

        // Set the title view to show the page number.
//        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
//                getString(R.string.title_template_step, mPageNumber + 1));

        relContent = (RelativeLayout) rootView.findViewById(R.id.linContent);
        relContent.setBackgroundColor(mColorId);
        imvIcon = (ImageView) rootView.findViewById(R.id.imvIcon);
        imvIcon.setImageDrawable(mIconDrawable);
        txtTitle = (TextView) rootView.findViewById(R.id.tvTitle);

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }

    public void setBackgroundColor(int color) {
        mColorId = color;
        if(isVisible()) {
            relContent.setBackgroundColor(mColorId);
        }
    }

    public void setmIconDrawable(Drawable drawable) {
        mIconDrawable = drawable;
        if(isVisible()) {
            imvIcon.setImageDrawable(mIconDrawable);
        }
    }

    private ObjectAnimator mAnimator;
    private float mOldOffset = 0;
    public void setSwipeOffset(float offset) {
        mSwipeOffset = offset;
//        ObjectAnimator yTranslate = ObjectAnimator.ofFloat(imvIcon, "translationY", offset);
//        yTranslate.setDuration(350L);
//        yTranslate.start();

//        AnimatorSet animators = new AnimatorSet();
//        animators.setDuration(350L);
//        animators.play(yTranslate);
//        animators.start();

//        ObjectAnimator.ofFloat(imvIcon, "rotation", 0f, 360f).start();
//        ObjectAnimator.ofFloat(imvIcon, "y", offset).setDuration(1).start();

//        Log.v("setSwipeOffset", "offset: "+offset);

//        float currentAlpha = imvIcon.getAlpha();
//        mAnimator = ObjectAnimator.ofFloat(imvIcon, "alpha", currentAlpha, currentAlpha-offset);
//        mAnimator.start();

        float toOffset = offset * 2;
        if(mOldOffset < toOffset) {
            //swipe to the right
            imvIcon.setTranslationY(imvIcon.getTranslationY() - toOffset);
        } else {
            //swipe to the left
            imvIcon.setTranslationY(imvIcon.getTranslationY() + toOffset);
        }
        mOldOffset = toOffset;

    }

    public void testRunAnimation() {
//        ObjectAnimator.ofFloat(imvIcon, "alpha", 0f, 1f).setDuration(5000).start();
    }
}
