import java.util.Arrays;
import java.io.*;

public class BOJ_1377 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        mData[] numbers = new mData[size];

        for (int i = 0; i < size; i += 1) {
            numbers[i] = new mData(Integer.parseInt(br.readLine()), i);
        }

        Arrays.sort(numbers);

        int difference = 0;
        for (int i = 0; i < size; i += 1) {
            if (numbers[i].index - i > difference) {
                difference = numbers[i].index - i;
            }
        }

        bw.write((difference + 1) + "\n");
        bw.flush();
    }
}

class mData implements Comparable<mData> {
    int value;
    int index;

    public mData(int value, int index) {
        super();
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(mData mdata1) {
        return this.value - mdata1.value;
    }
}