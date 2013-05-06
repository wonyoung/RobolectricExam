package com.lge.robolectricexam;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.fest.assertions.api.ANDROID.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.internal.Implementation;
import org.robolectric.internal.Implements;
import org.robolectric.shadows.ShadowImageView;

import com.lge.robolectricexam.MainActivity;
import com.lge.robolectricexam.R;

import android.widget.ImageView;
import android.widget.TextView;

@RunWith(RobolectricTestRunner.class)
public class MyActivityTest {

	private MainActivity activity;
	private TextView textview;

	@Before
	public void createActivity() throws Exception {
		activity = new MainActivity();
		activity.onCreate(null);
		
		textview = (TextView) activity.findViewById(R.id.textView1);
	}
	
	@Test
	public void shouldHaveHappySmile() throws Exception {
		String hello = activity.getResources().getString(R.string.hello_world);
		assertThat(hello).isEqualTo("Hello hworld, nice day.");

		assertThat(hello, equalTo("Hello world, nice day.."));
	}
	
	@Test
	public void shouldHaveALogo() throws Exception {
		ImageView image = (ImageView) activity.findViewById(R.id.exam_logo); 
		ShadowImageView shadowImageview = Robolectric.shadowOf(image);
		assertThat(shadowImageview.getResourceId(), equalTo(R.drawable.exam_logo));
	}
	
	@Test
	public void testTextviewAttribute() {
		assertThat(textview)
			.isClickable();
	}
	
	@Implements(BigDecimal.class)
	public class ShadowBigDecimal {
		
		private int num = 0;

		public void __constructor__(int num) {
			this.num = num;
		}

		@Implementation
		public BigDecimal abs() {
			return BigDecimal.ZERO;
		}
	}
	
	@Test
	public void testBigDecimal() {
		BigDecimal big = new BigDecimal(22);
		assertThat(big.abs(), equalTo(BigDecimal.ZERO));
	}
}

