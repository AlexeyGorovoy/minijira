package minijira.web;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Scanner;


public class Capitals {

	ArrayList<Capital> list = new ArrayList<Capital>();
	
	public Capitals() {
		String s;
		try {
			BufferedReader br = new BufferedReader(new FileReader("../applications/minijira-ear/minijira-web-1.0-SNAPSHOT_war/db/capitals.txt"));
			while (br.ready()) {
				s = br.readLine();
				list.add(new Capital(s));
			}
			br.close();
		} catch (IOException e) {}
	}
	
	public String getCapital(String country) {
		for (Capital c : list)
			if (c.getCountry().equals(country)) return c.getCity();
		return null;
	}
} 
