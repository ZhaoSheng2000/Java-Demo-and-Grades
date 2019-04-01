package 集合类;

import java.util.ArrayList;
import java.util.List;

public class ListDemo{
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        for(int j=0;j<3;j++){
            System.out.println(list.get(j));
        }
    }
}
