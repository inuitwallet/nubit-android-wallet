/*
 * Copyright 2011-2014 the original author or authors.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.woollysammoth.nubit_android_wallet.ui;

import javax.annotation.Nonnull;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import com.woollysammoth.nubit_android_wallet.data.PaymentIntent;
import com.woollysammoth.nubit_android_wallet.R;

/**
 * @author Andreas Schildbach
 */
public final class SendCoinsActivity extends AbstractBindServiceActivity
{
	public static final String INTENT_EXTRA_PAYMENT_INTENT = "payment_intent";

	public static void start(final Context context, @Nonnull PaymentIntent paymentIntent)
	{
		final Intent intent = new Intent(context, SendCoinsActivity.class);
		intent.putExtra(INTENT_EXTRA_PAYMENT_INTENT, paymentIntent);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.send_coins_content);

		getWalletApplication().startBlockchainService(false);

		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		getSupportMenuInflater().inflate(R.menu.send_coins_activity_options, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				finish();
				return true;

			case R.id.send_coins_options_help:
				HelpDialogFragment.page(getSupportFragmentManager(), R.string.help_send_coins);
				return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
