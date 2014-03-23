package minijira.ws;

/**
 * Created by Alexx on 23.02.14
 */
public class WsWorker {

    private static DbCRUD crud;

    static  {
        initCrud();
    }

    private static void initCrud() {
        MinijiraWS ws = new MinijiraWS();
        crud = ws.getDbCRUDPort();
    }

    public static DbCRUD getCrud() {
        return crud;
    }
}
