package com.lxj.poptestbutton;


import java.util.ArrayList;

import org.xml.sax.Parser;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CusPopButton extends RelativeLayout {
	private Context mContext;
	private Button btn;
	private boolean mFlag = true;
	private ArrayList<Button> list;
	public btnOnClickListener listener;
	private ArrayList<String> strings = new ArrayList<String>();

	public CusPopButton(Context context) {
		//this.strings = strs;
		this(context,null,0);
		// TODO Auto-generated constructor stub
	}
	public CusPopButton(Context context,ArrayList<String> strs){
		
		this(context, null,0);
		
	}
	public CusPopButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		//initView();
		//setButton(this.strings);
	}

	public CusPopButton(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}
	
	
	public void setButton(ArrayList<String> strings){
		list = new ArrayList<Button>();
		 View view = LayoutInflater.from(mContext).inflate(R.layout.basis_project_button,this);
		 RelativeLayout root = (RelativeLayout)view.findViewById(R.id.root);
		final RelativeLayout rLayout = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		rLayout.setLayoutParams(lp);
		rLayout.setId(0);
		//setContentView(rLayout);
		btn = new Button(mContext);
	
		RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//Button btn1 = new Button(mContext);
		//btn.setWidth(50);
		lParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		lParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		
		lParams.setMargins(10, 10, 30, 30);
		btn.setLayoutParams(lParams);
		btn.setId(1);
		btn.setBackgroundResource(R.drawable.btn_quickoption_route);
		//btn.setPadding(50, 0, 5, 0);
		for(int i =0;i < strings.size();i++){
			lParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			lParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		Button btnChild = new Button(mContext);
		RelativeLayout.LayoutParams lpChild = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//lpChild.
		 
		//btnChild.s(30);
		//btnChild.setHeight(40);
		btnChild.setBackgroundResource(R.drawable.popbutton_shape);
		lpChild.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		lpChild.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		lpChild.setMargins(0, 0, 20, 20);
		btnChild.setLayoutParams(lpChild);
		btnChild.setId(i);
		btnChild.setWidth(30);
		//btnChild.setTextColor(0xF56A55);
		btnChild.setText(strings.get(i));
		btnChild.setTextColor(Color.parseColor("#ff0000"));
		btnChild.setVisibility(View.GONE);
		
		rLayout.addView(btnChild);
		//rLayout.addView(btn2);
		list.add(btnChild);
		}
		rLayout.addView(btn);
		root.addView(rLayout);
		
		view.invalidate();
		setListener(view,list);
	}
	public void setListener(final View view,final ArrayList<Button> alist){
		 btn.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                if(mFlag) {
	                    setAnimation(view,alist);
	                   // outBounds.setVisibility(View.VISIBLE);
	                }else{
	                    closeAnimation(view,alist);
	                   // outBounds.setVisibility(View.GONE);
	                }
	            }
	        });
		for(int i = 0;i < alist.size();i++){
	        alist.get(i).setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                listener.btn1OnClick();
	            }
	        });
	        alist.get(i).setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                listener.btn2OnClick();
	            }
	        });
	    }
	}
	
	  public void setAnimation(View view,ArrayList<Button> aList){
		 // ArrayList<ObjectAnimator> animators = new ArrayList<ObjectAnimator>();
		  Animator[] animators = new Animator[aList.size()*3+1];
		  for(int i = 0;i < aList.size();i++){
			  aList.get(i).setVisibility(View.VISIBLE);
		 
			   ObjectAnimator animator = ObjectAnimator.ofFloat(aList.get(i),"alpha",0,1f);
			  ObjectAnimator animator1 = ObjectAnimator.ofFloat(aList.get(i),"translationY",-150F-100*i);
			  ObjectAnimator animator2 = ObjectAnimator.ofFloat(aList.get(i), "rotation",0, 720);
			  animators[i*3+0] = animator;
			  animators[i*3+1] = animator1;
			  animators[i*3+2] = animator2;
		  }

		  ObjectAnimator animator3 = ObjectAnimator.ofFloat(btn, "rotation", 0,90);
		  animators[aList.size()*3] = animator3;
	       AnimatorSet set = new AnimatorSet();
	       set.setDuration(500);
	       set.setInterpolator(new BounceInterpolator());
	       
	       set.playTogether(animators);
	       set.start();
	       mFlag = false;
	   }
	    public void closeAnimation(View view,ArrayList<Button> aList){
	       // ObjectAnimator animator5 = ObjectAnimator.ofFloat(image1, "translationY", -200);
	    	 Animator[] animators = new Animator[aList.size()*3+1];
	    	 float value1 = btn.getTranslationY();
			  for(int i = 0;i < aList.size();i++){
				  aList.get(i).setVisibility(View.VISIBLE);
				  ObjectAnimator animator = ObjectAnimator.ofFloat(aList.get(i),"alpha",1f,0);
				  ObjectAnimator animator1 = ObjectAnimator.ofFloat(aList.get(i),"translationY",-200F-100*i,value1);
				  ObjectAnimator animator2 = ObjectAnimator.ofFloat(aList.get(i), "rotation",720,0);
				  animators[i*3+0] = animator;
				  animators[i*3+1] = animator1;
				  animators[i*3+2] = animator2;
			  }

			  ObjectAnimator animator3 = ObjectAnimator.ofFloat(btn, "rotation", 90,0);
			  animators[aList.size()*3] = animator3;
	        AnimatorSet set = new AnimatorSet();
	        set.setDuration(1000);
	        set.setInterpolator(new BounceInterpolator());
	        set.playTogether(animators);
	        set.start();

	        mFlag = true;
	    }
	    
	    public void setBtnOnClickListener(btnOnClickListener mListener)
	    {
	            this.listener = mListener;
	        }
	        public interface btnOnClickListener{
	            void btn1OnClick();
	            void btn2OnClick();
	        }
}
