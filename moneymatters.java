package moneymatters;

/** Simple yet moderately fast I/O routines.
 *
 * Example usage:
 *
 * Kattio io = new Kattio(System.in, System.out);
 *
 * while (io.hasMoreTokens()) {
 *    int n = io.getInt();
 *    double d = io.getDouble();
 *    double ans = d*n;
 *
 *    io.println("Answer: " + ans);
 * }
 *
 * io.close();
 *
 *
 * Some notes:
 *
 * - When done, you should always do io.close() or io.flush() on the
 *   Kattio-instance, otherwise, you may lose output.
 *
 * - The getInt(), getDouble(), and getLong() methods will throw an
 *   exception if there is no more data in the input, so it is generally
 *   a good idea to use hasMoreTokens() to check for end-of-file.
 *
 * @author: Kattis
 */

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

class Kattio extends PrintWriter {
	public Kattio(InputStream i) {
		super(new BufferedOutputStream(System.out));
		r = new BufferedReader(new InputStreamReader(i));
	}
	public Kattio(InputStream i, OutputStream o) {
		super(new BufferedOutputStream(o));
		r = new BufferedReader(new InputStreamReader(i));
	}

	public boolean hasMoreTokens() {
		return peekToken() != null;
	}

	public int getInt() {
		return Integer.parseInt(nextToken());
	}

	public double getDouble() {
		return Double.parseDouble(nextToken());
	}

	public long getLong() {
		return Long.parseLong(nextToken());
	}

	public String getWord() {
		return nextToken();
	}



	private BufferedReader r;
	private String line;
	private StringTokenizer st;
	private String token;

	private String peekToken() {
		if (token == null)
			try {
				while (st == null || !st.hasMoreTokens()) {
					line = r.readLine();
					if (line == null) return null;
					st = new StringTokenizer(line);
				}
				token = st.nextToken();
			} catch (IOException e) { }
		return token;
	}

	private String nextToken() {
		String ans = peekToken();
		token = null;
		return ans;
	}
}


public class moneymatters {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		boolean memes = true;
		int a = io.getInt();
		int b = io.getInt();
		int counter = 1;
		int[][] matrix = new int[a][2];
		for (int x =0;x<a;x++){
			matrix[x][0] = io.getInt();
		}
		for (int y=0;y<b;y++){
			int f1 = io.getInt();
			int f2 = io.getInt();
			if(matrix[f1][1] == 0 && matrix[f2][1] == 0){
				matrix[f1][1] = counter;
				matrix[f2][1] = counter;
				counter +=1;
			}else if(matrix[f1][1] == 0 && matrix[f2][1]>0){
				matrix[f1][1] = matrix[f2][1];
			}else if(matrix[f2][1] == 0 && matrix[f1][1]>0){
				matrix[f2][1] = matrix[f1][1];
			}else{
				if(matrix[f1][1]>matrix[f2][1]){
					int change = matrix[f1][1];
					for (int t =0; t<a;t++){
						if(matrix[t][1]==change){
							matrix[t][1] = matrix[f2][1];
						}
					}
				}else{
					int change = matrix[f2][1];
					for (int t =0; t<a;t++){
						if(matrix[t][1]==change){
							matrix[t][1] = matrix[f1][1];
						}
					}
				}
			}
		}
		for (int z= 1;z<a;z++){
			if (matrix[z][1] == 0){
				if (matrix[z][0] != 0) {
					memes = false;
				}
			}
		}
		if(memes){
			for (int i = 1; i<=counter;i++){
				int total = 0;
				for (int j=0; j<a;j++){
					if(matrix[j][1]==i){
						total += matrix[j][0];
					}
				}
				if(total!=0){
					System.out.println("IMPOSSIBLE");
					memes = false;
					i = counter + 1;
				}
			}
		}
		if(memes){
			System.out.println("POSSIBLE");
		}
		io.close();
	}

}
