package wego.com.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Base64 {
	private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', '+', '/' };

	private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,
			60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,
			-1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
			38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,
			-1, -1 };

	private Base64() {
	}

	public static String encode(byte[] data) {
		StringBuffer sb = new StringBuffer();
		int len = data.length;
		int i = 0;
		int b1, b2, b3;

		while (i < len) {
			b1 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
				sb.append("==");
				break;
			}
			b2 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[((b1 & 0x03) << 4)
						| ((b2 & 0xf0) >>> 4)]);
				sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
				sb.append("=");
				break;
			}
			b3 = data[i++] & 0xff;
			sb.append(base64EncodeChars[b1 >>> 2]);
			sb.append(base64EncodeChars[((b1 & 0x03) << 4)
					| ((b2 & 0xf0) >>> 4)]);
			sb.append(base64EncodeChars[((b2 & 0x0f) << 2)
					| ((b3 & 0xc0) >>> 6)]);
			sb.append(base64EncodeChars[b3 & 0x3f]);
		}
		return sb.toString();
	}

	public static byte[] decode(String str) {
		byte[] data = str.getBytes();
		str = null;
		int len = data.length;
		ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
		int i = 0;
		int b1, b2, b3, b4;

		while (i < len) {

			/* b1 */
			do {
				b1 = base64DecodeChars[data[i++]];
			} while (i < len && b1 == -1);
			if (b1 == -1) {
				break;
			}

			/* b2 */
			do {
				b2 = base64DecodeChars[data[i++]];
			} while (i < len && b2 == -1);
			if (b2 == -1) {
				break;
			}
			buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

			/* b3 */
			do {
				b3 = data[i++];
				if (b3 == 61) {
					return buf.toByteArray();
				}
				b3 = base64DecodeChars[b3];
			} while (i < len && b3 == -1);
			if (b3 == -1) {
				break;
			}
			buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

			/* b4 */
			do {
				b4 = data[i++];
				if (b4 == 61) {
					return buf.toByteArray();
				}
				b4 = base64DecodeChars[b4];
			} while (i < len && b4 == -1);
			if (b4 == -1) {
				break;
			}
			buf.write((int) (((b3 & 0x03) << 6) | b4));
		}
		data = null;
		return buf.toByteArray();
	}

	public static byte[] encodeToByte(byte[] data) {
		byte[] resultByte = null;
		StringBuffer sb = new StringBuffer();
		int len = data.length;
		int i = 0;
		int b1, b2, b3;

		while (i < len) {
			b1 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
				sb.append("==");
				break;
			}
			b2 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[((b1 & 0x03) << 4)
						| ((b2 & 0xf0) >>> 4)]);
				sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
				sb.append("=");
				break;
			}
			b3 = data[i++] & 0xff;
			sb.append(base64EncodeChars[b1 >>> 2]);
			sb.append(base64EncodeChars[((b1 & 0x03) << 4)
					| ((b2 & 0xf0) >>> 4)]);
			sb.append(base64EncodeChars[((b2 & 0x0f) << 2)
					| ((b3 & 0xc0) >>> 6)]);
			sb.append(base64EncodeChars[b3 & 0x3f]);
		}
		resultByte = sb.toString().getBytes();
		sb = null;
		return resultByte;
	}

	public static byte[] decode2(byte[] data) throws IOException {
		// byte[] data = Util.getBytes(str);
		byte[] tempByte = null;
		int len = data.length;
		ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
		int i = 0;
		int b1, b2, b3, b4;

		boolean jump = false;
		while (i < len) {

			jump = false;
			/* b1 */
			do {
				b1 = base64DecodeChars[data[i++]];
				if (b1 == 61) {
					// return buf.toByteArray();
					jump = true;
					break;
				}
			} while (i < len && b1 == -1);

			if (jump) {
				continue;
			}

			if (b1 == -1 || i >= len) {
				break;
			}

			/* b2 */
			do {
				b2 = base64DecodeChars[data[i++]];

				if (b1 == 61) {
					// return buf.toByteArray();
					jump = true;
					break;
				}
			} while (i < len && b2 == -1);

			if (jump) {
				continue;
			}

			if (b2 == -1 || i >= len) {
				break;
			}
			buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

			/* b3 */
			do {
				b3 = data[i++];
				if (b3 == 61) {
					// return buf.toByteArray();
					jump = true;
					break;
				}
				b3 = base64DecodeChars[b3];
			} while (i < len && b3 == -1);

			if (jump) {
				continue;
			}

			if (b3 == -1 || i >= len) {
				break;
			}
			buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

			/* b4 */
			do {
				b4 = data[i++];
				if (b4 == 61) {
					// return buf.toByteArray();
					jump = true;
					break;
				}
				b4 = base64DecodeChars[b4];
			} while (i < len && b4 == -1);

			if (jump) {
				continue;
			}

			if (b4 == -1) {
				break;
			}
			buf.write((int) (((b3 & 0x03) << 6) | b4));
		}
		data = null;
		tempByte = buf.toByteArray();
		buf.flush();
		buf.close();
		buf = null;
		return tempByte;
	}

//	public static byte[] decode4(MappedByteBuffer tempBuffer, int len)
//			throws IOException {
//		// byte[] data = Util.getBytes(str);
//		// byte[] tempByte = null;
//		// ByteArrayOutputStream buf = new ByteArrayOutputStream();
//		ByteArrayBuffer buffer = new ByteArrayBuffer(len);
//		// buffer.
//		int i = 0;
//		int b1, b2, b3, b4;
//		boolean jump = false;
//		while (i < len) {
//			jump = false;
//			/* b1 */
//			do {
//				b1 = base64DecodeChars[tempBuffer.get(i++)];
//			} while (i < len && b1 == -1);
//			if (b1 == -1) {
//				break;
//			}
//
//			/* b2 */
//			do {
//				b2 = base64DecodeChars[tempBuffer.get(i++)];
//			} while (i < len && b2 == -1);
//			if (b2 == -1) {
//				break;
//			}
//			// buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));
//			buffer.append((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));
//
//			/* b3 */
//			do {
//				b3 = tempBuffer.get(i++);
//				if (b3 == 61) {
//					jump = true;
//					// return buf.toByteArray();
//					break;
//				}
//				b3 = base64DecodeChars[b3];
//			} while (i < len && b3 == -1);
//
//			if (jump) {
//				continue;
//			}
//
//			if (b3 == -1) {
//				break;
//			}
//			// buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));
//			buffer.append((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));
//
//			/* b4 */
//			do {
//				b4 = tempBuffer.get(i++);
//				if (b4 == 61) {
//					jump = true;
//					// return buf.toByteArray();
//					break;
//				}
//				b4 = base64DecodeChars[b4];
//			} while (i < len && b4 == -1);
//
//			if (jump) {
//				continue;
//			}
//
//			if (b4 == -1) {
//				break;
//			}
//			// buf.write((int) (((b3 & 0x03) << 6) | b4));
//			buffer.append((int) (((b3 & 0x03) << 6) | b4));
//		}
//		tempBuffer.clear();
//		tempBuffer = null;
//		// tempByte = buffer.toByteArray();
//
//		// 过滤掉totalByte后面的空位
//		int m = len - 1;
//		int flag = 0;
//		for (; m >= 0; m--) {
//			if (buffer.byteAt(m) != 0) {
//				flag = m;
//				break;
//			}
//		}
//		byte[] m_byte = new byte[flag];
//		flag--;
//		for (; flag >= 0; flag--) {
//			m_byte[flag] = (byte) buffer.byteAt(flag);
//		}
//		buffer.clear();
//		buffer = null;
//		return m_byte;
//	}

}