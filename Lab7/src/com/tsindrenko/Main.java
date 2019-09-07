package com.tsindrenko;

import java.util.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

        final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
        static String algorithm = "MD5";
        final static int N = 10000;
        final static int M = 1000;
        final static int LENGTH = 5;

        private static int generateHash(String input){
            int result = 0;
            int mod = 31;
            for(int i = 0; i<input.length(); i++){
                result+=input.charAt(i)%mod;
            }
            return result;
        }

        private static int generateHashDouble(String input){
            int result = 0;
            int mod = 37;
            for(int i = 0; i<input.length(); i++){
                if(i!=0)
                    result+=input.charAt(i)%mod+input.charAt(input.length()-i)%mod;
                else
                    result+=input.charAt(i)%mod;
            }
            return result;
        }

        private static String generateString(int length){
            StringBuilder result = new StringBuilder();
            for(int i = 0; i<length; i++){
                result.append(alphabet.charAt((int)(Math.random()*alphabet.length())));
            }
            return result.toString();
        }

        private static int collisionCountMod(List<Integer> list){
            int currentElement = -1;
            int collisions = 0;
            for(int i = 0; i<list.size(); i++){
                if(currentElement<list.get(i)){
                    currentElement = list.get(i);
                }
                else
                    collisions++;
            }
            return collisions;
        }

        private static int collisionCountMD5(List<String> list){
            String currentElement = "";
            int collisions = 0;
            for(int i = 0; i<list.size(); i++){
                if(!currentElement.equals(list.get(i))){
                    currentElement = list.get(i);
                }
                else
                    collisions++;
            }
            return collisions;
        }

        public static String md5Custom(String st) {
            MessageDigest messageDigest = null;
            byte[] digest = new byte[0];

            try {
                messageDigest = MessageDigest.getInstance(algorithm);
                messageDigest.reset();
                messageDigest.update(st.getBytes());
                digest = messageDigest.digest();
            } catch (NoSuchAlgorithmException e) {
                algorithm = "MD5";
            }

            BigInteger bigInt = new BigInteger(1, digest);
            String md5Hex = bigInt.toString(16);

            while( md5Hex.length() < 32 ){
                md5Hex = "0" + md5Hex;
            }

            return md5Hex;
        }

        public static void main(String[] args) {
            String current = "hello";
            long mod = 0;
            long MD5 = 0;
            long modExt = 0;
            Map<String, Integer> hashCodesMod = new HashMap<>();
            Map<String, String> hashCodesMD5 = new HashMap<>();
            Map<String, Integer> hashCodesModChar = new HashMap<>();
            for (int j = 0; j<10000; j++) {
                for (int i = 0; i < N; i++) {
                    current = generateString(LENGTH);
                    hashCodesMod.put(current, generateHash(current));
                    hashCodesMD5.put(current, md5Custom(current));
                    hashCodesModChar.put(current, generateHashDouble(current));
                }
                List<Integer> values = new ArrayList<>(hashCodesMod.values());
                List<String> valuesMD5 = new ArrayList<>(hashCodesMD5.values());
                List<Integer> valuesModDouble = new ArrayList<>(hashCodesModChar.values());

                List<Integer> collisionsMod = new ArrayList<>();
                List<String> collisionsMD5 = new ArrayList<>();
                List<Integer> collisionsModExt = new ArrayList<>();


                for (int i = 0; i < M; i++) {
                    collisionsMod.add(values.get(i));
                    collisionsMD5.add(valuesMD5.get(i));
                    collisionsModExt.add(valuesModDouble.get(i));
                }
                Collections.sort(collisionsMod);
                Collections.sort(collisionsMD5);
                Collections.sort(collisionsModExt);
                mod += collisionCountMod(collisionsMod);
                MD5 += collisionCountMD5(collisionsMD5);
                modExt += collisionCountMod(collisionsModExt);

                hashCodesMod.clear();
                hashCodesMD5.clear();
                hashCodesModChar.clear();
                values.clear();
                valuesMD5.clear();
                valuesModDouble.clear();
                collisionsMod.clear();
                collisionsMD5.clear();
                collisionsModExt.clear();
            }
            System.out.println(mod/10000);
            System.out.println(MD5/10000);
            System.out.println(modExt/10000);
        }
}

