import minijira.ws.DbCRUD;
import minijira.ws.MinijiraWS;

public class Main {

    public static void main(String[] args) {
        MinijiraWS ws = new MinijiraWS();
        DbCRUD crud = ws.getDbCRUDPort();

        System.out.println(crud.sayHello("console client!"));
    }
}
