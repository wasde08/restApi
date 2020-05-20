package qwer.controller;

import org.springframework.web.bind.annotation.*;
import qwer.dao.DealDAO;
import qwer.dao.MessageDAO;
import qwer.entity.Deal;
import qwer.entity.Message;

import java.util.List;


@RestController
public class MessageRestController {

    @RequestMapping(value = "/message", //
            method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getMessage() {
        MessageDAO messageDAO = new MessageDAO();
        List<Message> list = messageDAO.getMessageAll();
        return list;
    }

    @RequestMapping(value = "/message/{prodId}", //
            method = RequestMethod.GET)
    @ResponseBody
    public Message getMessage(@PathVariable("prodId") String prodId) {
        MessageDAO messageDAO = new MessageDAO();
        return messageDAO.getMessage(String.valueOf(prodId));
    }


    @RequestMapping(value = "/message", //
            method = RequestMethod.POST)
    @ResponseBody
    public Message add(@RequestBody Message message) {
        MessageDAO messageDAO = new MessageDAO();
        return messageDAO.addMessage(message);
    }
    

    @RequestMapping(value = "/message/{prodId}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("prodId") String prodId) {
        MessageDAO messageDAO = new MessageDAO();
        messageDAO.delete(String.valueOf(prodId));
    }
}
