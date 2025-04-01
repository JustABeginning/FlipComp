/* (C) 2025 */
package com.application;

import com.application.pkgLib.Library;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

	public void callLibrary() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int c = 0;
			do {
				System.out.println("\nChoose :");
				System.out.println("\n\t1) To Encode");
				System.out.println("\n\t2) To Decode BASE64");
				System.out.println("\n\t3) To Decode HEX");
				System.out.print("\nConfirm [1/2/3] ? ");
				c = Integer.parseInt(br.readLine());
				String str = null, data = null;
				Library libObj = null;
				switch (c) {
					case 1 :
						System.out.println("\nEnter a STRING :\n");
						str = br.readLine().trim();
						if (!str.isBlank()) {
							libObj = new Library();
							data = libObj.getEncodedStringA(str);
							System.out.println("\nENCODED STRING (BASE64) is =>\n\n" + data);
							System.out
									.println("\nRe-DECODED STRING (BASE64) is =>\n\n" + libObj.getDecodedStringA(data));
							data = libObj.getEncodedStringB(str);
							System.out.println("\nENCODED STRING (HEX) is =>\n\n" + data);
							System.out.println("\nRe-DECODED STRING (HEX) is =>\n\n" + libObj.getDecodedStringB(data));
						}
						break;
					case 2 :
						System.out.println("\nEnter the BASE64_STRING :\n");
						str = br.readLine().trim();
						if (!str.isBlank()) {
							libObj = new Library();
							data = libObj.getDecodedStringA(str);
							System.out.println("\nDECODED STRING (BASE64) is =>\n\n" + data);
							System.out
									.println("\nRe-ENCODED STRING (BASE64) is =>\n\n" + libObj.getEncodedStringA(data));
							data = libObj.getEncodedStringB(data);
							System.out.println("\nENCODED STRING (HEX) is =>\n\n" + data);
							System.out.println("\nRe-DECODED STRING (HEX) is =>\n\n" + libObj.getDecodedStringB(data));
						}
						break;
					case 3 :
						System.out.println("\nEnter the HEX_STRING :\n");
						str = br.readLine().trim();
						if (!str.isBlank()) {
							libObj = new Library();
							data = libObj.getDecodedStringB(str);
							System.out.println("\nDECODED STRING (HEX) is =>\n\n" + data);
							System.out.println("\nRe-ENCODED STRING (HEX) is =>\n\n" + libObj.getEncodedStringB(data));
							data = libObj.getEncodedStringA(data);
							System.out.println("\nENCODED STRING (BASE64) is =>\n\n" + data);
							System.out
									.println("\nRe-DECODED STRING (BASE64) is =>\n\n" + libObj.getDecodedStringA(data));
						}
						break;
					default :
						System.out.println("\nInvalid CHOICE !\n");
				}
			} while (c > 0 && c < 4);
		} catch (IOException ignored) {
		}
	}

	public static void main(String[] args) {
		new App().callLibrary();
	}
}
