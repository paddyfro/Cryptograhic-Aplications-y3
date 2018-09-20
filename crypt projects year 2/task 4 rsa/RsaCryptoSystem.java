package rsacryptosystem;

/**
 *
 * @author patrick McDonnell Student no: D00006968
 */
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class RsaCryptoSystem {

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws UnsupportedEncodingException {
        //limitation 125 characters long
        //longer character string means more intense processing
        //initalise various variables
        String playAgain = "y";
        BigInteger modulus;
        BigInteger publicKey = new BigInteger("65537");     // common value in practice = 2^16 + 1
        BigInteger privateKey;
        BigInteger cipherText;
        BigInteger plainText;
        int keySize = 500;
        int messageLimit = 125;
        //neded to initalise a one for formula later
        BigInteger one = new BigInteger("1");
        // create a secure random number generator
        SecureRandom random = new SecureRandom();
        Scanner kb = new Scanner(System.in);

        //use probablePrime function to generate a prime number for p and q
        BigInteger p = BigInteger.probablePrime(keySize, random);
        BigInteger q = BigInteger.probablePrime(keySize, random);
        //making sure p and q are not the same
        while (q == p) {
            q = BigInteger.probablePrime(keySize, random);
        }
        //print out of p and q
        System.out.println("p: " + p);
        System.out.println("q: " + q + "\n");

        //multplying pa dn q to find the world in which we are working
        modulus = p.multiply(q);
        //working out phiN
        BigInteger phi = p.subtract(one).multiply(q.subtract(one));
        System.out.println("modulus : " + modulus);
        System.out.println("phi: " + phi + "\n");

        //destroy p and q
        p = null;
        q = null;

        //getting e (public key) making sure it is co-prime to phi
        while (!publicKey.gcd(phi).equals(one));
        {
            publicKey = BigInteger.probablePrime(keySize, random);
        }
        //finding d (private key)this is so that d^ed = 1
        privateKey = publicKey.modInverse(phi);

        System.out.println("public key: " + publicKey);
        System.out.println("private key: " + privateKey + "\n");

        //get BigInteger value for the users inputed message
        while (playAgain.equalsIgnoreCase("y")) {
            ArrayList<BigInteger> bigIntArray = new ArrayList<>();
            bigIntArray = getMessage(messageLimit);

            //encrypt the user message
            System.out.println("- Encryption -");
            ArrayList<BigInteger> enccryptedMessagesArray = new ArrayList<>();
//            System.out.println("Encrypt: " + userMessage + "\n");
//ecnrypt each value in returned array using public key data and add it to new arraylist-> enccryptedMessagesArray
            for (BigInteger userMsg : bigIntArray) {
                cipherText = deEncrypto(userMsg, publicKey, modulus);
                System.out.println("ciphertext: " + cipherText);
                enccryptedMessagesArray.add(cipherText);
            }

            //decrypt the user message
            //takes each values in enccryptedMessagesArray and then using private key data stores this in a new array - > plainTextMessagesArray
            ArrayList<BigInteger> plainTextMessagesArray = new ArrayList<>();
            System.out.println("\n- Decryption -");
            for (BigInteger cipherMsg : enccryptedMessagesArray) {
                plainText = deEncrypto(cipherMsg, privateKey, modulus);
//            System.out.println("plaintext: " + plainText);
                plainTextMessagesArray.add(plainText);
            }

            //print out the user message taking the taking the bigInteger to a byte array then converting that to a string
            //iterates through plainTextMessagesArray to display the unecrypted message back to the user
           System.out.println("Message in plaintext: \n");
            for (BigInteger plainBigInt : plainTextMessagesArray) {
                
                System.out.println(new String(plainBigInt.toByteArray()));
            }
            System.out.println("\nplayagain? y, n");
            playAgain = kb.nextLine();
        }

        System.out.println("-----------------------");
        System.out.println("Thank you for playing!");

    }

    /**
     *
     * @param message BigInteger value of message to be encrypted/decrypted
     * @param key BigInteger value for either public or private key
     * @param modulus BigInteger value of modulus or world in which we are
     * working
     * @return BigInteger value of the message thats encrypted or decrypted
     */
    public static BigInteger deEncrypto(BigInteger message, BigInteger key, BigInteger modulus) {

        return message.modPow(key, modulus);
    }

    /**
     * Takes string message from the user, converts it to a byte array then to a
     * BigInteger object and returns an arraylist of biginteger values back
     *
     * @param messageLimit character count for limit onencrypting
     * @return BigINteger value of converted message
     */
    public static ArrayList<BigInteger> getMessage(int messageLimit) {
        //take in user message
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter message to decrypt/encrypt");
        String message = kb.nextLine();
        //check to see if message length is ok for encryption/decryption
//        while (message.length() > messageLimit) {
//            System.out.println("Message was too long, limit of 125 characters");
//            System.out.println("Enter message to decrypt/encrypt");
//            message = kb.nextLine();
//        }
        System.out.println("Message for encryption: \n" + message + "\n");

        //system to break up messages of bigger charcater values
        ArrayList<BigInteger> messageArray = new ArrayList<>();
        BigInteger msg;
        byte[] bytes;
        //if message greater than limit
        if (message.length() > messageLimit) {
            System.out.println("message greater");
            while (messageLimit < message.length()) {
//                System.out.println(message.substring(0, messageLimit));
                String msgCut = message.substring(0, messageLimit);
                message = message.substring(messageLimit);
                //convert message to a byte array
                bytes = msgCut.getBytes();
                //convert byte array to a BigInteger object
                msg = new BigInteger(bytes);
                messageArray.add(msg);
            }
            bytes = message.getBytes();
            //convert byte array to a BigInteger object
            msg = new BigInteger(bytes);
            messageArray.add(msg);

        } else {
            //convert message to a byte array
            bytes = message.getBytes();
            //convert byte array to a BigInteger object
            msg = new BigInteger(bytes);
            messageArray.add(msg);
        }
//        //convert message to a byte array
//        byte[] bytes = message.getBytes();
//        //convert byte array to a BigInteger object
//        BigInteger msg = new BigInteger(bytes);
        //return message
        return messageArray;
    }
}
