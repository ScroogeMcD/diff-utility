package com.easylearn;

public class LCS {

    public static int[] findLCS(int[] seq1, int[] seq2){
        int[][] m = new int[seq1.length+1][seq2.length+1];

        // generating the LCS matrix
        for(int i=0; i<= seq1.length; i++){
            for(int j=0;j<= seq2.length; j++){
                if(i==0 || j==0){
                    m[i][j] = 0;
                    continue;
                }
                if(seq1[i-1] == seq2[j-1])
                    m[i][j] = m[i-1][j-1] + 1;
                else
                    m[i][j] = Math.max(m[i][j-1],m[i-1][j]);
            }
        }

        int lcsLength = m[seq1.length][seq2.length];
        //System.out.println("The length of the LCS is : " + m[seq1.length][seq2.length]);

        // getting the actual LCS array
        int[] lcs = new int[lcsLength];
        int k = lcsLength-1;

        int i = seq1.length;
        int j = seq2.length;
        while(k >= 0){
            if(seq1[i-1] == seq2[j-1]){
                lcs[k] = seq1[i-1];
                i--;
                j--;
                k--;
            }
            else{
                //move towards the max of the two
                if(m[i-1][j] > m[i][j-1])
                    i--;
                else
                    j--;
            }
        }

        return lcs;
    }

}
