package utils;

import entity.Employee;
import other.Period;
import other.Roster;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FileUtils {
    private Period period;

    private List<Employee> employees = new LinkedList<>();

    private List<Roster> rosters = new LinkedList<>();

    public FileUtils(String filename) throws IOException {
        getData(readFile(filename));
    }

    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    /**
     * 按行读取文件
     * @return List
     */
    private List<String> readFile(String filename) throws IOException {
        // 使用ArrayList来存储每行读取到的字符串
        List<String> list = new LinkedList<>();
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(filename), "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            // 按行读取字符串
            while ((str = bufferedReader.readLine()) != null) {
                list.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            bufferedReader.close();
            inputStreamReader.close();
        }
        return list;
    }

    private void getData(List<String> list){
        int len = list.size();
        int i = 0;

        while (i < len) {
            String str = list.get(i);
            if (str.contains("Period")) {
                String t = list.get(i);
                int start = 1;
                int end = t.length();
                String[] data = new String[2];
                for (int j = 0; j < t.length(); ++j) {
                    if (t.charAt(j) == '{') {
                        start = j + 1;
                    }
                }
                for (int j = t.length() - 1; j >= 0; --j){
                    if(t.charAt(j) == '}'){
                        end = j;
                    }
                }
                String sub = t.substring(start, end);
                data = sub.split(",");
                i++;
                period = new Period(data[0], data[1]);
            }
            if (str.contains("Employee")) {
                i++;
                while (!list.get(i).equals("}")) {
                    String t = list.get(i);
                    Employee emp = new Employee();
                    int start = 1;
                    int end = t.length();
                    for (int j = 0; j < t.length(); ++j) {
                        if (t.charAt(j) == '{') {
                            emp.setName(t.substring(2, j));
                            start = j + 1;
                        }
                    }
                    for(int j = t.length() - 1; j >= 0; --j){
                        if(t.charAt(j) == '}'){
                            end = j;
                        }
                    }
                    String sub = t.substring(start, end);
                    String[] data = sub.split(",");
                    emp.setCareer(data[0]);
                    emp.setPhone(data[1]);
                    employees.add(emp);
                    i++;
                }
                i++;
            }
            if (str.contains("Roster")) {
                i++;
                while (!list.get(i).equals("}")) {
                    String t = list.get(i);
                    Roster roster = new Roster();
                    int start = 1;
                    int end = t.length();
                    String[] date = new String[2];
                    for (int j = 0; j < t.length(); ++j) {
                        if (t.charAt(j) == '{') {
                            roster.setName(t.substring(2, j));
                            start = j + 1;
                        }
                    }
                    for(int j = t.length() - 1; j >= 0; --j){
                        if(t.charAt(j) == '}'){
                            end = j;
                        }
                    }
                    String sub = t.substring(start, end);
                    date = sub.split(",");
                    roster.addDate(date);
                    rosters.add(roster);
                    i++;
                }
                i++;
            }
        }
    }


    public Period getPeriods() {
        return period;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Roster> getRosters() {
        return rosters;
    }
}
