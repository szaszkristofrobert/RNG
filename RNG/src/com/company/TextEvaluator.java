package com.company;

import java.io.*;
import java.util.Locale;

public class TextEvaluator {
    String elsobetu = "";
    int a=0, aa=0, b=0, c=0, cs=0, d=0, dz=0, dzs=0, e=0, ee=0, f=0, g=0, gy=0, h=0, i=0, ii=0, j=0, k=0, l=0, ly=0, m=0, n=0, ny=0, o=0, oo=0, ooo=0, oooo=0, p=0, q=0, r=0, s=0, sz=0, t=0, ty=0, u=0, uu=0, uuu=0, uuuu=0, v=0, w=0, x=0, y=0, z=0, zs=0;
    FileManager fm = new FileManager();

    void evaluate(String inputname, String language, String method, String outputname) {
        try {
            FileInputStream fis = fm.getFis(inputname);

            FileWriter myOtherWriter = fm.getStatWriter();

            FileWriter myWriter = fm.getWriter(outputname);
            //karakterek kiertekelese
            int oneByte;
            if (language.equals("angol")) {
                if (method.equals("felezes")) {
                    while ((oneByte = fis.read()) != -1) {
                        //System.out.write(oneByte);
                        if (oneByte > 64 && oneByte < 78)
                            myWriter.write("0");
                        if (oneByte > 77 && oneByte < 91)
                            myWriter.write("1");
                        if (oneByte > 96 && oneByte < 110)
                            myWriter.write("0");
                        if (oneByte > 109 && oneByte < 123)
                            myWriter.write("1");
                    }
                }

                if (method.equals("betueloszlas")) {/*
            a 0-t ado betuk: a, c, e, i, l, o, y, x, j, u, f, z
            osszesen 49.978%
            */
                    while ((oneByte = fis.read()) != -1) {
                        if (oneByte > 96 && oneByte < 123)
                            oneByte -= 32;

                        if (oneByte > 64 && oneByte < 91) {
                            if (oneByte == 65 ||
                                    oneByte == 67 ||
                                    oneByte == 69 ||
                                    oneByte == 73 ||
                                    oneByte == 76 ||
                                    oneByte == 79 ||
                                    oneByte == 89 ||
                                    oneByte == 88 ||
                                    oneByte == 74 ||
                                    oneByte == 85 ||
                                    oneByte == 70 ||
                                    oneByte == 90) myWriter.write("0");
                            else myWriter.write("1");
                        }
                    }

                }

                if (method.equals("hasonlitas")) {
                    while ((oneByte = fis.read()) != -1) {
                        int elso = oneByte;
                        if ((oneByte = fis.read()) != -1) {
                            if (oneByte > elso)
                                myWriter.write("0");
                            if (oneByte < elso)
                                myWriter.write("1");
                        }
                    }
                }
            }

            if (language.equals("magyar")) {
                int bit = 0;
                int[] byteBuffer = new int[3];
                byteBuffer[1] = fis.read();
                byteBuffer[2] = fis.read();
                int bufferCountdown = 2;
                while ((oneByte = fis.read()) != -1 || bufferCountdown-- > 0) {
                    byteBuffer[0] = byteBuffer[1];
                    byteBuffer[1] = byteBuffer[2];
                    byteBuffer[2] = oneByte;

                    if (bufferCountdown < 2){
                        byteBuffer[2] = -1;
                    }

                    if (byteBuffer[0] == -1)
                        continue;

                    else if (byteBuffer[0] == 'c' || byteBuffer[0] == 'C'){
                        if (byteBuffer[1] == 's'){
                            bit = magyarevaluate("cs", method);
                            byteBuffer[1] = -1;
                        }
                        else{
                            bit = magyarevaluate("c", method);
                        }
                    }
                    else if (byteBuffer[0] == 'd' || byteBuffer[0] == 'D'){
                        if (byteBuffer[1] == 'z') {
                            if (byteBuffer[2] == 's') {
                                bit = magyarevaluate("dzs", method);
                                byteBuffer[1] = -1;
                                byteBuffer[2] = -1;
                            } else {
                                bit = magyarevaluate("dz", method);
                                byteBuffer[1] = -1;
                            }
                        }
                        else{
                            bit = magyarevaluate("d", method);
                        }
                    }
                    else if (byteBuffer[0] == 'g' || byteBuffer[0] == 'G'){
                        if (byteBuffer[1] == 'y'){
                            bit = magyarevaluate("gy", method);
                            byteBuffer[1] = -1;
                        }
                        else{
                            bit = magyarevaluate("g", method);
                        }
                    }
                    else if (byteBuffer[0] == 'l' || byteBuffer[0] == 'L'){
                        if (byteBuffer[1] == 'y'){
                            bit = magyarevaluate("ly", method);
                            byteBuffer[1] = -1;
                        }
                        else{
                            bit = magyarevaluate("l", method);
                        }
                    }
                    else if (byteBuffer[0] == 'n' || byteBuffer[0] == 'N'){
                        if (byteBuffer[1] == 'y'){
                            bit = magyarevaluate("ny", method);
                            byteBuffer[1] = -1;
                        }
                        else{
                            bit = magyarevaluate("n", method);
                        }
                    }
                    else if (byteBuffer[0] == 's' || byteBuffer[0] == 'S'){
                        if (byteBuffer[1] == 'z'){
                            bit = magyarevaluate("sz", method);
                            byteBuffer[1] = -1;
                        }
                        else{
                            bit = magyarevaluate("s", method);
                        }
                    }
                    else if (byteBuffer[0] == 't' || byteBuffer[0] == 'T'){
                        if (byteBuffer[1] == 'y'){
                            bit = magyarevaluate("ty", method);
                            byteBuffer[1] = -1;
                        }
                        else{
                            bit = magyarevaluate("t", method);
                        }
                    }
                    else if (byteBuffer[0] == 'z' || byteBuffer[0] == 'Z'){
                        if (byteBuffer[1] == 's'){
                            bit = magyarevaluate("zs", method);
                            byteBuffer[1] = -1;
                        }
                        else{
                            bit = magyarevaluate("z", method);
                        }
                    }
                    else if (byteBuffer[0] == 'ã' || byteBuffer[0] == 'Ã'){
                        if (byteBuffer[1] == '\u0081' || byteBuffer[1] == 161){
                            bit = magyarevaluate("á", method);
                            byteBuffer[1] = -1;
                        }
                        else if (byteBuffer[1] == 137 || byteBuffer[1] == 169){
                            bit = magyarevaluate("é", method);
                            byteBuffer[1] = -1;
                        }
                        else if (byteBuffer[1] == 141 || byteBuffer[1] == 173){
                            bit = magyarevaluate("í", method);
                            byteBuffer[1] = -1;
                        }
                        else if (byteBuffer[1] == 147 || byteBuffer[1] == 179){
                            byteBuffer[1] = -1;
                            bit = magyarevaluate("ó", method);
                        }
                        else if (byteBuffer[1] == 150 || byteBuffer[1] == 182){
                            bit = magyarevaluate("ö", method);
                            byteBuffer[1] = -1;
                        }
                        else if (byteBuffer[1] == 154 || byteBuffer[1] == 186){
                            bit = magyarevaluate("ú", method);
                            byteBuffer[1] = -1;
                        }
                        else if (byteBuffer[1] == 156 || byteBuffer[1] == 188){
                            bit = magyarevaluate("ü", method);
                            byteBuffer[1] = -1;
                        }
                    }
                    else if (byteBuffer[0] == 'å' || byteBuffer[0] == 'Å'){
                        if (byteBuffer[1] == 144 || byteBuffer[1] == 145){
                            bit = magyarevaluate("ő", method);
                            byteBuffer[1] = -1;
                        }
                        else if (byteBuffer[1] == 176 || byteBuffer[1] == 177){
                            bit = magyarevaluate("ű", method);
                            byteBuffer[1] = -1;
                        }
                    }

                    else{
                        bit = magyarevaluate(String.valueOf((char)byteBuffer[0]).toLowerCase(), method);
                    }
                    if (bit == 0)
                        myWriter.write("0");
                    if (bit == 1)
                        myWriter.write("1");
                }
            }

            if (method.equals("setup"))writeStats(myOtherWriter);

            myWriter.close();
            myOtherWriter.close();
            System.out.flush();
            System.out.println("Text evaluated!");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    int magyarevaluate(String betu, String modszer){
        //System.out.println(betu);
        int ret = -1;
        if (modszer.equals("setup")) {
            switch (betu) {
                case "a":
                    a++;
                    break;
                case "á":
                    aa++;
                    break;
                case "b":
                    b++;
                    break;
                case "c":
                    c++;
                    break;
                case "cs":
                    cs++;
                    break;
                case "d":
                    d++;
                    break;
                case "dz":
                    dz++;
                    break;
                case "dzs":
                    dzs++;
                    break;
                case "e":
                    e++;
                    break;
                case "é":
                    ee++;
                    break;
                case "f":
                    f++;
                    break;
                case "g":
                    g++;
                    break;
                case "gy":
                    gy++;
                    break;
                case "h":
                    h++;
                    break;
                case "i":
                    i++;
                    break;
                case "í":
                    ii++;
                    break;
                case "j":
                    j++;
                    break;
                case "k":
                    k++;
                    break;
                case "l":
                    l++;
                    break;
                case "ly":
                    ly++;
                    break;
                case "m":
                    m++;
                    break;
                case "n":
                    n++;
                    break;
                case "ny":
                    ny++;
                    break;
                case "o":
                    o++;
                    break;
                case "ó":
                    oo++;
                    break;
                case "ö":
                    ooo++;
                    break;
                case "ő":
                    oooo++;
                    break;
                case "p":
                    p++;
                    break;
                case "q":
                    q++;
                    break;
                case "r":
                    r++;
                    break;
                case "s":
                    s++;
                    break;
                case "sz":
                    sz++;
                    break;
                case "t":
                    t++;
                    break;
                case "ty":
                    ty++;
                    break;
                case "u":
                    u++;
                    break;
                case "ú":
                    uu++;
                    break;
                case "ü":
                    uuu++;
                    break;
                case "ű":
                    uuuu++;
                    break;
                case "v":
                    v++;
                    break;
                case "w":
                    w++;
                    break;
                case "x":
                    x++;
                    break;
                case "y":
                    y++;
                    break;
                case "z":
                    z++;
                    break;
                case "zs":
                    zs++;
                    break;
            }
        }
        if (modszer.equals("felezes")){
            switch (betu){
                case "a", "á", "b", "c", "cs", "d", "dz", "dzs", "e", "é", "f", "g", "gy", "h", "i", "í", "j", "k", "l", "ly", "m", "n": ret = 0; break;
                case "ny", "o", "ó", "ö", "ő", "p", "q", "r", "s", "sz", "t", "ty", "u", "ú", "ü", "ű", "v", "w", "x", "y", "z", "zs": ret = 1; break;
            }
        }
        if (modszer.equals("betueloszlas")){
            switch (betu){
                case "a", "á", "b", "e", "t", "é", "k", "i", "p", "ü", "y", "zs", "dzs": ret = 0; break;
                case "í", "j", "l", "ly", "m", "n", "h", "gy", "g", "f", "dz", "d", "cs", "c", "ny", "o", "ó", "ö", "ő", "q", "r", "s", "sz", "ty", "u", "ú", "ű", "v", "w", "x", "z": ret = 1; break;
            }
        }
        if (modszer.equals("hasonlitas")){
            if (!elsobetu.equals("")){
                int elso = 0;
                switch (elsobetu){
                    case "a": elso = 1; break;
                    case "á": elso = 2; break;
                    case "b": elso = 3; break;
                    case "c": elso = 4; break;
                    case "cs": elso = 5; break;
                    case "d": elso = 6; break;
                    case "dz": elso = 7; break;
                    case "dzs": elso = 8; break;
                    case "e": elso = 9; break;
                    case "é": elso = 10; break;
                    case "f": elso = 11; break;
                    case "g": elso = 12; break;
                    case "gy": elso = 13; break;
                    case "h": elso = 14; break;
                    case "i": elso = 15; break;
                    case "í": elso = 16; break;
                    case "j": elso = 17; break;
                    case "k": elso = 18; break;
                    case "l": elso = 19; break;
                    case "ly": elso = 20; break;
                    case "m": elso = 21; break;
                    case "n": elso = 22; break;
                    case "ny": elso = 23; break;
                    case "o": elso = 24; break;
                    case "ó": elso = 25; break;
                    case "ö": elso = 26; break;
                    case "ő": elso = 27; break;
                    case "p": elso = 28; break;
                    case "q": elso = 29; break;
                    case "r": elso = 30; break;
                    case "s": elso = 31; break;
                    case "sz": elso = 32; break;
                    case "t": elso = 33; break;
                    case "ty": elso = 34; break;
                    case "u": elso = 35; break;
                    case "ú": elso = 36; break;
                    case "ü": elso = 37; break;
                    case "ű": elso = 38; break;
                    case "v": elso = 39; break;
                    case "w": elso = 40; break;
                    case "x": elso = 41; break;
                    case "y": elso = 42; break;
                    case "z": elso = 43; break;
                    case "zs": elso = 44; break;
                }
                switch (betu){
                    case "a": if (elso > 1) ret = 0; break;
                    case "á": if (elso > 2) ret = 0; if (elso < 2) ret = 1; break;
                    case "b": if (elso > 3) ret = 0; if (elso < 3) ret = 1; break;
                    case "c": if (elso > 4) ret = 0; if (elso < 4) ret = 1; break;
                    case "cs": if (elso > 5) ret = 0; if (elso < 5) ret = 1; break;
                    case "d": if (elso > 6) ret = 0; if (elso < 6) ret = 1; break;
                    case "dz": if (elso > 7) ret = 0; if (elso < 7) ret = 1; break;
                    case "dzs": if (elso > 8) ret = 0; if (elso < 8) ret = 1; break;
                    case "e": if (elso > 9) ret = 0; if (elso < 9) ret = 1; break;
                    case "é": if (elso > 10) ret = 0; if (elso < 10) ret = 1; break;
                    case "f": if (elso > 11) ret = 0; if (elso < 11) ret = 1; break;
                    case "g": if (elso > 12) ret = 0; if (elso < 12) ret = 1; break;
                    case "gy": if (elso > 13) ret = 0; if (elso < 13) ret = 1; break;
                    case "h": if (elso > 14) ret = 0; if (elso < 14) ret = 1; break;
                    case "i": if (elso > 15) ret = 0; if (elso < 15) ret = 1; break;
                    case "í": if (elso > 16) ret = 0; if (elso < 16) ret = 1; break;
                    case "j": if (elso > 17) ret = 0; if (elso < 17) ret = 1; break;
                    case "k": if (elso > 18) ret = 0; if (elso < 18) ret = 1; break;
                    case "l": if (elso > 19) ret = 0; if (elso < 19) ret = 1; break;
                    case "ly": if (elso > 20) ret = 0; if (elso < 20) ret = 1; break;
                    case "m": if (elso > 21) ret = 0; if (elso < 21) ret = 1; break;
                    case "n": if (elso > 22) ret = 0; if (elso < 22) ret = 1; break;
                    case "ny": if (elso > 23) ret = 0; if (elso < 23) ret = 1; break;
                    case "o": if (elso > 24) ret = 0; if (elso < 24) ret = 1; break;
                    case "ó": if (elso > 25) ret = 0; if (elso < 25) ret = 1; break;
                    case "ö": if (elso > 26) ret = 0; if (elso < 26) ret = 1; break;
                    case "ő": if (elso > 27) ret = 0; if (elso < 27) ret = 1; break;
                    case "p": if (elso > 28) ret = 0; if (elso < 28) ret = 1; break;
                    case "q": if (elso > 29) ret = 0; if (elso < 29) ret = 1; break;
                    case "r": if (elso > 30) ret = 0; if (elso < 30) ret = 1; break;
                    case "s": if (elso > 31) ret = 0; if (elso < 31) ret = 1; break;
                    case "sz": if (elso > 32) ret = 0; if (elso < 32) ret = 1; break;
                    case "t": if (elso > 33) ret = 0; if (elso < 33) ret = 1; break;
                    case "ty": if (elso > 34) ret = 0; if (elso < 34) ret = 1; break;
                    case "u": if (elso > 35) ret = 0; if (elso < 35) ret = 1; break;
                    case "ú": if (elso > 36) ret = 0; if (elso < 36) ret = 1; break;
                    case "ü": if (elso > 37) ret = 0; if (elso < 37) ret = 1; break;
                    case "ű": if (elso > 38) ret = 0; if (elso < 38) ret = 1; break;
                    case "v": if (elso > 39) ret = 0; if (elso < 39) ret = 1; break;
                    case "w": if (elso > 40) ret = 0; if (elso < 40) ret = 1; break;
                    case "x": if (elso > 41) ret = 0; if (elso < 41) ret = 1; break;
                    case "y": if (elso >42) ret = 0; if (elso < 42) ret = 1; break;
                    case "z": if (elso > 43) ret = 0; if (elso < 43) ret = 1; break;
                    case "zs": if (elso < 44) ret = 1; break;
                }
            }
        }


        setElsoBetu(betu);
        return ret;
    }

    void setElsoBetu(String betu){
        if (elsobetu.equals(""))
            elsobetu = betu;
        else
            elsobetu = "";
    }

    void writeStats(FileWriter myWriter) throws IOException{

        myWriter.write("a = " + a + "\n");
        myWriter.write("aa = " + aa + "\n");
        myWriter.write("b = " + b + "\n");
        myWriter.write("c = " + c + "\n");
        myWriter.write("cs = " + cs + "\n");
        myWriter.write("d = " + d + "\n");
        myWriter.write("dz = " + dz + "\n");
        myWriter.write("dzs = " + dzs + "\n");
        myWriter.write("e = " + e + "\n");
        myWriter.write("ee = " + ee + "\n");
        myWriter.write("f = " + f + "\n");
        myWriter.write("g = " + g + "\n");
        myWriter.write("gy = " + gy + "\n");
        myWriter.write("h = " + h + "\n");
        myWriter.write("i = " + i + "\n");
        myWriter.write("ii = " + ii + "\n");
        myWriter.write("j = " + j + "\n");
        myWriter.write("k = " + k + "\n");
        myWriter.write("l = " + l + "\n");
        myWriter.write("ly = " + ly + "\n");
        myWriter.write("m = " + m + "\n");
        myWriter.write("n = " + n + "\n");
        myWriter.write("ny = " + ny + "\n");
        myWriter.write("o = " + o + "\n");
        myWriter.write("oo = " + oo + "\n");
        myWriter.write("ooo = " + ooo + "\n");
        myWriter.write("oooo = " + oooo + "\n");
        myWriter.write("p = " + p + "\n");
        myWriter.write("q = " + q + "\n");
        myWriter.write("r = " + r + "\n");
        myWriter.write("s = " + s + "\n");
        myWriter.write("sz = " + sz + "\n");
        myWriter.write("t = " + t + "\n");
        myWriter.write("ty = " + ty + "\n");
        myWriter.write("u = " + u + "\n");
        myWriter.write("uu = " + uu + "\n");
        myWriter.write("uuu = " + uuu + "\n");
        myWriter.write("uuuu = " + uuuu + "\n");
        myWriter.write("v = " + v + "\n");
        myWriter.write("w = " + w + "\n");
        myWriter.write("x = " + x + "\n");
        myWriter.write("y = " + y + "\n");
        myWriter.write("z = " + z + "\n");
        myWriter.write("zs = " + zs + "\n");
        System.out.println("Stats done.");
    }
}