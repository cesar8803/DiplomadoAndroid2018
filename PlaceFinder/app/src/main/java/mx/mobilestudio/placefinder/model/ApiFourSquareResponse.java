package mx.mobilestudio.placefinder.model;

/**
 * Created by cesar on 8/11/18.
 */

public class ApiFourSquareResponse {

    private Meta meta;
    private ResponseF response;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ResponseF getResponse() {
        return response;
    }

    public void setResponse(ResponseF response) {
        this.response = response;
    }
}
