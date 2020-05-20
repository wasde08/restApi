package qwer.controller;


import org.springframework.web.bind.annotation.*;
import qwer.entity.Client;
import qwer.dao.ClientDAO;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ClientRestController {
    @RequestMapping(value = "/client",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Client> getClients() {
        ClientDAO clientDAO = new ClientDAO();
        List<Client> list = clientDAO.getClientAll();
        return list;
    }

    @RequestMapping(value = "/client/{prodId}", //
            method = RequestMethod.GET)
    @ResponseBody
    public Client getClient(@PathVariable("prodId") String prodId) throws SQLException {
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.getClient(String.valueOf(prodId));
    }


    @RequestMapping(value = "/client", //
            method = RequestMethod.POST)
    @ResponseBody
    public Client add(@RequestBody Client client) throws SQLException {
        ClientDAO clientDAO = new ClientDAO();
        if (client.getCheck()==0){
            if (clientDAO.check(client)!=null) {
                return clientDAO.getClient(clientDAO.check(client).getClientId());
            } else {
                return null;
            }
        } else {
            return clientDAO.addClient(client);
        }
    }

    @RequestMapping(value = "/client", //
            method = RequestMethod.PUT)
    @ResponseBody
    public Client update (@RequestBody Client client) throws SQLException {
        ClientDAO clientDAO = new ClientDAO();
        return  clientDAO.updateClient(client);
    }

    @RequestMapping(value = "/client/{prodId}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("prodId") String prodId) throws SQLException {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.delete(String.valueOf(prodId));
    }
}
