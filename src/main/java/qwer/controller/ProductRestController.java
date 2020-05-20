package qwer.controller;

import org.springframework.web.bind.annotation.*;
import qwer.dao.*;
import qwer.entity.Product;

import java.util.List;

@RestController
public class ProductRestController {

    @RequestMapping(value = "/product",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProducts() {
        ProductDAO productDAO = new ProductDAO();
        List<Product> list = productDAO.getProductAll();
        return list;
    }

    @RequestMapping(value = "/product/{prodId}", //
            method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable("prodId") String prodId) {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.getProduct(String.valueOf(prodId));
    }

    @RequestMapping(value = "/product", //
            method = RequestMethod.POST)
    @ResponseBody
        public Product add(@RequestBody Product product){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.addProduct(product);
    }

    @RequestMapping(value = "/product", //
            method = RequestMethod.PUT)
    @ResponseBody
    public Product update( @RequestBody Product product) {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.updateProduct(product);
    }
    @RequestMapping(value = "/product/{prodId}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("prodId") String prodId){
        ProductDAO productDAO = new ProductDAO();
        productDAO.delete(String.valueOf(prodId));
    }
}
