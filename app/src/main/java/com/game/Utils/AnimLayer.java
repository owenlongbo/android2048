package com.game.Utils;

import com.game.Model.Card;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

public class AnimLayer extends FrameLayout {

	public AnimLayer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public AnimLayer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AnimLayer(Context context) {
		super(context);
	}

    //创建移动动画
	public void createMoveAnim(final Card from, final Card to, int fromX,
			int toX, int fromY, int toY) {

        //获取一张卡片
		final Card c = getCard(from.getNum());

        //设置左侧外边距  右侧外边距
		LayoutParams lp = new LayoutParams(Config.CARD_WIDTH, Config.CARD_WIDTH);
		lp.leftMargin = fromX * Config.CARD_WIDTH;
		lp.topMargin = fromY * Config.CARD_WIDTH;

        //应用设置
		c.setLayoutParams(lp);

        //如果卡片是0  将卡片隐藏
		if (to.getNum() <= 0) {
			to.getLabel().setVisibility(View.INVISIBLE);
		}

        //创建一个动画实体
		TranslateAnimation ta = new TranslateAnimation(0, Config.CARD_WIDTH
				* (toX - fromX), 0, Config.CARD_WIDTH * (toY - fromY));

        //设置动画播放速度
 		ta.setDuration(100);

        //设置动画监听器
		ta.setAnimationListener(new Animation.AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				to.getLabel().setVisibility(View.VISIBLE);
				recycleCard(c);
			}
		});
		c.startAnimation(ta);
	}


    //获取要拷贝的动画的信息 以用来拷贝
	private Card getCard(int num) {
		Card c;
		c = new Card(getContext());
		addView(c);
		c.setVisibility(View.VISIBLE);
		c.setNum(num);
		return c;
	}


    //回收卡片
	private void recycleCard(Card c) {

        //隐藏卡片
		c.setVisibility(View.INVISIBLE);

        //设置卡片动画为null
		c.setAnimation(null);
	}


    //新出现卡片的扩散动画
	public void createScaleTo1(Card target) {

        //扩散动画实体

//        fromX：起始X坐标上的伸缩尺寸。
//
//        toX：结束X坐标上的伸缩尺寸。
//
//        fromY：起始Y坐标上的伸缩尺寸。
//
//        toY：结束Y坐标上的伸缩尺寸。
//
//        pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
//
//        pivotXValue：X坐标的伸缩值。
//
//        pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
//
//        pivotYValue：Y坐标的伸缩值。
		ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
        //设置动画演示速度
		sa.setDuration(100);

        //卡片动画执行之后回收动画
		target.setAnimation(null);

        //然后开始执行动画
		target.getLabel().startAnimation(sa);
	}

}
