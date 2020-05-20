package qwer.controller;

import org.springframework.web.bind.annotation.*;
import qwer.dao.SellerDAO;
import qwer.entity.Seller;

import java.util.List;

@RestController
public class SellerRestController {
   @RequestMapping(value = "/seller",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Seller> getSellers() {
        SellerDAO sellerDAO = new SellerDAO();
        List<Seller> list = sellerDAO.getSellerAll();
        return list;
    }

    @RequestMapping(value = "/seller/{prodId}", //
            method = RequestMethod.GET)
    @ResponseBody
    public Seller getSeller(@PathVariable("prodId") String prodId) {
        SellerDAO sellerDAO = new SellerDAO();
        return sellerDAO.getSeller(String.valueOf(prodId));
    }


    @RequestMapping(value = "/seller", //
            method = RequestMethod.POST)
    @ResponseBody
    public Seller add(@RequestBody Seller seller){
        SellerDAO sellerDAO = new SellerDAO();
        if (seller.getCheck()==0){
            if (sellerDAO.check(seller)!=null) {
                return sellerDAO.getSeller(sellerDAO.check(seller).getSellerId());
            } else {
                return null;
            }
        } else {
            return sellerDAO.addSeller(seller);
        }
    }

    @RequestMapping(value = "/seller", //
            method = RequestMethod.PUT)
    @ResponseBody
    public Seller update( @RequestBody Seller seller) {
        SellerDAO sellerDAO = new SellerDAO();
        return sellerDAO.updateSeller(seller);
    }

    @RequestMapping(value = "/seller/{prodId}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("prodId") String prodId){
        SellerDAO sellerDAO = new SellerDAO();
        sellerDAO.delete(String.valueOf(prodId));
    }

}
