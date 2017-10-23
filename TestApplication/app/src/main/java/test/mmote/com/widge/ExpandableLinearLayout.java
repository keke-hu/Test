package test.mmote.com.widge;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import at.blogc.android.views.ExpandableTextView;
import test.mmote.com.activity.R;

/**
 * Created by KeKe on 2017/9/29.
 * 可折叠的LinearLayout
 */

public class ExpandableLinearLayout extends LinearLayout {


    private OnExpandListener onExpandListener;
    private TimeInterpolator expandInterpolator;
    private TimeInterpolator collapseInterpolator;

    private float maxHeight;
    private long animationDuration;
    private boolean animating;
    private boolean expanded;
    int DEFAULT_ANIMATION_DURATION = 750;
    int DEFAULT_MAX_HEIGHT = 500;

    public ExpandableLinearLayout(Context context) {
        super(context);
    }

    public ExpandableLinearLayout(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandableLinearLayout(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ExpandableLinearLayout, defStyle, 0);
        this.animationDuration = attributes.getInt(R.styleable.ExpandableLinearLayout_animation_duration, DEFAULT_ANIMATION_DURATION);
        this.maxHeight = attributes.getDimension(R.styleable.ExpandableLinearLayout_max_height, DEFAULT_MAX_HEIGHT);
        this.expanded = attributes.getBoolean(R.styleable.ExpandableLinearLayout_expand, true);
        attributes.recycle();
        this.expandInterpolator = new AccelerateDecelerateInterpolator();
        this.collapseInterpolator = new AccelerateDecelerateInterpolator();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!expanded) {
            final ViewGroup.LayoutParams layoutParams = ExpandableLinearLayout.this.getLayoutParams();
            layoutParams.height = (int) maxHeight;
            this.setLayoutParams(layoutParams);
            this.expanded = false;
        }

    }

    /**
     * Toggle the expanded state of this {@link ExpandableLinearLayout}.
     *
     * @return true if toggled, false otherwise.
     */
    public boolean toggle() {
        return this.expanded
                ? this.collapse()
                : this.expand();
    }

    /**
     * Expand this {@link ExpandableLinearLayout}.
     *
     * @return true if expanded, false otherwise.
     */
    public boolean expand() {
        if (!this.expanded && !this.animating && this.maxHeight >= 0) {
            this.animating = true;

            // notify listener
            if (this.onExpandListener != null) {
                this.onExpandListener.onExpand(this);
            }

            this.measure(
                    MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

            final int expandedHeight = this.getMeasuredHeight();

            // animate from collapsed height to expanded height
            final ValueAnimator valueAnimator = ValueAnimator.ofFloat(this.maxHeight, expandedHeight);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(final ValueAnimator animation) {
                    final ViewGroup.LayoutParams layoutParams = ExpandableLinearLayout.this.getLayoutParams();
                    layoutParams.height = (int) ((float) animation.getAnimatedValue());
                    ExpandableLinearLayout.this.setLayoutParams(layoutParams);
                }
            });

            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(final Animator animation) {
                    // if fully expanded, set height to WRAP_CONTENT, because when rotating the device
                    // the height calculated with this ValueAnimator isn't correct anymore
                    final ViewGroup.LayoutParams layoutParams = ExpandableLinearLayout.this.getLayoutParams();
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    ExpandableLinearLayout.this.setLayoutParams(layoutParams);

                    // keep track of current status
                    ExpandableLinearLayout.this.expanded = true;
                    ExpandableLinearLayout.this.animating = false;
                }
            });

            // set interpolator
            valueAnimator.setInterpolator(this.expandInterpolator);

            // start the animation
            valueAnimator
                    .setDuration(this.animationDuration)
                    .start();

            return true;
        }

        return false;
    }

    /**
     * @return true if collapsed, false otherwise.
     */
    public boolean collapse() {
        if (this.expanded && !this.animating && this.maxHeight >= 0) {
            this.animating = true;

            // notify listener
            if (this.onExpandListener != null) {
                this.onExpandListener.onCollapse(this);
            }

            // get expanded height
            final int expandedHeight = this.getMeasuredHeight();

            // animate from expanded height to collapsed height
            final ValueAnimator valueAnimator = ValueAnimator.ofFloat(expandedHeight, this.maxHeight);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(final ValueAnimator animation) {
                    final ViewGroup.LayoutParams layoutParams = ExpandableLinearLayout.this.getLayoutParams();
                    layoutParams.height = (int) ((float) animation.getAnimatedValue());
                    ExpandableLinearLayout.this.setLayoutParams(layoutParams);
                }
            });

            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(final Animator animation) {
                    // set maxLines to original value
//                    ExpandableLinearLayout.this.setMaxLines(ExpandableLinearLayout.this.maxLines);

                    // if fully collapsed, set height to WRAP_CONTENT, because when rotating the device
                    // the height calculated with this ValueAnimator isn't correct anymore
                    final ViewGroup.LayoutParams layoutParams = ExpandableLinearLayout.this.getLayoutParams();
                    layoutParams.height = (int) maxHeight;
                    ExpandableLinearLayout.this.setLayoutParams(layoutParams);

                    // keep track of current status
                    ExpandableLinearLayout.this.expanded = false;
                    ExpandableLinearLayout.this.animating = false;
                }
            });

            // set interpolator
            valueAnimator.setInterpolator(this.collapseInterpolator);

            // start the animation
            valueAnimator
                    .setDuration(this.animationDuration)
                    .start();

            return true;
        }

        return false;
    }


    /**
     * Sets the duration of the expand / collapse animation.
     *
     * @param animationDuration duration in milliseconds.
     */
    public void setAnimationDuration(final long animationDuration) {
        this.animationDuration = animationDuration;
    }

    /**
     * Sets a listener which receives updates about this {@link ExpandableTextView}.
     *
     * @param onExpandListener the listener.
     */
    public void setOnExpandListener(final OnExpandListener onExpandListener) {
        this.onExpandListener = onExpandListener;
    }

    /**
     * Returns the {@link OnExpandListener}.
     *
     * @return the listener.
     */
    public OnExpandListener getOnExpandListener() {
        return this.onExpandListener;
    }

    /**
     * Sets a {@link TimeInterpolator} for expanding and collapsing.
     *
     * @param interpolator the interpolator
     */
    public void setInterpolator(final TimeInterpolator interpolator) {
        this.expandInterpolator = interpolator;
        this.collapseInterpolator = interpolator;
    }

    /**
     * Sets a {@link TimeInterpolator} for expanding.
     *
     * @param expandInterpolator the interpolator
     */
    public void setExpandInterpolator(final TimeInterpolator expandInterpolator) {
        this.expandInterpolator = expandInterpolator;
    }

    /**
     * Returns the current {@link TimeInterpolator} for expanding.
     *
     * @return the current interpolator, null by default.
     */
    public TimeInterpolator getExpandInterpolator() {
        return this.expandInterpolator;
    }

    /**
     * Sets a {@link TimeInterpolator} for collpasing.
     *
     * @param collapseInterpolator the interpolator
     */
    public void setCollapseInterpolator(final TimeInterpolator collapseInterpolator) {
        this.collapseInterpolator = collapseInterpolator;
    }

    /**
     * Returns the current {@link TimeInterpolator} for collapsing.
     *
     * @return the current interpolator, null by default.
     */
    public TimeInterpolator getCollapseInterpolator() {
        return this.collapseInterpolator;
    }

    /**
     * Is this {@link ExpandableTextView} expanded or not?
     *
     * @return true if expanded, false if collapsed.
     */
    public boolean isExpanded() {
        return this.expanded;
    }


    public interface OnExpandListener {
        /**
         * The {@link ExpandableLinearLayout} is being expanded.
         *
         * @param view the textview
         */
        void onExpand(ExpandableLinearLayout view);

        /**
         * The {@link ExpandableLinearLayout} is being collapsed.
         *
         * @param view the textview
         */
        void onCollapse(ExpandableLinearLayout view);
    }

}
