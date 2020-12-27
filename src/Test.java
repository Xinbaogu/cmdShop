import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        boolean bool=true;

        while (bool) {
            System.out.println("请输入用户名");
            String username = sc.next();//阻塞方法

            System.out.println("请输入密码");
            String password = sc.next();

            //File file=new File("C:\\Users\\DELL\\Desktop\\cmdShop\\Customer.xlsx");绝对路径不适用于用户

            InputStream in = Class.forName("Test").getResourceAsStream("/Customer.xlsx");
            ReadCustomerExcel readCustomerExcel = new ReadCustomerExcel();//创建对象
            Customer customers[] = readCustomerExcel.readExcel(in);

            //用户信息比对
            for (int i = 0; i < customers.length; i++) {
                //System.out.println(customers[i].getUsername());
                //System.out.println(customers[i].getPassword());
                if (username.equals(customers[i].getUsername()) && password.equals(customers[i].getPassword())) {
                    bool = false;
                    System.out.println("登录成功");
                    break;
                } else{
                    System.out.println("登录失败，用户名或密码错误");
                }
            }

        }


        if(bool==false) {

            InputStream in = Class.forName("Test").getResourceAsStream("/Product.xlsx");
            ReadProductExcel readProductExcel = new ReadProductExcel();//创建对象
            Product products[] = readProductExcel.readExcel(in);

            System.out.println("");
            System.out.print("\t");
            System.out.println("欢迎来到慧慧子超市！");
            System.out.print("商品编号");
            System.out.print("\t商品名称");
            System.out.print("\t商品价格");
            System.out.println("\t商品数量");
            for (int j = 0; j < products.length; j++) {
                System.out.print(products[j].getProductId());
                System.out.print("\t"+products[j].getProductName());
                System.out.print("\t\t"+products[j].getPrices());
                System.out.println("\t\t"+products[j].getAmount());
            }
        }
    }
}