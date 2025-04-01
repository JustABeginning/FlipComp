# FlipComp

A Silly Cryptographic Encoding Algorithm

## Concepts Involved

- String Reversal

- 2's Complement

  ```Java

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

  ```

- Flip Nibble (4-bits)

  ```Java

  private int flipNibble(int n) {
    return (((n & 15) << 4) | ((n >> 4) & 15));
  }

  ```
