/*
* Copyright 2016 Yoshio Terada
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.yoshio3.entities.emotion;

import com.yoshio3.utils.MyObjectMapperProvider;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author yoterada
 */
public class EmotionImpl {

    public List<EmotionResponseJSONBody> getEmotionalPerson(String pictURI, String subsctiption){
        String baseURI = "https://api.projectoxford.ai/emotion/v1.0/recognize";
        
        Client client = ClientBuilder.newBuilder()
            .register(MyObjectMapperProvider.class)
            .register(JacksonFeature.class)
            .build();
        WebTarget target = client.target(baseURI);
        
        EmotionRequestJSONBody entity = new EmotionRequestJSONBody();
        entity.setUrl(pictURI);
        
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .header("Ocp-Apim-Subscription-Key", subsctiption)
                .post(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));
        
        //System.out.println(response.readEntity(String.class));
        
        EmotionResponseJSONBody[] persons = response.readEntity(EmotionResponseJSONBody[].class);
        List<EmotionResponseJSONBody> list = Arrays.asList(persons);
        return list;
    }
}
