package com.example.itemselector;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class DisItemSelector extends FrameLayout {

    private ConstraintLayout clItemSelector;
    private AppCompatImageButton btnMinus;
    private AppCompatImageButton btnAdd;
    private AppCompatTextView txtTitle;
    private AppCompatButton btnValue;
    private View viewLine;

    public DisItemSelector(Context context) {
        super(context);
        init(null, 0);
    }

    public DisItemSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DisItemSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        btnAdd.setOnClickListener(null);
        btnMinus.setOnClickListener(null);
        btnValue.setOnClickListener(null);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        View view = inflate(getContext(), R.layout.dis_item_selector, this);
        clItemSelector = view.findViewById(R.id.cl_item_selector);
        txtTitle = view.findViewById(R.id.txt_title);
        btnValue = view.findViewById(R.id.btn_value);
        viewLine = view.findViewById(R.id.view_line);
        btnAdd = view.findViewById(R.id.btn_add);
        btnMinus = view.findViewById(R.id.btn_minus);

        TypedArray styledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.DisItemSelector);
        setupLayout(styledAttributes);
        setupDrawable(styledAttributes);
        setupTitle(styledAttributes);
        setupValue(styledAttributes);
        styledAttributes.recycle();
    }

    private void setupLayout(TypedArray styledAttributes) {
        setBackgroundResource(styledAttributes.getResourceId(R.styleable.DisItemSelector_disBackgroundColor, R.color.disItemSelectorBackground));
        setHeight(styledAttributes.getDimension(R.styleable.DisItemSelector_disFixHeight, getResources().getDimension(R.dimen.defaultHeightSize)));
        setWidth(styledAttributes.getDimension(R.styleable.DisItemSelector_disFixWidth, getResources().getDimension(R.dimen.defaultWidthSize)));
    }

    private void setupDrawable(TypedArray styledAttributes) {
        setAddDrawable(styledAttributes.getResourceId(R.styleable.DisItemSelector_disAddSrc, R.drawable.ic_add_gray));
        setMinusDrawable(styledAttributes.getResourceId(R.styleable.DisItemSelector_disMinusSrc, R.drawable.ic_minus_gray));
        setAddTintColor(styledAttributes.getColor(R.styleable.DisItemSelector_disAddTintColor, getResources().getColor(R.color.disAddTintColor)));
        setMinusTintColor(styledAttributes.getColor(R.styleable.DisItemSelector_disMinusTintColor, getResources().getColor(R.color.disMinusTintColor)));
    }

    private void setupTitle(TypedArray styledAttributes) {
        setTitleText(styledAttributes.getString(R.styleable.DisItemSelector_disTitle));
        setTitleTextColor(styledAttributes.getColor(R.styleable.DisItemSelector_disTitleTextColor, getResources().getColor(R.color.disTitleTextColor)));
        setTitleTextSize(styledAttributes.getDimension(R.styleable.DisItemSelector_disTitleTextSize, getResources().getDimension(R.dimen.disTitleTextSize)));
    }

    private void setupValue(TypedArray styledAttributes) {
        setValueTextColor(styledAttributes.getColor(R.styleable.DisItemSelector_disValueTextColor, getResources().getColor(R.color.disValueTextColor)));
        setValueTextSize(styledAttributes.getDimension(R.styleable.DisItemSelector_disValueTextSize, getResources().getDimension(R.dimen.disValueTextSize)));
        setUnderlineColor(styledAttributes.getColor(R.styleable.DisItemSelector_disUnderlineColor, getResources().getColor(R.color.disUnderlineColor)));
        setUnderlineHeight(styledAttributes.getDimension(R.styleable.DisItemSelector_disUnderlineHeight, getResources().getDimension(R.dimen.disUnderlineHeightSize)));
    }


    public void setBackgroundResource(int res) {
        clItemSelector.setBackgroundResource(res);
    }

    public void setHeight(float height) {
        clItemSelector.getLayoutParams().height = (int) height;
        clItemSelector.requestLayout();
    }

    public void setWidth(float width) {
        btnValue.getLayoutParams().width = (int) width;
        btnValue.requestLayout();
    }

    public void setTitleText(String text) {
        txtTitle.setText(text);
    }

    public void setTitleTextColor(int textColor) {
        txtTitle.setTextColor(textColor);
    }

    public void setTitleTextSize(float textSize) {
        textSize = textSize / getResources().getDisplayMetrics().density;
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    public void setAddDrawable(int addDrawable) {
        btnAdd.setImageResource(addDrawable);
    }

    public void setMinusDrawable(int minusDrawable) {
        btnMinus.setImageResource(minusDrawable);
    }

    public void setAddTintColor(int addTintColor) {
        btnAdd.setColorFilter(addTintColor);
    }

    public void setMinusTintColor(int minusTintColor) {
        btnMinus.setColorFilter(minusTintColor);
    }

    public void setValueTextSize(float valueTextSize) {
        valueTextSize = valueTextSize / getResources().getDisplayMetrics().density;
        btnValue.setTextSize(TypedValue.COMPLEX_UNIT_DIP, valueTextSize);
    }

    public void setValueTextColor(int valueTextColor) {
        btnValue.setTextColor(valueTextColor);
    }

    public void setUnderlineHeight(float underlineHeight) {
        underlineHeight = underlineHeight / getResources().getDisplayMetrics().density;
        viewLine.getLayoutParams().height = (int) underlineHeight;
        viewLine.requestLayout();
    }

    public void setUnderlineColor(int underlineColor) {
        viewLine.setBackgroundColor(underlineColor);
    }


    public void setOnAddClickListener(OnClickListener onAddClickListener) {
        btnAdd.setOnClickListener(onAddClickListener);
    }

    public void setOnMinusClickListener(OnClickListener onAddClickListener) {
        btnMinus.setOnClickListener(onAddClickListener);
    }

    public void setOnValueClickListener(OnClickListener onAddClickListener) {
        btnValue.setOnClickListener(onAddClickListener);
    }

    public int getValue() {
        if (btnValue.getText() != null && !String.valueOf(btnValue.getText()).isEmpty()) {
            return Integer.parseInt(String.valueOf(btnValue.getText()).trim());
        } else {
            return 0;
        }
    }

    public void setValue(int value) {
        btnValue.setText(String.valueOf(value));
    }

    public void setValue(String value) {
        btnValue.setText(value);
    }


}