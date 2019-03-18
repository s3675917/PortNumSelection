package Control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;
import mail.*;

public class app01 {
	public static String filepath;

	public static String check(String pathString, String stunum, String port1, String port2) throws Exception {
		filepath = pathString;
		int portnum = 0;
		while (true) {

			System.out.println("======" + port1 + "-" + port2 + "=======");
			port1 = inputport(port1);
			System.out.println(port1);
			port2 = inputport(port2);
			System.out.println(port2);
			stunum = inputstu(stunum);
			System.out.println(stunum);
			if (port1.equals("err") || port2.equals("err") || stunum.equals("err")) {
				return " check your input?";
			}
			int status = lookup(pathString, stunum + "," + port1 + "," + port2);
			switch (status) {
			case 3: {
				writeToFile2(stunum + "," + port1 + "," + port2);
				return " you have successfully picked a port" + Connection.sendMail(stunum + "," + port1 + "," + port2);
			}
			case 2: {
				return " you have already picked a port";
			}
			case 1: {
				return " port taken";
			}
			default:
				return " check your input";
			}

		}
	}

	public static String inputport(String port) {
		int portnum = 0;
		Scanner sc = new Scanner(System.in);
		boolean mark = true;
		// String port = "";
		// System.out.printf(n + " select a port (61000-61999)\n");
		// port = sc.nextLine();
		try {
			portnum = Integer.parseInt(port);
			if (portnum > 61999 || portnum < 61000) {
				System.out.println("error input port");
				return "err";
			} else {

			}
		} catch (NumberFormatException e) {
			return "err";
		}
		return port;
	}

	public static String inputstu(String stunum) {
		Scanner sc = new Scanner(System.in);
		// String stunum = "";

		System.out.printf("input student number\n");
		// stunum = sc.nextLine();
		if (!stunum.startsWith("s") || stunum.length() != 8)
			return "err";
		else
			return stunum;
	}

	public static void writeToFile2(String port) {
		try {
			String content = port;
			File file = new File(filepath);
			// �ļ�������ʱ�����������ļ���
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.write(";");
			bw.close();
			fw.close();
			System.out.println(port + " success");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static String readFile(File file, String charset) {
		// ����Ĭ�ϱ���
		if (charset == null) {
			charset = "UTF-8";
		}

		if (file.isFile() && file.exists()) {
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				StringBuffer sb = new StringBuffer();
				String text = null;
				while ((text = bufferedReader.readLine()) != null) {
					sb.append(text);
				}
				return sb.toString();
			} catch (Exception e) {
				System.out.println("no file");
			}
		}
		return null;
	}

	public static int lookup(String filepath, String input) {
		String[] arr = readFile(new File(filepath), "UTF-8").split(";");
		String[] arr2 = input.split(",");
		int x = 0;
		while (x < arr.length) {
			// System.out.println(arr[x]);
			if (arr[x].contains(arr2[1]) || arr[x].contains(arr2[2])) {
				System.out.println("port taken (" + arr[x] + ")");
				return 1;
			}
			if (arr[x].contains(arr2[0])) {
				System.out.println("you have already picked a port");
				return 2;
			}
			x++;
		}
		return 3;

	}
}