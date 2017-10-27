package com.thebay.thebay1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;

import com.thebay.thebay1.R;

import java.util.regex.Pattern;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

/**
 * Created by kyoungae on 2017-08-25.
 */

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {
    private TypedArray typedArray;

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }


    private void initView(Context context, AttributeSet attrs) {
        typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomEditText);

        setTypeArray();
    }

    private void setTypeArray() {

//        int hintTextColor = typedArray.getInt(R.styleable.CustomAnimationEditText_hint_color,-1);
//        switch (hintTextColor) {
//            case 0:
//                mEditLayout.setHintTextAppearance(R.style.white_hint_style);
//                break;
//            case 1:
//                mEditLayout.setHintTextAppearance(R.style.gray_hint_style);
//                break;
//        }
        //기본값 힌트 화이트
//        mEditText.setHintTextColor(hintColor);
//        int lineColor = typedArray.getInteger(R.styleable.CustomAnimationEditText_line_color,-1);
//        ColorStateList colorStateList = new ColorStateList(new int[][]{},new int[]{R.color.gray});
//        mEditText.setBackgroundColor(lineColor);

        int characterNumber = typedArray.getInt(R.styleable.CustomEditText_character_limit, -1);
        switch (characterNumber) {
            case 0:
                setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                setFilters(new InputFilter[]{englishAndNumbersFilter});
                break;
            case 1:
                setFilters(new InputFilter[]{numbersFilter});
                setInputType(TYPE_CLASS_NUMBER);
                break;

            case 2:
                setFilters(new InputFilter[]{hangulFilter});
                break;
            case 3:
                setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case 4:
                setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                setFilters(new InputFilter[]{englishFilter});
                break;
        }
    }

    //영어랑 숫자만 허용
    protected InputFilter englishAndNumbersFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };

    //영어만 허용
    public InputFilter englishFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[a-zA-Z]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };

    //숫자만 허용
    public InputFilter numbersFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[0-9]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };

    // 한글만 허용
    public InputFilter hangulFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[ㄱ-가-힣]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };
//
//    private String getChangeLanguage() {
//        return mLanguageValue;
//    }
//
//    private void setChangeLanguage(boolean isKorean) {
//        if (isKorean) {
//            changeKorean();
//        } else {
//            changeChinese();
//        }
//    }
//
//    public String getName() {
//        return typedArray.getString(R.styleable.CustomEditText_key);
//    }
//
//    private void setTypeArray(TypedArray typedArray) {
//
////        hint = typedArray.getString(R.styleable.Cust);
////        editLayout.setHint(hint);
//
//        int characterNumber = typedArray.getInt(R.styleable.CustomEditText_character_limit, -1);
//        switch (characterNumber) {
//            case 0:
//                setFilters(new InputFilter[]{englishAndNumbersFilter});
//
//                break;
//            case 1:
//                setFilters(new InputFilter[]{numbersFilter});
//                break;
//
//            case 2:
//                setFilters(new InputFilter[]{koreanFilter});
//                break;
//        }
//
//        String languageValue = mPref.getString("language", "");
//        if (!TextUtils.isEmpty(languageValue)) {
//            if (languageValue.equals("한국어")) {
//                changeKorean();
//
//            } else {
//                changeChinese();
//            }
//        }
//
//        typedArray.recycle();
//    }
//
//    //영어랑 숫자만 허용
//    protected InputFilter englishAndNumbersFilter = new InputFilter() {
//        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//            Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
//            if (!ps.matcher(source).matches()) {
//                return "";
//            }
//            return null;
//        }
//    };
//
//    //숫자만 허용
//    public InputFilter numbersFilter = new InputFilter() {
//        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//            Pattern ps = Pattern.compile("^[0-9]+$");
//            if (!ps.matcher(source).matches()) {
//                return "";
//            }
//            return null;
//        }
//    };
//
//    // 한글만 허용
//    public InputFilter koreanFilter = new InputFilter() {
//        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//            Pattern ps = Pattern.compile("^[ㄱ-가-힣]+$");
//            if (!ps.matcher(source).matches()) {
//                return "";
//            }
//            return null;
//        }
//    };
//
//
//    public void isNullCheck() {
//
//        if (TextUtils.isEmpty(getText().toString())) {  //빈 값일때
//            editLayout.setError(typedArray.getString(R.styleable.CustomEditText_character_limit) + "을(를) 입력해주세요.");
//        }
//    }
//
//    public void changeKorean() {
//
//        String koreanText = typedArray.getString(R.styleable.CustomEditText_language_hangul);
//        if(!TextUtils.isEmpty(koreanText)) {
//            mLanguageValue = koreanText;
//            setHint(koreanText);
//            Log.d("setTypeArray: ", mLanguageValue);
//        }
//    }
//
//    public void changeChinese() {
//        String chineseText = typedArray.getString(R.styleable.CustomEditText_language_chinese);
//        if(!TextUtils.isEmpty(chineseText)) {
//            mLanguageValue = chineseText;
//            setHint(chineseText);
//            Log.d("setTypeArray: ", mLanguageValue);
//        }
////        String chineseText = typedArray.getString(R.styleable.CustomEditText_language_chinese);
////        if (!TextUtils.isEmpty(chineseText)) {
////            mLanguageValue = chineseText;
////            setHint(chineseText);
////        }
//    }
//
//    @Override
//    public void onFocusChange(View v, boolean hasFocus) {
//        if (hasFocus) {
//            editLayout.setError(null);
//            editLayout.setErrorEnabled(false);
//        }
//    }
}
