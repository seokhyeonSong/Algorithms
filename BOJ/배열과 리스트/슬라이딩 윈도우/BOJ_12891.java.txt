import java.util.StringTokenizer;
import java.io.*;
import java.util.Arrays;

public class BOJ_12891 {
    static int givenDNA[];
    static int myDNA[];
    static int checkValid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        char[] DNAString = new char[n];
        DNAString = st.nextToken().toCharArray();
        givenDNA = new int[4];
        myDNA = new int[4];
        checkValid = 0;
        int count = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i += 1) {
            givenDNA[i] = Integer.parseInt(st.nextToken());
            if (givenDNA[i] == 0) {
                checkValid++;
            }
        }

        for (int i = 0; i < m; i += 1) {
            Add(DNAString[i]);
        }
        if (checkValid == 4) {
            count++;
        }
        for (int i = m; i < n; i++) {
            int j = i - m;
            Add(DNAString[i]);
            Remove(DNAString[j]);
            if (checkValid == 4) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static void Add(char c) {
        switch (c) {
            case 'A':
                myDNA[0]++;
                if (myDNA[0] == givenDNA[0]) {
                    checkValid++;
                }
                break;
            case 'C':
                myDNA[1]++;
                if (myDNA[1] == givenDNA[1]) {
                    checkValid++;
                }
                break;
            case 'G':
                myDNA[2]++;
                if (myDNA[2] == givenDNA[2]) {
                    checkValid++;
                }
                break;
            case 'T':
                myDNA[3]++;
                if (myDNA[3] == givenDNA[3]) {
                    checkValid++;
                }
                break;
            default:
                break;
        }
    }

    private static void Remove(char c) {
        switch (c) {
            case 'A':
                if (myDNA[0] == givenDNA[0]) {
                    checkValid--;
                }
                myDNA[0]--;
                break;
            case 'C':
                if (myDNA[1] == givenDNA[1]) {
                    checkValid--;
                }
                myDNA[1]--;
                break;
            case 'G':
                if (myDNA[2] == givenDNA[2]) {
                    checkValid--;
                }
                myDNA[2]--;
                break;
            case 'T':
                if (myDNA[3] == givenDNA[3]) {
                    checkValid--;
                }
                myDNA[3]--;
                break;
            default:
                break;
        }
    }
}