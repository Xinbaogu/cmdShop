import java.io.File;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入用户名");
        String username = sc.next();//阻塞方法

        System.out.println("请输入密码");
        String password = sc.next();

        //创建对象
        File file=new File("C:\\Users\\DELL\\Desktop\\cmdShop\\Customer.xlsx");
        ReadExcel readExcel=new ReadExcel();
        Customer customers[]=readExcel.readExcel(file);

        //用户信息比对
        for(int i=0;i< customers.length;i++){
            System.out.println(customers[i].getUsername());
            System.out.println(customers[i].getPassword());
            /*if(username.equals(customers[i].getUsername()) && password.equals(customers[i].getPassword())){
                System.out.println("登录成功");
            }else{
                System.out.println("登录失败");
            }*/
        }

    }
}