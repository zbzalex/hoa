package com.web3horizen.hoa.framework.mvc.results;

import com.web3horizen.hoa.framework.Request;
import com.web3horizen.hoa.framework.Response;
import com.web3horizen.hoa.framework.mvc.Result;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

public class Json extends Result {
    private final Object obj;

    public Json(JSONObject obj) {
        this.obj = obj;
    }

    public Json(Object obj) throws InstantiationException, IllegalAccessException {
        JSONObject jsonObj = new JSONObject();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            jsonObj.put(field.getName(), field.get(obj));
        }

        this.obj = jsonObj;
    }

    @Override
    public void apply(Request request, Response response) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=utf-8");
        response.write(obj.toString());
    }
}
