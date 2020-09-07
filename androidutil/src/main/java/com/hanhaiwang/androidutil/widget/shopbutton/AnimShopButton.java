package com.hanhaiwang.androidutil.widget.shopbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.hanhaiwang.androidutil.R.styleable;


public class AnimShopButton extends View {
    protected static final String TAG =AnimShopButton.class.getName();
    protected static final int DEFAULT_DURATION = 350;
    protected int mLeft;
    protected int mTop;
    protected int mWidth;
    protected int mHeight;
    protected Region mAddRegion;
    protected Region mDelRegion;
    protected Path mAddPath;
    protected Path mDelPath;
    protected Paint mAddPaint;
    protected boolean isAddFillMode;
    protected int mAddEnableBgColor;
    protected int mAddEnableFgColor;
    protected int mAddDisableBgColor;
    protected int mAddDisableFgColor;
    protected Paint mDelPaint;
    protected boolean isDelFillMode;
    protected int mDelEnableBgColor;
    protected int mDelEnableFgColor;
    protected int mDelDisableBgColor;
    protected int mDelDisableFgColor;
    protected int mMaxCount;
    protected int mCount;
    protected float mRadius;
    protected float mCircleWidth;
    protected float mLineWidth;
    protected float mGapBetweenCircle;
    protected float mTextSize;
    protected Paint mTextPaint;
    protected FontMetrics mFontMetrics;
    protected ValueAnimator mAnimAdd;
    protected ValueAnimator mAniDel;
    protected float mAnimFraction;
    protected ValueAnimator mAnimExpandHint;
    protected ValueAnimator mAnimReduceHint;
    protected int mPerAnimDuration;
    protected boolean ignoreHintArea;
    protected boolean isHintMode;
    protected float mAnimExpandHintFraction;
    protected boolean isShowHintText;
    protected Paint mHintPaint;
    protected int mHintBgColor;
    protected int mHingTextSize;
    protected String mHintText;
    protected int mHintFgColor;
    protected int mHintBgRoundValue;
    protected boolean isReplenish;
    protected Paint mReplenishPaint;
    protected int mReplenishTextColor;
    protected int mReplenishTextSize;
    protected String mReplenishText;
    protected IOnAddDelListener mOnAddDelListener;

    public AnimShopButton(Context context) {
        this(context, (AttributeSet)null);
    }

