/*
 * Copyright 2013-2014 the original author or authors.
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

package com.woollysammoth.nubit_android_wallet.util;

import java.util.UUID;

import javax.annotation.Nonnull;

/**
 * @author Andreas Schildbach
 */
public class Bluetooth
{
	public static final UUID BLUETOOTH_UUID_PAYMENT_REQUESTS = UUID.fromString("3357A7BB-762D-464A-8D9A-DCA592D57D59");
	public static final UUID BLUETOOTH_UUID_PAYMENT_PROTOCOL = UUID.fromString("3357A7BB-762D-464A-8D9A-DCA592D57D5A");
	public static final UUID BLUETOOTH_UUID_CLASSIC = UUID.fromString("3357A7BB-762D-464A-8D9A-DCA592D57D5B");
	public static final String MAC_URI_PARAM = "bt";

	public static String compressMac(@Nonnull final String mac)
	{
		return mac.replaceAll(":", "");
	}

	public static String decompressMac(@Nonnull final String compressedMac)
	{
		final StringBuilder mac = new StringBuilder();
		for (int i = 0; i < compressedMac.length(); i += 2)
			mac.append(compressedMac.substring(i, i + 2)).append(':');
		mac.setLength(mac.length() - 1);

		return mac.toString();
	}

	public static boolean isBluetoothUrl(final String url)
	{
		return url != null && GenericUtils.startsWithIgnoreCase(url, "bt:");
	}

	public static String getBluetoothMac(final String url)
	{
		if (!isBluetoothUrl(url))
			throw new IllegalArgumentException(url);

		final int queryIndex = url.indexOf('/');
		if (queryIndex != -1)
			return url.substring(3, queryIndex);
		else
			return url.substring(3);
	}

	public static String getBluetoothQuery(final String url)
	{
		if (!isBluetoothUrl(url))
			throw new IllegalArgumentException(url);

		final int queryIndex = url.indexOf('/');
		if (queryIndex != -1)
			return url.substring(queryIndex);
		else
			return "/";
	}
}
