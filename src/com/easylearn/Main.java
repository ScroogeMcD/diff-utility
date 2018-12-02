package com.easylearn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
	    String fileName1 = "C:\\Users\\chintu\\IdeaProjects\\FileDiff\\src\\com\\easylearn\\test1.txt"; //args[0];
	    String fileName2 = "C:\\Users\\chintu\\IdeaProjects\\FileDiff\\src\\com\\easylearn\\test2.txt"; //args[1];
	    //System.out.println("File 1 : " + fileName1 + ", File 2 : " + fileName2);

	    File file1 = new File(fileName1);
	    File file2 = new File(fileName2);

		BufferedReader br1 = new BufferedReader(new FileReader(file1));
		BufferedReader br2 = new BufferedReader(new FileReader(file2));

		List<Integer> file1HashcodePerLine = br1.lines().map(String::hashCode).collect(Collectors.toList());
		List<Integer> file2HashcodePerLine = br2.lines().map(String::hashCode).collect(Collectors.toList());
		br1.close();
		br2.close();

		int[] file1HashCodeArr = file1HashcodePerLine.stream().mapToInt(i -> i).toArray();
		int[] file2HashCodeArr = file2HashcodePerLine.stream().mapToInt(i -> i).toArray();

		int[] lcs = LCS.findLCS(file1HashCodeArr, file2HashCodeArr);
		int lcsIndex = 0;

		Scanner sc1 = new Scanner(file1);
		Scanner sc2 = new Scanner(file2);

		String strFromFile1 = null;
		String strFromFile2 = null;
		while(lcsIndex < lcs.length && (sc1.hasNextLine() || strFromFile1 != null) && (sc2.hasNextLine() || strFromFile2 != null)){
			if(strFromFile1 == null) strFromFile1 = sc1.nextLine();
			if(strFromFile2 == null) strFromFile2 = sc2.nextLine();
			int hashCode1 = strFromFile1.hashCode();
			int hashCode2 = strFromFile2.hashCode();

			if(hashCode1 == lcs[lcsIndex] && hashCode2 == lcs[lcsIndex]){
				System.out.println("   || " + strFromFile1);
				strFromFile1 = null;
				strFromFile2 = null;
				lcsIndex ++;
			}else if(hashCode1 != lcs[lcsIndex]){
				System.out.println("-- || " + strFromFile1);
				strFromFile1 = null;
			}else {
				System.out.println("++ || " + strFromFile2);
				strFromFile2 = null;
			}
		}

		while(sc1.hasNextLine())
			System.out.println("-- || " + sc1.nextLine());

		while(sc2.hasNextLine())
			System.out.println("++ || " + sc2.nextLine());


    }
}
