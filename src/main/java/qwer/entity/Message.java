package qwer.entity;

import qwer.dao.DealDAO;

public class Message {
    String id;
    String text;
    String idDeal;
    Deal deal;

    public Message(String id, String text, String idDeal) {
        this.id = id;
        this.text = text;
        this.idDeal= idDeal;
        this.deal = DealDAO.getDeal(idDeal);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIdDeal() {
        return idDeal;
    }

    public void setIdDeal(String idDeal) {
        this.idDeal = idDeal;
    }
}

