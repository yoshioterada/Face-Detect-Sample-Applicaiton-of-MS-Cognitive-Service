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
package com.yoshio3.entities.verify;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Yoshio Terada
 */
public class FaceVerifyImpl {
    
    public boolean isEqualofTwoFaces(String setFaceId1, String setFaceId2, String subsctiption){
        String baseURI = "https://api.projectoxford.ai/face/v1.0/verify";
        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseURI);
        
        FaceVerifyRequestJSONBody entity = new FaceVerifyRequestJSONBody();
        entity.setFaceId1(setFaceId1);
        entity.setFaceId2(setFaceId2);
        
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .header("Ocp-Apim-Subscription-Key", subsctiption)
                .post(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));
        
        FaceVerifyResponseJSONBody resBody = response.readEntity(FaceVerifyResponseJSONBody.class);
                
        return resBody.getConfidence() >= 0.5;
        /*
        https://dev.projectoxford.ai/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f3039523a
        confidence value :
        A number indicates the confidence of whether two faces belong to one person.
        By default, isIdentical is set to True if confidence is greater or equal than 0.5.
        This is useful for advanced users to override "isIdentical" and fine-tune the 
        result on their own data.
         */
    }
}
