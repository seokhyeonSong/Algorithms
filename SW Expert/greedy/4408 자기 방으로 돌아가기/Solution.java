import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    static int cnt;
    static Student[] students;

    static int goBack() {
        int[] cnt = new int[201];
        for (int i = 0; i < students.length; i += 1) {
            int start = students[i].currentRoom > students[i].myRoom ? students[i].myRoom : students[i].currentRoom;
            int end = students[i].currentRoom <= students[i].myRoom ? students[i].myRoom : students[i].currentRoom;
            for (int j = start; j <= end; j += 1) {
                cnt[j] += 1;
            }
        }
        int max = 0;
        for (int i = 0; i < cnt.length; i += 1) {
            if (max < cnt[i]) {
                max = cnt[i];
            }
        }
        return max;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int TC, currentRoom, myRoom;
        TC = Integer.parseInt(br.readLine());

        for (int i = 1; i <= TC; i += 1) {
            cnt = Integer.parseInt(br.readLine());
            students = new Student[cnt];
            for (int j = 0; j < cnt; j += 1) {
                st = new StringTokenizer(br.readLine());
                currentRoom = Integer.parseInt(st.nextToken());
                myRoom = Integer.parseInt(st.nextToken());
                students[j] = new Student(currentRoom, myRoom);
            }
            bw.write("#" + i + " " + goBack() + "\n");
            bw.flush();
        }
    }
}

class Student {
    int currentRoom;
    int myRoom;

    Student(int currentRoom, int myRoom) {
        this.currentRoom = (currentRoom + 1) / 2;
        this.myRoom = (myRoom + 1) / 2;
    }
}