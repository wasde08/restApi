package qwer.controller;

import org.springframework.web.bind.annotation.*;
import qwer.dao.DealDAO;
import qwer.entity.Deal;

import java.sql.SQLException;
import java.util.List;

@RestController
public class DealRestController {

    @RequestMapping(value = "/deal",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Deal> getDeals() {
        DealDAO dealDAO = new DealDAO();
        List<Deal> list = dealDAO.getDealAll();
        return list;
    }


    @RequestMapping(value = "/deal/{prodId}", //
            method = RequestMethod.GET)
    @ResponseBody
    public Deal getDeal(@PathVariable("prodId") String prodId) {
        DealDAO dealDAO = new DealDAO();
        return dealDAO.getDeal(String.valueOf(prodId));
    }

    @RequestMapping(value = "/deal", //
            method = RequestMethod.POST)
    @ResponseBody
    public Deal add(@RequestBody Deal deal) throws SQLException {
        DealDAO dealDAO = new DealDAO();
        return dealDAO.addDeal(deal);
    }

    @RequestMapping(value = "/deal", //
            method = RequestMethod.PUT)
    @ResponseBody
    public Deal update( @RequestBody Deal deal) throws SQLException {
        DealDAO dealDAO = new DealDAO();
        return dealDAO.updateDeal(deal);
    }
    @RequestMapping(value = "/deal/{prodId}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("prodId") String prodId){
        DealDAO dealDAO = new DealDAO();
        dealDAO.delete(String.valueOf(prodId));
    }
}
