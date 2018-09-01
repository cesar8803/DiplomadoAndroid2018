package mx.mobilestudio.storelocator.LiverpoolPackage;


import java.util.List;

public class ApiLiverpoolResponse {

    private List<Store> stores = null;

    public List <Store> getStore (){
        return stores;
    }

    public void setStore(List<Store> stores) {
        this.stores = stores;
    }
}
