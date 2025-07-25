===========================================
          SecureTextEncoder (Java)
===========================================

🧠 Description:
SecureTextEncoder is a simple Java-based encryption tool that uses a **custom Caesar Cipher algorithm**. It takes in a combination of uppercase letters and numbers, and encodes them based on a dynamic shift value derived from a user-defined group ID.

🚀 Key Features:
- Accepts **uppercase + numeric** input only
- Validates input before encoding
- Custom Caesar cipher shift based on `groupID.hashCode()`
- Option to encode multiple times until user quits

📦 How It Works:
1. Enter your group ID
2. Enter the text you want to encode
3. The program will validate the input and then encode it using a Caesar shift
4. You can continue encoding more inputs or exit the program

🧪 Example Input:
Group ID: `T1G1`
Text: `HELLO123`
Output:QNUUX678
