package shared.transferobject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable
{
  private String msg;
  private Date date;
  private String username;
  public Message(String msg,Date date,String username){
    this.msg=msg;
    this.date=date;
    this.username=username;
  }

  public String getMsg() {
    return msg;
  }

  public Date getDate() {
    return date;
  }

  public String getUsername() {
    return username;
  }

  @Override public String toString()
  {
  	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    return username + "(" + formatter.format(date) + "): " + msg;
  }
}

