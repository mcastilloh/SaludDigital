package fia.ues.saluddigital.Conexiones;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import fia.ues.saluddigital.GestionPeso.RecordPeso;

public class WebServiceManager {
    private static WebServiceManager instance;
    private RequestQueue requestQueue;
    private static Context context;

    private WebServiceManager(Context ctx) {
        context = ctx;
        requestQueue = getRequestQueue();
    }

    public static synchronized WebServiceManager getInstance(Context context) {
        if (instance == null) {
            instance = new WebServiceManager(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void registrarPeso(RecordPeso infoPeso, Response.Listener<String> responseListener, Response.ErrorListener errorListener){
        String url = "https://gg20013pdm115.000webhostapp.com/registrar_peso.php";

        StringRequest request = new StringRequest(Request.Method.GET, url + "?fecha=" + infoPeso.getFecha() + "&peso=" + infoPeso.getPeso() + "&unidad=" + infoPeso.getUnidad(), responseListener, errorListener);

        addToRequestQueue(request);
    }

}
