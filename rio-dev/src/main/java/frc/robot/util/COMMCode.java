package frc.robot.util;

/*
Class for the Communication code
Creates the code from information provided

@author josephtelaak

*/

import frc.robot.util.Codelist;

public class COMMCode implements Codelist {
    private int DataPrefix;
    private int data;

    private String Code;

    public COMMCode(int DataPrefix, int data) {
        this.DataPrefix = DataPrefix;
        this.data = data;

        Code = "" + DataPrefix + IDENTIFIER_CONSTANT + data;
    }

    /*
        Dissasembles the code into components
    */

    public Integer[] decode(int code) {
        String tmp;
        String scode = code + "";

        Integer[] codes = new Integer[2];

        tmp = scode.substring(0, scode.indexOf("9"));
        codes[1] = Integer.parseInt(tmp);
        tmp = "";

        tmp = scode.substring(scode.indexOf("9"), scode.length());
        codes[2] = Integer.parseInt(tmp);

        return codes;
    }

    /*
     * Returns code generated in constructor
     */

    public int GetCode() {
        return Integer.parseInt(Code);
    }

    /*
     * Bypasses constructor Allows the creation of a code without the use of a new
     * object
     */

    public static int GetCode(int DataPrefix, int data) {
        String TmpCode = "" + DataPrefix + IDENTIFIER_CONSTANT + data;

        return Integer.parseInt(TmpCode);
    }

    public String toString() {
        return Code;
    }

    
}