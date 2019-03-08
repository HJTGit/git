import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class People{
    private String name;
    private String phone;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
public class Test{
    static List<People> addressBook = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String args[]){
        while (true){
            System.out.println("####################");
            System.out.println("添加联系人输入1");
            System.out.println("删除联系人输入2");
            System.out.println("展示联系人输入3");
            System.out.println("####################");
            String i = scanner.next();
            if (i == "1"){
                add();
            }
            else if (i == "2"){
                delete();
            }
            else if (i == "3"){
                show();
            }
            else{
                System.out.print("输入参数错误");
            }
        }
    }
    public static void add(){
        People people = new People();
        System.out.print("输入姓名：");
        String name = scanner.next();
        System.out.print("输入手机号：");
        String phone = scanner.next();
        people.setName(name);
        people.setPhone(phone);
        addressBook.add(people);
        show();
    }
    public static void show(){
        System.out.println("联系人列表-----------------------------");
        for (int i=0; i < addressBook.size();i++) {
            People p = addressBook.get(i);
            System.out.println("序号："+i+" 姓名："+p.getName()+" 手机号："+p.getPhone());
        }
        System.out.println("-----------------------------");
    }
    public static void delete(){
        System.out.print("输入要删除的联系人序号：");
        int i = scanner.nextInt();
        try {
            addressBook.remove(i);
            System.out.print("删除成功");
        }catch (Exception e){
            System.out.println("####################");
            System.out.println("无该序号联系人");
            System.out.println("####################");
        }
        show();
    }
}

