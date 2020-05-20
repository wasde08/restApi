package qwer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import qwer.dao.ClientDAO;
import qwer.dao.DealDAO;
import qwer.dao.ProductDAO;
import qwer.dao.SellerDAO;
import qwer.entity.Client;
import qwer.entity.Deal;
import qwer.entity.Product;
import qwer.entity.Seller;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        /*ClientDAO productDAO = new ClientDAO();
        productDAO.getClientAll().forEach(System.out::println);
       /* Timestamp stamp = new Timestamp(System.currentTimeMillis());
        productDAO.updateDeal(new Deal("3",String.valueOf(stamp),"3","2","5","1"));
        System.out.println("---------------");
        System.out.println(ProductDAO.getProduct("3"));
        /*Timestamp stamp = new Timestamp(System.currentTimeMillis());
        System.out.println(stamp);
        Date date = new Date(stamp.getTime());
        System.out.println(date);*/
    }

}
