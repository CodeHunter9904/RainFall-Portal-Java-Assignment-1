
import java.util.*;
import java.io.*;

public class rainfall_portal {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		String month[] = new String[12];
		month[0] = "January";
		month[1] = "February";
		month[2] = "March";
		month[3] = "April";
		month[4] = "May";
		month[5] = "June";
		month[6] = "July";
		month[7] = "August";
		month[8] = "September";
		month[9] = "October";
		month[10] = "November";
		month[11] = "December";

		int n;
		boolean flag = true;
		do {
			System.out.println("1. Add Data");
			System.out.println("2. Show Data");
			System.out.println("3. Exit");
			System.out.println("Enter Your Choice::");
			n = sc.nextInt();
			switch (n) {
			case 1: {
				int total = 0;
				String name = new String();
				String s = new String("");
				String date = new String();
				try {
					Scanner sc1 = new Scanner(System.in);
					File f = new File("Rainfall Data.txt");
					FileWriter fw = new FileWriter(f.getAbsoluteFile(), true);
					BufferedWriter bw = new BufferedWriter(fw);

					System.out.println("Enter the Name of Distrinct : ");
					name = sc1.nextLine();
					System.out.print("Enter The Year : ");
					date = sc1.nextLine();

					s = s + name + "#" + date + "#";

					for (int i = 0; i < 12; i++) {
						try {
							Scanner sc2 = new Scanner(System.in);
							System.out.print("Enter the Rainfall in Month of " + month[i] + " : ");
							float r = sc2.nextFloat();
							total += r;
							s = s + r + "#";
						} catch (InputMismatchException e) {
							System.out.println("You can Enter only Float value !!!");
							i--;
						}
					}
					s = s + total;
					bw.write(s);
					bw.newLine();
					bw.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

				break;

			}
			case 2: {

				String name_l = new String();
				String year = new String();
				String max_year = new String();
				String min_year = new String();

				System.out.println("\nName\t\tMaxRain MaxYear Month MinRain MinYear Average Rain");

				float f[] = new float[12], min = 99999, max = -1, max1 = -1, avg = 0, temp;

				int count = 0, index = 0;

				try {
					File file = new File("Rainfall Data.txt");
					FileReader fw = new FileReader(file.getAbsoluteFile());
					BufferedReader br = new BufferedReader(fw);

					String currentLine = br.readLine();
					String str[] = currentLine.split("#");
					name_l = str[0];

					while (currentLine != null) {

						str = currentLine.split("#");
						if (str[0].equals(name_l)) {
							for (int i = 0; i < 12; i++) {
								f[i] = Float.valueOf(str[i + 2]);
							}
							temp = Float.valueOf(str[14]);

							if (max < temp) {
								max = temp;
								max_year = str[1];
								max1 = -1;
								for (int j = 0; j < 12; j++) {
									if (max1 < f[j]) {
										max1 = f[j];
										index = j;
									}
								}

							}
							if (min > temp) {
								min = temp;
								min_year = str[1];
							}
							avg += temp;
							count++;
							currentLine = br.readLine();

						} else {

							System.out.println(name_l + "       " + max + "\t" + max_year + "\t" + month[index] + "\t"
									+ min + "\t" + min_year + "\t" + avg / count);
							max = -1;
							min = 99999;
							name_l = str[0];
							avg = 0;
							count = 0;
						}

					}

					System.out.println(name_l + "\t\t" + max + "\t" + max_year + "\t" + month[index] + "\t" + min + "\t"
							+ min_year + "\t" + avg / count);
					br.close();
					System.out.println();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			}
			case 3: {
				System.out.println("\nGAURAV SAKARIYA\n19BCE233\nTHANK YOU!!!!");
				flag = false;
				break;
			}
			default: {
				System.out.println("Enter Valid Choice !!!!!\n");
			}
			}

		} while (flag);
	}
}