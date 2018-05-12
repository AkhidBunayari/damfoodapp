package online.damfood.damfood.intro;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import online.damfood.damfood.R;

import static online.damfood.damfood.intro.SlideFragment.BundleKey.TITLE_INTRO;
import static online.damfood.damfood.intro.SlideFragment.BundleKey.DESC_INTRO;
import static online.damfood.damfood.intro.SlideFragment.BundleKey.LOGO_INTRO;
import static online.damfood.damfood.intro.SlideFragment.BundleKey.BG_INTRO;


public class SlideFragment extends Fragment {

    private View rootView;
    private ImageView ivLogoSlider, ivBackground;
    private TextView tvTitle, tvDescription;

    public static SlideFragment newInstance(@StringRes int titleId, @StringRes int descId, @DrawableRes int logoId, @ColorRes int bgId) {

        Bundle args = new Bundle();
        args.putInt(TITLE_INTRO, titleId);
        args.putInt(DESC_INTRO, descId);
        args.putInt(LOGO_INTRO, logoId);
        args.putInt(BG_INTRO, bgId);
        SlideFragment fragment = new SlideFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_slide, container, false);
        tvTitle = rootView.findViewById(R.id.tv_title);
        tvDescription = rootView.findViewById(R.id.tv_description);
        ivLogoSlider = rootView.findViewById(R.id.iv_logoSlider);
        ivBackground = rootView.findViewById(R.id.iv_bgSlider);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (null != bundle) {

            int titleId = bundle.getInt(TITLE_INTRO);
            int descId = bundle.getInt(DESC_INTRO);
            int logoId = bundle.getInt(LOGO_INTRO);
            int bgId = bundle.getInt(BG_INTRO);

            tvTitle.setText(titleId);
            tvDescription.setText(descId);
            ivLogoSlider.setImageResource(logoId);
            ivBackground.setImageResource(bgId);

        }
    }

    public final class BundleKey {
        private BundleKey() {
        }

        public static final String TITLE_INTRO = "SlideFragment.TITLE_INTRO";
        public static final String DESC_INTRO = "SlideFragment.DESC_INTRO";
        public static final String LOGO_INTRO = "SlideFragment.LOGO_INTRO";
        public static final String BG_INTRO = "SlideFragment.BG_INTRO";
    }
}