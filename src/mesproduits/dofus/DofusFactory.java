package mesproduits.dofus;

import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DofusFactory {

    public static String donneBigJSON() {
        kong.unirest.HttpResponse<JsonNode> response = Unirest.get("https://api.dofusdu.de/dofus2/fr/items/equipment/all?filter[type_name]=dofus")
                .asJson();
        return response.getBody().toString();
    }

    public static List<Dofus> getListDofus(){
        ArrayList<Dofus> ret = new ArrayList<>();
        JSONArray items = (JSONArray) Unirest.get("https://api.dofusdu.de/dofus2/fr/items/equipment/all?filter[type_name]=dofus")
                .asJson().getBody().getObject().get("items");
        for (int i=0; i < items.length(); i++) {
            JSONObject o = items.getJSONObject(i);
            ret.add(new Dofus(o.getString("name"),o.getDouble("level"),o.getJSONObject("image_urls").getString("icon")));
        }
        return ret;


    }

}
