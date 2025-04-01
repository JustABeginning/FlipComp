/* (C) 2025 */
package com.application.pkgLib;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Library {

	/* Encoding Functions */
	private String toHexA(char[] src) {
		final String hex_chars = "0123456789ABCDEF";
		int lsrc = src.length;
		StringBuilder ret_str = new StringBuilder();
		for (int i = 0; i < lsrc; i++) {
			ret_str.append(hex_chars.charAt((src[i] >> 4) & 15));
			ret_str.append(hex_chars.charAt(src[i] & 15));
		}
		return ret_str.toString();
	}

	private String toHexB(char[] src) {
		final String hex_chars = "0123456789ABCDEF";
		int lsrc = src.length;
		StringBuilder ret_str = new StringBuilder();
		for (int i = 0; i < lsrc; i++) {
			ret_str.append(hex_chars.charAt(src[i] & 15));
			ret_str.append(hex_chars.charAt((src[i] >> 4) & 15));
		}
		return ret_str.toString();
	}

	private int flipNibble(int n) {
		return (((n & 15) << 4) | ((n >> 4) & 15));
	}

	private char[] flipComp(String st) {
		char[] src = st.toCharArray();
		int l = st.length(), n = l / 2, x = l - 1;
		int b = 0x80;
		for (int i = 0; i < n; i++) {
			char c = (char) flipNibble((~src[i] + 1) ^ b);
			src[i] = (char) flipNibble((~src[x] + 1) ^ b);
			src[x] = c;
			x--;
		}
		return src;
	}

	/* Decoding Functions */
	private int getHexVal(byte c) {
		int ret_val;
		if (c >= '0' && c <= '9')
			ret_val = c - 48;
		else if (c >= 'A' && c <= 'F')
			ret_val = c - 55;
		else
			ret_val = c;
		return ret_val;
	}

	private byte[] fromHexA(byte[] hex_bytes) {
		int len = hex_bytes.length;
		if (len % 2 == 0) {
			byte[] retBytes = new byte[len / 2];
			for (int i = 0, j = 0; i < len; i += 2, j++) {
				int b = getHexVal(hex_bytes[i]);
				b = b << 4 | getHexVal(hex_bytes[i + 1]);
				retBytes[j] = (byte) b;
			}
			return retBytes;
		}
		return null;
	}

	private byte[] fromHexB(byte[] hex_bytes) {
		int len = hex_bytes.length;
		if (len % 2 == 0) {
			byte[] retBytes = new byte[len / 2];
			for (int i = 0, j = 0; i < len; i += 2, j++) {
				int b = getHexVal(hex_bytes[i + 1]);
				b = b << 4 | getHexVal(hex_bytes[i]);
				retBytes[j] = (byte) b;
			}
			return retBytes;
		}
		return null;
	}

	public String flipIComp(byte[] arr) {
		if (arr != null) {
			int n = arr.length / 2, x = arr.length - 1, c = 0x80;
			for (int i = 0; i < n; i++) {
				byte b = (byte) (~(flipNibble(arr[i]) ^ c) + 1);
				arr[i] = (byte) (~(flipNibble(arr[x]) ^ c) + 1);
				arr[x] = b;
				x--;
			}
			return new String(arr);
		}
		return null;
	}

	/* Public Functions */
	public String getEncodedStringA(String st) {
		byte[] hex_bytes = toHexA(flipComp(st)).getBytes(StandardCharsets.UTF_8);
		return Base64.getEncoder().encodeToString(hex_bytes);
	}

	public String getDecodedStringA(String data) {
		byte[] hex_decoded = fromHexA(Base64.getDecoder().decode(data));
		return flipIComp(hex_decoded);
	}

	public String getEncodedStringB(String st) {
		char[] revComp_chars = flipComp(st);
		byte[] revComp_bytes = new byte[revComp_chars.length];
		for (int i = 0; i < revComp_bytes.length; i++)
			revComp_bytes[i] = (byte) revComp_chars[i];
		String base64_encoded = Base64.getEncoder().encodeToString(revComp_bytes);
		return toHexB(base64_encoded.toCharArray());
	}

	public String getDecodedStringB(String data) {
		byte[] hex_decoded = fromHexB(data.getBytes(StandardCharsets.UTF_8));
		byte[] base64_decoded = Base64.getDecoder().decode(hex_decoded);
		return flipIComp(base64_decoded);
	}

}
