package com.nrxtechnologies.hoto.utilities;

public class SiteDetails {
    private String ticket_Id;
    private String planed_Date;
    private String status;
    private String site_Name;
    private String tower_name;

    private String hst;
    private String cn;
    private String state;
    private String cicle;
    private String cmp;
    private String siteaddress;
    private String siteid;
    private String typeofSite;
    private String sourceofpower;


    public String getTicket_Id() {
        return ticket_Id;
    }

    public String getHst() {
        return hst;
    }

    public String getCn() {
        return cn;
    }

    public String getState() {
        return state;
    }

    public String getCicle() {
        return cicle;
    }

    public String getCmp() {
        return cmp;
    }

    public String getSiteaddress() {
        return siteaddress;
    }

    public String getSiteid() {
        return siteid;
    }

    public String getTypeofSite() {
        return typeofSite;
    }

    public String getSourceofpower() {
        return sourceofpower;
    }

String ticket;
    public SiteDetails(String ticket_Id, String planed_Date, String status, String site_Name, String towername,String hst,String cn,String state
    ,String circle,String cmp,String siteaddress,String siteid,String typeofSite,String ticket) {
        this.ticket_Id = ticket_Id;
        this.planed_Date = planed_Date;
        this.status = status;
        this.site_Name = site_Name;
        this.tower_name = towername;
        this.hst=hst;
        this.cn=cn;
        this.state=state;
        this.cicle=circle;
        this.cmp=cmp;
        this.ticket=ticket;
        this.siteaddress=siteaddress;
                this.siteid=siteid;
                this.typeofSite=typeofSite;
                this.sourceofpower=sourceofpower;

    }

    public String getTicketId() {
        return ticket_Id;
    }
public String getTicket()
{
    return ticket;
}
    public String getPlaned_Date() {
        return planed_Date;
    }

    public String getSite_Name() {
        return site_Name;
    }


    public String getStatus() {
        return status;
    }

    public String getTower_name() {
        return tower_name;
    }
}