package com.Team4073.codes;

/*
Class for the Communication code
Creates the code from information provided

@author josephtelaak

*/


public class CommunicationCode implements datacodes.java{
    private int DataPrefix;
    private int data;
    
    private String Code;

    public CommunicationCode (int DataPrefix, int data) {
        this.DataPrefix = DataPrefix;
        this.data = data;

        Code = "" + DataPrefix + IDENTIFIER_CONSTANT + data;
    }

    /*
        Dissasembles the code into components
    */

    public Integer[] decode(int code) {
        String tmp;
        Strinf scode = code + "";

        Integer[] codes = new Integer[2];

        tmp = scode.substring(0, scode.find("9"));
        codes[1] = Integer.ParseInt(tmp);
        tmp = "";

        tmp = scode.substring(scode.find("9"), scode.length)
    }

    /*
        Returns code generated in constructor
    */

    public int GetCode() {
        return Integer.ParseInt(Code);
    }

    /*
        Bypasses constructor
        Allows the creation of a code without the use of a new object
    */

    public static int GetCode(int DataPrefix, int data) {
        String TmpCode = "" + DataPrefix + IDENTIFIER_CONSTANT + data;
        
        return Integer.ParseInt(TmpCode);
    }

    public String toString() {
        return Code;
    }

    
}