    public AnimShopButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimShopButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mPerAnimDuration = 350;
        this.init(context, attrs, defStyleAttr);
    }

    public int getCount() {
        return this.mCount;
    }

    public AnimShopButton setCount(int count) {
        this.mCount = count;
        this.cancelAllAnim();
        this.initAnimSettingsByCount();
        return this;
    }

    private void cancelAllAnim() {
        if (this.mAnimAdd != null && this.mAnimAdd.isRunning()) {
            this.mAnimAdd.cancel();
        }

        if (this.mAniDel != null && this.mAniDel.isRunning()) {
            this.mAniDel.cancel();
        }

        if (this.mAnimExpandHint != null && this.mAnimExpandHint.isRunning()) {
            this.mAnimExpandHint.cancel();
        }

        if (this.mAnimReduceHint != null && this.mAnimReduceHint.isRunning()) {
            this.mAnimReduceHint.cancel();
        }

    }

    public IOnAddDelListener getOnAddDelListener() {
        return this.mOnAddDelListener;
    }

    public int getMaxCount() {
        return this.mMaxCount;
    }

    public AnimShopButton setMaxCount(int maxCount) {
        this.mMaxCount = maxCount;
        return this;
    }

    public boolean isReplenish() {
        return this.isReplenish;
    }

    public AnimShopButton setReplenish(boolean replenish) {
        this.isReplenish = replenish;
        if (this.isReplenish && null == this.mReplenishPaint) {
            this.mReplenishPaint = new Paint(1);
            this.mReplenishPaint.setTextSize((float)this.mReplenishTextSize);
            this.mReplenishPaint.setColor(this.mReplenishTextColor);
        }

        return this;
    }

    public int getReplenishTextColor() {
        return this.mReplenishTextColor;
    }

    public AnimShopButton setReplenishTextColor(int replenishTextColor) {
        this.mReplenishTextColor = replenishTextColor;
        return this;
    }

    public int getReplenishTextSize() {
        return this.mReplenishTextSize;
    }

    public AnimShopButton setReplenishTextSize(int replenishTextSize) {
        this.mReplenishTextSize = replenishTextSize;
        return this;
    }

    public String getReplenishText() {
        return this.mReplenishText;
    }

    public AnimShopButton setReplenishText(String replenishText) {
        this.mReplenishText = replenishText;
        return this;
    }

    public boolean isAddFillMode() {
        return this.isAddFillMode;
    }

    public AnimShopButton setAddFillMode(boolean addFillMode) {
        this.isAddFillMode = addFillMode;
        return this;
    }

    public int getAddEnableBgColor() {
        return this.mAddEnableBgColor;
    }

    public AnimShopButton setAddEnableBgColor(int addEnableBgColor) {
        this.mAddEnableBgColor = addEnableBgColor;
        return this;
    }

    public int getAddEnableFgColor() {
        return this.mAddEnableFgColor;
    }

    public AnimShopButton setAddEnableFgColor(int addEnableFgColor) {
        this.mAddEnableFgColor = addEnableFgColor;
        return this;
    }

    public int getAddDisableBgColor() {
        return this.mAddDisableBgColor;
    }

    public AnimShopButton setAddDisableBgColor(int addDisableBgColor) {
        this.mAddDisableBgColor = addDisableBgColor;
        return this;
    }

    public int getAddDisableFgColor() {
        return this.mAddDisableFgColor;
    }

    public AnimShopButton setAddDisableFgColor(int addDisableFgColor) {
        this.mAddDisableFgColor = addDisableFgColor;
        return this;
    }

    public boolean isDelFillMode() {
        return this.isDelFillMode;
    }

    public AnimShopButton setDelFillMode(boolean delFillMode) {
        this.isDelFillMode = delFillMode;
        return this;
    }

    public int getDelEnableBgColor() {
        return this.mDelEnableBgColor;
    }

    public AnimShopButton setDelEnableBgColor(int delEnableBgColor) {
        this.mDelEnableBgColor = delEnableBgColor;
        return this;
    }

    public int getDelEnableFgColor() {
        return this.mDelEnableFgColor;
    }

    public AnimShopButton setDelEnableFgColor(int delEnableFgColor) {
        this.mDelEnableFgColor = delEnableFgColor;
        return this;
    }

    public int getDelDisableBgColor() {
        return this.mDelDisableBgColor;
    }

    public AnimShopButton setDelDisableBgColor(int delDisableBgColor) {
        this.mDelDisableBgColor = delDisableBgColor;
        return this;
    }

    public int getDelDisableFgColor() {
        return this.mDelDisableFgColor;
    }

    public AnimShopButton setDelDisableFgColor(int delDisableFgColor) {
        this.mDelDisableFgColor = delDisableFgColor;
        return this;
    }

    public float getRadius() {
        return this.mRadius;
    }

    public AnimShopButton setRadius(float radius) {
        this.mRadius = radius;
        return this;
    }

    public float getCircleWidth() {
        return this.mCircleWidth;
    }

    public AnimShopButton setCircleWidth(float circleWidth) {
        this.mCircleWidth = circleWidth;
        return this;
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }

    public AnimShopButton setLineWidth(float lineWidth) {
        this.mLineWidth = lineWidth;
        return this;
    }

    public float getTextSize() {
        return this.mTextSize;
    }

    public AnimShopButton setTextSize(float textSize) {
        this.mTextSize = textSize;
        return this;
    }

    public float getGapBetweenCircle() {
        return this.mGapBetweenCircle;
    }

    public AnimShopButton setGapBetweenCircle(float gapBetweenCircle) {
        this.mGapBetweenCircle = gapBetweenCircle;
        return this;
    }

    public int getPerAnimDuration() {
        return this.mPerAnimDuration;
    }

    public AnimShopButton setPerAnimDuration(int perAnimDuration) {
        this.mPerAnimDuration = perAnimDuration;
        return this;
    }

    public boolean isIgnoreHintArea() {
        return this.ignoreHintArea;
    }

    public AnimShopButton setIgnoreHintArea(boolean ignoreHintArea) {
        this.ignoreHintArea = ignoreHintArea;
        return this;
    }

    public int getHintBgColor() {
        return this.mHintBgColor;
    }

    public AnimShopButton setHintBgColor(int hintBgColor) {
        this.mHintBgColor = hintBgColor;
        return this;
    }

    public int getHingTextSize() {
        return this.mHingTextSize;
    }

    public AnimShopButton setHingTextSize(int hingTextSize) {
        this.mHingTextSize = hingTextSize;
        return this;
    }

    public String getHintText() {
        return this.mHintText;
    }

    public AnimShopButton setHintText(String hintText) {
        this.mHintText = hintText;
        return this;
    }

    public int getHintFgColor() {
        return this.mHintFgColor;
    }

    public AnimShopButton setHintFgColor(int hintFgColor) {
        this.mHintFgColor = hintFgColor;
        return this;
    }

    public int getHintBgRoundValue() {
        return this.mHintBgRoundValue;
    }

    public AnimShopButton setHintBgRoundValue(int hintBgRoundValue) {
        this.mHintBgRoundValue = hintBgRoundValue;
        return this;
    }

    public AnimShopButton setOnAddDelListener(IOnAddDelListener IOnAddDelListener) {
        this.mOnAddDelListener = IOnAddDelListener;
        return this;
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.initDefaultValue(context);
        TypedArray ta = context.obtainStyledAttributes(attrs, styleable.AnimShopButton, defStyleAttr, 0);
        int indexCount = ta.getIndexCount();

        for(int i = 0; i < indexCount; ++i) {
            int index = ta.getIndex(i);
            if (index == styleable.AnimShopButton_gapBetweenCircle) {
                this.mGapBetweenCircle = ta.getDimension(index, this.mGapBetweenCircle);
            } else if (index == styleable.AnimShopButton_isAddFillMode) {
                this.isAddFillMode = ta.getBoolean(index, this.isAddFillMode);
            } else if (index == styleable.AnimShopButton_addEnableBgColor) {
                this.mAddEnableBgColor = ta.getColor(index, this.mAddEnableBgColor);
            } else if (index == styleable.AnimShopButton_addEnableFgColor) {
                this.mAddEnableFgColor = ta.getColor(index, this.mAddEnableFgColor);
            } else if (index == styleable.AnimShopButton_addDisableBgColor) {
                this.mAddDisableBgColor = ta.getColor(index, this.mAddDisableBgColor);
            } else if (index == styleable.AnimShopButton_addDisableFgColor) {
                this.mAddDisableFgColor = ta.getColor(index, this.mAddDisableFgColor);
            } else if (index == styleable.AnimShopButton_isDelFillMode) {
                this.isDelFillMode = ta.getBoolean(index, this.isDelFillMode);
            } else if (index == styleable.AnimShopButton_delEnableBgColor) {
                this.mDelEnableBgColor = ta.getColor(index, this.mDelEnableBgColor);
            } else if (index == styleable.AnimShopButton_delEnableFgColor) {
                this.mDelEnableFgColor = ta.getColor(index, this.mDelEnableFgColor);
            } else if (index == styleable.AnimShopButton_delDisableBgColor) {
                this.mDelDisableBgColor = ta.getColor(index, this.mDelDisableBgColor);
            } else if (index == styleable.AnimShopButton_delDisableFgColor) {
                this.mDelDisableFgColor = ta.getColor(index, this.mDelDisableFgColor);
            } else if (index == styleable.AnimShopButton_maxCount) {
                this.mMaxCount = ta.getInteger(index, this.mMaxCount);
            } else if (index == styleable.AnimShopButton_count) {
                this.mCount = ta.getInteger(index, this.mCount);
            } else if (index == styleable.AnimShopButton_radius) {
                this.mRadius = ta.getDimension(index, this.mRadius);
            } else if (index == styleable.AnimShopButton_circleStrokeWidth) {
                this.mCircleWidth = ta.getDimension(index, this.mCircleWidth);
            } else if (index == styleable.AnimShopButton_lineWidth) {
                this.mLineWidth = ta.getDimension(index, this.mLineWidth);
            } else if (index == styleable.AnimShopButton_numTextSize) {
                this.mTextSize = ta.getDimension(index, this.mTextSize);
            } else if (index == styleable.AnimShopButton_hintText) {
                this.mHintText = ta.getString(index);
            } else if (index == styleable.AnimShopButton_hintBgColor) {
                this.mHintBgColor = ta.getColor(index, this.mHintBgColor);
            } else if (index == styleable.AnimShopButton_hintFgColor) {
                this.mHintFgColor = ta.getColor(index, this.mHintFgColor);
            } else if (index == styleable.AnimShopButton_hingTextSize) {
                this.mHingTextSize = ta.getDimensionPixelSize(index, this.mHingTextSize);
            } else if (index == styleable.AnimShopButton_hintBgRoundValue) {
                this.mHintBgRoundValue = ta.getDimensionPixelSize(index, this.mHintBgRoundValue);
            } else if (index == styleable.AnimShopButton_ignoreHintArea) {
                this.ignoreHintArea = ta.getBoolean(index, false);
            } else if (index == styleable.AnimShopButton_perAnimDuration) {
                this.mPerAnimDuration = ta.getInteger(index, 350);
            } else if (index == styleable.AnimShopButton_replenishText) {
                this.mReplenishText = ta.getString(index);
            } else if (index == styleable.AnimShopButton_replenishTextColor) {
                this.mReplenishTextColor = ta.getColor(index, this.mReplenishTextColor);
            } else if (index == styleable.AnimShopButton_replenishTextSize) {
                this.mReplenishTextSize = ta.getDimensionPixelSize(index, this.mReplenishTextSize);
            }
        }

        ta.recycle();
        this.mAddRegion = new Region();
        this.mDelRegion = new Region();
        this.mAddPath = new Path();
        this.mDelPath = new Path();
        this.mAddPaint = new Paint(1);
        if (this.isAddFillMode) {
            this.mAddPaint.setStyle(Style.FILL);
        } else {
            this.mAddPaint.setStyle(Style.STROKE);
        }

        this.mDelPaint = new Paint(1);
        if (this.isDelFillMode) {
            this.mDelPaint.setStyle(Style.FILL);
        } else {
            this.mDelPaint.setStyle(Style.STROKE);
        }

        this.mHintPaint = new Paint(1);
        this.mHintPaint.setStyle(Style.FILL);
        this.mHintPaint.setTextSize((float)this.mHingTextSize);
        this.mTextPaint = new Paint(1);
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mFontMetrics = this.mTextPaint.getFontMetrics();
        this.mAnimAdd = ValueAnimator.ofFloat(new float[]{1.0F, 0.0F});
        this.mAnimAdd.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                AnimShopButton.this.mAnimFraction = (Float)animation.getAnimatedValue();
                AnimShopButton.this.invalidate();
            }
        });
        this.mAnimAdd.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
            }
        });
        this.mAnimAdd.setDuration((long)this.mPerAnimDuration);
        this.mAnimReduceHint = ValueAnimator.ofFloat(new float[]{0.0F, 1.0F});
        this.mAnimReduceHint.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                AnimShopButton.this.mAnimExpandHintFraction = (Float)animation.getAnimatedValue();
                AnimShopButton.this.invalidate();
            }
        });
        this.mAnimReduceHint.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                if (AnimShopButton.this.mCount >= 1) {
                    AnimShopButton.this.isHintMode = false;
                }

                if (AnimShopButton.this.mCount >= 1) {
                    Log.d(AnimShopButton.TAG, "现在还是》=1 开始收缩动画");
                    if (AnimShopButton.this.mAnimAdd != null && !AnimShopButton.this.mAnimAdd.isRunning()) {
                        AnimShopButton.this.mAnimAdd.start();
                    }
                }

            }

            public void onAnimationStart(Animator animation) {
                if (AnimShopButton.this.mCount == 1) {
                    AnimShopButton.this.isShowHintText = false;
                }

            }
        });
        this.mAnimReduceHint.setDuration(this.ignoreHintArea ? 0L : (long)this.mPerAnimDuration);
        this.mAniDel = ValueAnimator.ofFloat(new float[]{0.0F, 1.0F});
        this.mAniDel.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                AnimShopButton.this.mAnimFraction = (Float)animation.getAnimatedValue();
                AnimShopButton.this.invalidate();
            }
        });
        this.mAniDel.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                if (AnimShopButton.this.mCount == 0) {
                    Log.d(AnimShopButton.TAG, "现在还是0onAnimationEnd() called with: animation = [" + animation + "]");
                    if (AnimShopButton.this.mAnimExpandHint != null && !AnimShopButton.this.mAnimExpandHint.isRunning()) {
                        AnimShopButton.this.mAnimExpandHint.start();
                    }
                }

            }
        });
        this.mAniDel.setDuration((long)this.mPerAnimDuration);
        this.mAnimExpandHint = ValueAnimator.ofFloat(new float[]{1.0F, 0.0F});
        this.mAnimExpandHint.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                AnimShopButton.this.mAnimExpandHintFraction = (Float)animation.getAnimatedValue();
                AnimShopButton.this.invalidate();
            }
        });
        this.mAnimExpandHint.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                if (AnimShopButton.this.mCount == 0) {
                    AnimShopButton.this.isShowHintText = true;
                }

            }

            public void onAnimationStart(Animator animation) {
                if (AnimShopButton.this.mCount == 0) {
                    AnimShopButton.this.isHintMode = true;
                }

            }
        });
        this.mAnimExpandHint.setDuration(this.ignoreHintArea ? 0L : (long)this.mPerAnimDuration);
    }

    private void initDefaultValue(Context context) {
        this.mGapBetweenCircle = TypedValue.applyDimension(1, 34.0F, context.getResources().getDisplayMetrics());
        this.isAddFillMode = true;
        //可用时的背景颜色
        this.mAddEnableBgColor = -9125;
        //可用时的前景颜色
        this.mAddEnableFgColor = -16777216;
        //不可用时的背景颜色
        this.mAddDisableBgColor = -6842473;
        //不可用时的前景颜色
        this.mAddDisableFgColor = -16777216;
        this.isDelFillMode = false;
        //删除时可用的背景颜色
        this.mDelEnableBgColor = -6842473;
        //删除时可用的前景颜色
        this.mDelEnableFgColor = -6842473;
        //册除时不可用的背景颜色
        this.mDelDisableBgColor = -6842473;
        //册除时不可用的前景颜色
        this.mDelDisableFgColor = -6842473;
        this.mRadius = TypedValue.applyDimension(1, 12.5F, this.getResources().getDisplayMetrics());
        this.mCircleWidth = TypedValue.applyDimension(1, 1.0F, this.getResources().getDisplayMetrics());
        this.mLineWidth = TypedValue.applyDimension(1, 2.0F, this.getResources().getDisplayMetrics());
        this.mTextSize = TypedValue.applyDimension(2, 14.5F, this.getResources().getDisplayMetrics());
        this.mHintText = "加入购物车";
        this.mHintBgColor = this.mAddEnableBgColor;
        this.mHintFgColor = this.mAddEnableFgColor;
        this.mHingTextSize = (int) TypedValue.applyDimension(2, 12.0F, context.getResources().getDisplayMetrics());
        this.mHintBgRoundValue = (int) TypedValue.applyDimension(1, 5.0F, context.getResources().getDisplayMetrics());
        this.mReplenishText = "补货中";
        this.mReplenishTextColor = -840389;
        this.mReplenishTextSize = this.mHingTextSize;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int computeSize;
        switch(wMode) {
            case -2147483648:
                computeSize = (int)((float)this.getPaddingLeft() + this.mRadius * 2.0F + this.mGapBetweenCircle + this.mRadius * 2.0F + (float)this.getPaddingRight() + this.mCircleWidth * 2.0F);
                wSize = computeSize < wSize ? computeSize : wSize;
                break;
            case 0:
                computeSize = (int)((float)this.getPaddingLeft() + this.mRadius * 2.0F + this.mGapBetweenCircle + this.mRadius * 2.0F + (float)this.getPaddingRight() + this.mCircleWidth * 2.0F);
                wSize = computeSize;
            case 1073741824:
        }

        switch(hMode) {
            case -2147483648:
                computeSize = (int)((float)this.getPaddingTop() + this.mRadius * 2.0F + (float)this.getPaddingBottom() + this.mCircleWidth * 2.0F);
                hSize = computeSize < hSize ? computeSize : hSize;
                break;
            case 0:
                computeSize = (int)((float)this.getPaddingTop() + this.mRadius * 2.0F + (float)this.getPaddingBottom() + this.mCircleWidth * 2.0F);
                hSize = computeSize;
            case 1073741824:
        }

        this.setMeasuredDimension(wSize, hSize);
        this.cancelAllAnim();
        this.initAnimSettingsByCount();
    }

    private void initAnimSettingsByCount() {
        if (this.mCount == 0) {
            this.mAnimFraction = 1.0F;
        } else {
            this.mAnimFraction = 0.0F;
        }

        if (this.mCount == 0) {
            this.isHintMode = true;
            this.isShowHintText = true;
            this.mAnimExpandHintFraction = 0.0F;
        } else {
            this.isHintMode = false;
            this.isShowHintText = false;
            this.mAnimExpandHintFraction = 1.0F;
        }

    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mLeft = (int)((float)this.getPaddingLeft() + this.mCircleWidth);
        this.mTop = (int)((float)this.getPaddingTop() + this.mCircleWidth);
        this.mWidth = w;
        this.mHeight = h;
    }

    protected void onDraw(Canvas canvas) {
        int baseX;
        if (this.isReplenish) {
            //int baseX = (int)((float)(this.mWidth / 2) - this.mReplenishPaint.measureText(this.mReplenishText) / 2.0F);
            baseX = (int)((float)(this.mHeight / 2) - (this.mReplenishPaint.descent() + this.mReplenishPaint.ascent()) / 2.0F);
            canvas.drawText(this.mReplenishText, (float)baseX, (float)baseX, this.mReplenishPaint);
        } else {
            if (!this.ignoreHintArea && this.isHintMode) {
                this.mHintPaint.setColor(this.mHintBgColor);
                RectF rectF = new RectF((float)this.mLeft + ((float)this.mWidth - this.mRadius * 2.0F) * this.mAnimExpandHintFraction, (float)this.mTop, (float)this.mWidth - this.mCircleWidth, (float)this.mHeight - this.mCircleWidth);
                canvas.drawRoundRect(rectF, (float)this.mHintBgRoundValue, (float)this.mHintBgRoundValue, this.mHintPaint);
                if (this.isShowHintText) {
                    this.mHintPaint.setColor(this.mHintFgColor);
                    baseX = (int)((float)(this.mWidth / 2) - this.mHintPaint.measureText(this.mHintText) / 2.0F);
                    int baseY = (int)((float)(this.mHeight / 2) - (this.mHintPaint.descent() + this.mHintPaint.ascent()) / 2.0F);
                    canvas.drawText(this.mHintText, (float)baseX, (float)baseY, this.mHintPaint);
                }
            } else {
                float animOffsetMax = this.mRadius * 2.0F + this.mGapBetweenCircle;
                int animAlphaMax = 255;
                int animRotateMax = 360;
                if (this.mCount > 0) {
                    this.mDelPaint.setColor(this.mDelEnableBgColor);
                } else {
                    this.mDelPaint.setColor(this.mDelDisableBgColor);
                }

                this.mDelPaint.setAlpha((int)((float)animAlphaMax * (1.0F - this.mAnimFraction)));
                this.mDelPaint.setStrokeWidth(this.mCircleWidth);
                this.mDelPath.reset();
                this.mDelPath.addCircle(animOffsetMax * this.mAnimFraction + (float)this.mLeft + this.mRadius, (float)this.mTop + this.mRadius, this.mRadius, Direction.CW);
                this.mDelRegion.setPath(this.mDelPath, new Region(this.mLeft, this.mTop, this.mWidth - this.getPaddingRight(), this.mHeight - this.getPaddingBottom()));
                canvas.drawPath(this.mDelPath, this.mDelPaint);
                if (this.mCount > 0) {
                    this.mDelPaint.setColor(this.mDelEnableFgColor);
                } else {
                    this.mDelPaint.setColor(this.mDelDisableFgColor);
                }

                this.mDelPaint.setStrokeWidth(this.mLineWidth);
                canvas.save();
                canvas.translate(animOffsetMax * this.mAnimFraction + (float)this.mLeft + this.mRadius, (float)this.mTop + this.mRadius);
                canvas.rotate((float)((int)((float)animRotateMax * (1.0F - this.mAnimFraction))));
                canvas.drawLine(-this.mRadius / 2.0F, 0.0F, this.mRadius / 2.0F, 0.0F, this.mDelPaint);
                canvas.restore();
                canvas.save();
                canvas.translate(this.mAnimFraction * (this.mGapBetweenCircle / 2.0F - this.mTextPaint.measureText(this.mCount + "") / 2.0F + this.mRadius), 0.0F);
                canvas.rotate(360.0F * this.mAnimFraction, this.mGapBetweenCircle / 2.0F + (float)this.mLeft + this.mRadius * 2.0F, (float)this.mTop + this.mRadius);
                this.mTextPaint.setAlpha((int)(255.0F * (1.0F - this.mAnimFraction)));
                canvas.drawText(this.mCount + "", this.mGapBetweenCircle / 2.0F - this.mTextPaint.measureText(this.mCount + "") / 2.0F + (float)this.mLeft + this.mRadius * 2.0F, (float)this.mTop + this.mRadius - (this.mFontMetrics.top + this.mFontMetrics.bottom) / 2.0F, this.mTextPaint);
                canvas.restore();
                if (this.mCount < this.mMaxCount) {
                    this.mAddPaint.setColor(this.mAddEnableBgColor);
                } else {
                    this.mAddPaint.setColor(this.mAddDisableBgColor);
                }

                this.mAddPaint.setStrokeWidth(this.mCircleWidth);
                float left = (float)this.mLeft + this.mRadius * 2.0F + this.mGapBetweenCircle;
                this.mAddPath.reset();
                this.mAddPath.addCircle(left + this.mRadius, (float)this.mTop + this.mRadius, this.mRadius, Direction.CW);
                this.mAddRegion.setPath(this.mAddPath, new Region(this.mLeft, this.mTop, this.mWidth - this.getPaddingRight(), this.mHeight - this.getPaddingBottom()));
                canvas.drawPath(this.mAddPath, this.mAddPaint);
                if (this.mCount < this.mMaxCount) {
                    this.mAddPaint.setColor(this.mAddEnableFgColor);
                } else {
                    this.mAddPaint.setColor(this.mAddDisableFgColor);
                }

                this.mAddPaint.setStrokeWidth(this.mLineWidth);
                canvas.drawLine(left + this.mRadius / 2.0F, (float)this.mTop + this.mRadius, left + this.mRadius / 2.0F + this.mRadius, (float)this.mTop + this.mRadius, this.mAddPaint);
                canvas.drawLine(left + this.mRadius, (float)this.mTop + this.mRadius / 2.0F, left + this.mRadius, (float)this.mTop + this.mRadius / 2.0F + this.mRadius, this.mAddPaint);
            }

        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch(action) {
            case 0:
                if (!this.isReplenish) {
                    if (this.isHintMode) {
                        this.onAddClick();
                        return true;
                    } else if (this.mAddRegion.contains((int)event.getX(), (int)event.getY())) {
                        this.onAddClick();
                        return true;
                    } else if (this.mDelRegion.contains((int)event.getX(), (int)event.getY())) {
                        this.onDelClick();
                        return true;
                    }
                }
            case 1:
            case 2:
            case 3:
            default:
                return super.onTouchEvent(event);
        }
    }

    protected void onDelClick() {
        if (this.mCount > 0) {
            --this.mCount;
            this.onCountDelSuccess();
            if (null != this.mOnAddDelListener) {
                this.mOnAddDelListener.onDelSuccess(this.mCount);
            }
        } else if (null != this.mOnAddDelListener) {
            this.mOnAddDelListener.onDelFaild(this.mCount, IOnAddDelListener.FailType.COUNT_MIN);
        }

    }

    protected void onAddClick() {
        if (this.mCount < this.mMaxCount) {
            ++this.mCount;
            this.onCountAddSuccess();
            if (null != this.mOnAddDelListener) {
                this.mOnAddDelListener.onAddSuccess(this.mCount);
            }
        } else if (null != this.mOnAddDelListener) {
            this.mOnAddDelListener.onAddFailed(this.mCount, IOnAddDelListener.FailType.COUNT_MAX);
        }

    }

    public void onCountAddSuccess() {
        if (this.mCount == 1) {
            this.cancelAllAnim();
            this.mAnimReduceHint.start();
        } else {
            this.mAnimFraction = 0.0F;
            this.invalidate();
        }

    }

    public void onCountDelSuccess() {
        if (this.mCount == 0) {
            this.cancelAllAnim();
            this.mAniDel.start();
        } else {
            this.mAnimFraction = 0.0F;
            this.invalidate();
        }

    }
}