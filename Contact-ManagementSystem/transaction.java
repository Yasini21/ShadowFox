import java.time.LocalDateTime;

public class transaction {
    private String type;
    private double amount;
    private LocalDateTime date;
    public transaction(String type,double amount){
        this.type=type;
        this.amount=amount;
        this.date=LocalDateTime.now();

    }
    public String toString(){
        return date + ":"+type+": Rs."+amount;
    }
    
}
