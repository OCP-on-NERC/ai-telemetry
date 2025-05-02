package org.mghpcc.aitelemetry.model.baremetalorder;

import org.computate.search.wrap.Wrap;
import org.computate.vertx.search.list.SearchList;
import org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClass;

import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Promise: true
 **/
public class BareMetalOrderPage extends BareMetalOrderPageGen<BareMetalOrderGenPage> {

    /**
     * Ignore: true
     */
    protected void _resourceClassSearch(Promise<SearchList<BareMetalResourceClass>> promise) {
        SearchList<BareMetalResourceClass> l = new SearchList<>();
        l.setC(BareMetalResourceClass.class);
        l.q("*:*");
        l.rows(100);
        l.setStore(true);
        promise.complete(l);
    }

    protected void _resourceClasses(Wrap<JsonArray> w) {
        JsonArray array = new JsonArray();
        resourceClassSearch.getList().stream().forEach(resourceClass -> {
            array.add(JsonObject.mapFrom(resourceClass));
        });
        w.o(array);
    }
}
